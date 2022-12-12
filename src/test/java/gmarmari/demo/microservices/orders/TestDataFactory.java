package gmarmari.demo.microservices.orders;

import gmarmari.demo.microservices.orders.api.ProductDto;
import gmarmari.demo.microservices.orders.entities.ProductDao;

import java.util.Random;

public class TestDataFactory {

    private static final Random random = new Random();

    private TestDataFactory() {
        // Hide constructor
    }

    public static ProductDao aProductDao() {
        ProductDao product = new ProductDao();
        product.setName("Milk");
        product.setBrand("Milk Maker");
        product.setColor(random.nextBoolean() ? "White" : null);
        product.setDescription(random.nextBoolean() ? "The best milk!" : null);
        product.setHeightMm(random.nextInt());
        product.setWidthMm(random.nextInt());
        product.setLengthMm(random.nextInt());
        product.setWeightGrams(random.nextInt());
        product.setPrizeEuro(random.nextDouble());
        return product;
    }

    public static ProductDto aProductDto() {
        return new ProductDto(
                random.nextInt(),
                "Milk",
                "Milk Maker",
                random.nextBoolean() ? "White" : null,
                random.nextBoolean() ? "The best milk!" : null,
                random.nextInt(),
                random.nextInt(),
                random.nextInt(),
                random.nextInt(),
                random.nextDouble()
        );
    }
}
