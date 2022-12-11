package gmarmari.demo.microservices.orders.api.controllers;

import gmarmari.demo.microservices.orders.adapters.ProductAdapter;
import gmarmari.demo.microservices.orders.api.ProductApi;
import gmarmari.demo.microservices.orders.api.ProductDto;
import gmarmari.demo.microservices.orders.api.ProductNotFoundException;
import gmarmari.demo.microservices.orders.api.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class ProductRestController implements ProductApi {

    private final ProductAdapter adapter;

    @Autowired
    public ProductRestController(ProductAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public List<ProductDto> getProducts() {
        return adapter.getProducts();
    }

    @Override
    public ProductDto getProductById(long id) {
        return adapter.getProduct(id)
                .orElseThrow(ProductNotFoundException::new);
    }

    @Override
    public void deleteById(long id) {
        adapter.delete(id)
                .throwIfNotOk(() -> new ResponseStatusException(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        "An error occurred by deleting the product"));
    }

    @Override
    public void saveProduct(ProductDto product) {
        adapter.save(product)
                .throwIfNotOk(() -> new ResponseStatusException(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        "An error occurred by saving the product"));

    }
}
