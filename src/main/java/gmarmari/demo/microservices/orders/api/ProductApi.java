package gmarmari.demo.microservices.orders.api;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/products")
@Tag(name = "Product API", description = "Product management API")
public interface ProductApi {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            description = "List all products"
    )
    List<ProductDto> getProducts();

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            description = "Get the product with the given id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProductDto.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Product not found")

    })
    ProductDto getProductById(@PathVariable("id") long id);

    @DeleteMapping(path = "/{id}")
    @Operation(
            description = "Delete the product with the given id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Product was deleted"),
            @ApiResponse(
                    responseCode = "500",
                    description = "An error occurred by deleting the product")

    })
    void deleteById(@PathVariable("id") long id);

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            description = "Save the given product"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Product was saved"),
            @ApiResponse(
                    responseCode = "500",
                    description = "An error occurred by saving the product")

    })
    void saveProduct(@RequestBody ProductDto product);

}
