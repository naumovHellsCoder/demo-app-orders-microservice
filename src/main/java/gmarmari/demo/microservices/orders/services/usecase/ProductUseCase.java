package gmarmari.demo.microservices.orders.services.usecase;

import gmarmari.demo.microservices.orders.entities.ProductDao;
import gmarmari.demo.microservices.orders.repositories.ProductRepository;
import gmarmari.demo.microservices.orders.services.ProductService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductUseCase implements ProductService {

    private static final Sort SORT_BY_NAME_ASC = Sort.by(Sort.Order.asc("name").ignoreCase());

    private final ProductRepository repository;

    public ProductUseCase(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(ProductDao productDao) {
        repository.save(productDao);
    }

    @Override
    public void delete(long productId) {
        repository.deleteById(productId);
    }

    @Override
    public Optional<ProductDao> getProduct(long productId) {
        return repository.findById(productId);
    }

    @Override
    public List<ProductDao> getProducts() {
        return repository.findAll(SORT_BY_NAME_ASC);
    }
}
