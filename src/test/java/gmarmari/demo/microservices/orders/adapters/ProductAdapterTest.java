package gmarmari.demo.microservices.orders.adapters;

import gmarmari.demo.microservices.orders.api.ProductDto;
import gmarmari.demo.microservices.orders.api.Result;
import gmarmari.demo.microservices.orders.entities.ProductDao;
import gmarmari.demo.microservices.orders.services.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static gmarmari.demo.microservices.orders.TestDataFactory.aProductDao;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductAdapterTest {

    @Mock
    private ProductService service;

    @InjectMocks
    private ProductAdapter adapter;

    @Test
    void save() {
        // Given
        ProductDao dao = aProductDao();
        ProductDto dto = new ProductDto(
                dao.getId(),
                dao.getVersion(),
                dao.getName(),
                dao.getBrand(),
                dao.getDescription(),
                dao.getColor(),
                dao.getLengthMm(),
                dao.getWidthMm(),
                dao.getHeightMm(),
                dao.getWeightGrams(),
                dao.getPrizeEuro()
        );

        // When
        Result result = adapter.save(dto);

        // Then
        assertThat(result).isEqualTo(Result.OK);
        verify(service).save(dao);
        verifyNoMoreInteractions(service);
    }

    @Test
    void save_error() {
        // Given
        ProductDao dao = aProductDao();
        ProductDto dto = new ProductDto(
                dao.getId(),
                dao.getVersion(),
                dao.getName(),
                dao.getBrand(),
                dao.getDescription(),
                dao.getColor(),
                dao.getLengthMm(),
                dao.getWidthMm(),
                dao.getHeightMm(),
                dao.getWeightGrams(),
                dao.getPrizeEuro()
        );

        doThrow(new NullPointerException()).when(service).save(dao);

        // When
        Result result = adapter.save(dto);

        // Then
        assertThat(result).isEqualTo(Result.ERROR);
        verify(service).save(dao);
        verifyNoMoreInteractions(service);
    }

    @Test
    void delete() {
        // Given
        long productId = 123;

        // When
        Result result = adapter.delete(productId);

        // Then
        assertThat(result).isEqualTo(Result.OK);
        verify(service).delete(productId);
        verifyNoMoreInteractions(service);
    }

    @Test
    void delete_error() {
        // Given
        long productId = 123;

        doThrow(new NullPointerException()).when(service).delete(productId);

        // When
        Result result = adapter.delete(productId);

        // Then
        assertThat(result).isEqualTo(Result.ERROR);
        verify(service).delete(productId);
        verifyNoMoreInteractions(service);
    }

    @Test
    void getProduct() {
        // Given
        long productId = 123;
        ProductDao dao = aProductDao();
        dao.setId(productId);

        when(service.getProduct(productId)).thenReturn(Optional.of(dao));

        // When
        Optional<ProductDto>result = adapter.getProduct(productId);

        // Then
        assertThat(result).isPresent();
        ProductDto dto = result.get();

        assertThat(dto.id).isEqualTo(dao.getId());
        assertThat(dto.version).isEqualTo(dao.getVersion());
        assertThat(dto.name).isEqualTo(dao.getName());
        assertThat(dto.brand).isEqualTo(dao.getBrand());
        assertThat(dto.description).isEqualTo(dao.getDescription());
        assertThat(dto.color).isEqualTo(dao.getColor());
        assertThat(dto.lengthMm).isEqualTo(dao.getLengthMm());
        assertThat(dto.widthMm).isEqualTo(dao.getWidthMm());
        assertThat(dto.heightMm).isEqualTo(dao.getHeightMm());
        assertThat(dto.weightGrams).isEqualTo(dao.getWeightGrams());
        assertThat(dto.prizeEuro).isEqualTo(dao.getPrizeEuro());

        verifyNoMoreInteractions(service);
    }

    @Test
    void getProduct_empty() {
        // Given
        long productId = 123;


        when(service.getProduct(productId)).thenReturn(Optional.empty());

        // When
        Optional<ProductDto>result = adapter.getProduct(productId);

        // Then
        assertThat(result).isEmpty();
        verifyNoMoreInteractions(service);
    }


    @Test
    void getProducts() {
        // Given
        ProductDao dao1 = aProductDao();
        dao1.setId(1);
        ProductDao dao2 = aProductDao();
        dao2.setId(2);
        ProductDao dao3 = aProductDao();
        dao3.setId(3);

        when(service.getProducts()).thenReturn(List.of(dao1, dao2, dao3));

        // When
        List<ProductDto> list = adapter.getProducts();

        // Then
        assertThat(list).hasSize(3);
        assertThat(list.get(0).id).isEqualTo(1);
        assertThat(list.get(1).id).isEqualTo(2);
        assertThat(list.get(2).id).isEqualTo(3);

        verifyNoMoreInteractions(service);
    }


}