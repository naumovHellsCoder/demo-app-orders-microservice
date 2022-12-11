package gmarmari.demo.microservices.orders.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import gmarmari.demo.microservices.orders.adapters.ProductAdapter;
import gmarmari.demo.microservices.orders.api.ProductDto;
import gmarmari.demo.microservices.orders.api.Result;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;
import java.util.Optional;

import static gmarmari.demo.microservices.orders.TestDataFactory.aProductDto;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ProductRestController.class)
class ProductRestControllerTest {

    @MockBean
    private ProductAdapter adapter;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Test
    void getProducts() throws Exception {
        // Given
        List<ProductDto> list = List.of(aProductDto(), aProductDto());
        when(adapter.getProducts()).thenReturn(list);

        // When
        ResultActions resultActions = mockMvc.perform(get("/products"));

        // Then
        resultActions.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(list)));
    }

    @Test
    void getProductById() throws Exception {
        // Given
        ProductDto dto = aProductDto();
        when(adapter.getProduct(dto.id)).thenReturn(Optional.of(dto));

        // When
        ResultActions resultActions = mockMvc.perform(get("/products/{id}", dto.id));

        // Then
        resultActions.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(dto)));
    }

    @Test
    void getProductById_notFound() throws Exception {
        // Given
        long productId = 123;
        when(adapter.getProduct(productId)).thenReturn(Optional.empty());

        // When
        ResultActions resultActions = mockMvc.perform(get("/products/{id}", productId));

        // Then
        resultActions.andExpect(status().isNotFound());
    }

    @Test
    void deleteById() throws Exception {
        // Given
        long productId = 123;
        when(adapter.delete(productId)).thenReturn(Result.OK);

        // When
        ResultActions resultActions = mockMvc.perform(delete("/products/{id}", productId));

        // Then
        resultActions.andExpect(status().isOk());
    }

    @Test
    void deleteById_error() throws Exception {
        // Given
        long productId = 123;
        when(adapter.delete(productId)).thenReturn(Result.ERROR);

        // When
        ResultActions resultActions = mockMvc.perform(delete("/products/{id}", productId));

        // Then
        resultActions.andExpect(status().isInternalServerError());
    }

    @Test
    void saveProduct() throws Exception {
        // Given
        ProductDto dto = aProductDto();
        when(adapter.save(dto)).thenReturn(Result.OK);

        // When
        ResultActions resultActions = mockMvc.perform(post("/products").
                contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(dto)));

        // Then
        resultActions.andExpect(status().isOk());
    }

    @Test
    void saveProduct_error() throws Exception {
        // Given
        ProductDto dto = aProductDto();
        when(adapter.save(dto)).thenReturn(Result.ERROR);

        // When
        ResultActions resultActions = mockMvc.perform(post("/products").
                contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(dto)));

        // Then
        resultActions.andExpect(status().isInternalServerError());
    }

}