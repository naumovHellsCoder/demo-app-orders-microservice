package gmarmari.demo.microservices.orders.services;

import gmarmari.demo.microservices.orders.entities.ProductDao;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    void save(ProductDao productDao);

    void delete(long productId);

    Optional<ProductDao> getProduct(long productId);

    List<ProductDao> getProducts();

}
