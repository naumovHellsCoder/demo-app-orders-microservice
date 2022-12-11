package gmarmari.demo.microservices.orders.services.usecase;

import gmarmari.demo.microservices.orders.entities.ProductDao;
import gmarmari.demo.microservices.orders.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

import static gmarmari.demo.microservices.orders.TestDataFactory.aProductDao;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductUseCaseTest {

    @Mock
    private ProductRepository repository;

    @InjectMocks
    private  ProductUseCase useCase;


    @Test
    void save() {
        // Given
        ProductDao product = aProductDao();

        // When
        useCase.save(product);

        // Then
        verify(repository).save(product);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void delete() {
        // Given
        long productId = 123;

        // When
        useCase.delete(productId);

        // Then
        verify(repository).deleteById(productId);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void getProduct() {
        // Given
        long id = 123;
        ProductDao product = aProductDao();
        product.setId(id);

        when(repository.findById(id)).thenReturn(Optional.of(product));

        // When
        Optional<ProductDao> result = useCase.getProduct(id);

        // Then
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(product);

        verifyNoMoreInteractions(repository);
    }

    @Test
    void getProducts() {
        // Given
        long id = 123;
        ProductDao product1 = aProductDao();
        product1.setId(1);
        ProductDao product2 = aProductDao();
        product2.setId(2);
        ProductDao product3 = aProductDao();
        product3.setId(2);

        Sort sort = Sort.by(Sort.Order.asc("name").ignoreCase());
        when(repository.findAll(sort)).thenReturn(List.of(product1, product2, product3));

        // When
        List<ProductDao> result = useCase.getProducts();

        // Then
        assertThat(result).containsExactly(product1, product2, product3);
        verifyNoMoreInteractions(repository);
    }

}