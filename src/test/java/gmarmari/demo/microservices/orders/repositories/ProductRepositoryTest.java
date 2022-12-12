package gmarmari.demo.microservices.orders.repositories;

import gmarmari.demo.microservices.orders.entities.ProductDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static gmarmari.demo.microservices.orders.TestDataFactory.aProductDao;
import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void findById() {
        // Given
        ProductDao product = aProductDao();
        Long id = entityManager.persistAndGetId(product, Long.class);

        // When
        Optional<ProductDao> resultOptional = repository.findById(id);

        // Then
        assertThat(resultOptional).isPresent();
        ProductDao result = resultOptional.get();
        assertThat(result.getId()).isEqualTo(id);
        assertThat(result.getName()).isEqualTo(product.getName());
        assertThat(result.getDescription()).isEqualTo(product.getDescription());
        assertThat(result.getColor()).isEqualTo(product.getColor());
        assertThat(result.getHeightMm()).isEqualTo(product.getHeightMm());
        assertThat(result.getWidthMm()).isEqualTo(product.getWidthMm());
        assertThat(result.getLengthMm()).isEqualTo(product.getLengthMm());
        assertThat(result.getWeightGrams()).isEqualTo(product.getWeightGrams());
        assertThat(result.getPrizeEuro()).isEqualTo(product.getPrizeEuro());
    }


}