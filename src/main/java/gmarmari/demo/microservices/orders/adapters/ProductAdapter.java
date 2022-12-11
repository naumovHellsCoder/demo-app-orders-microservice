package gmarmari.demo.microservices.orders.adapters;

import gmarmari.demo.microservices.orders.api.ProductDto;
import gmarmari.demo.microservices.orders.api.Result;
import gmarmari.demo.microservices.orders.entities.ProductDao;
import gmarmari.demo.microservices.orders.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ProductAdapter {

    private final ProductService service;

    @Autowired
    public ProductAdapter(ProductService service) {
        this.service = service;
    }

    public Result save(ProductDto productDto) {
        try {
            service.save(convert(productDto));
            return Result.OK;
        } catch (Exception e) {
            return Result.ERROR;
        }
    }

    public Result delete(long productId) {
        try {
            service.delete(productId);
            return Result.OK;
        } catch (Exception e) {
            return Result.ERROR;
        }
    }

    public Optional<ProductDto> getProduct(long productId) {
        return service.getProduct(productId).map(ProductAdapter::convert);
    }

    public List<ProductDto> getProducts() {
        return service.getProducts().stream()
                .map(ProductAdapter::convert)
                .collect(Collectors.toList());
    }

    private static ProductDao convert(ProductDto dto) {
        ProductDao dao = new ProductDao();
        dao.setId(dto.id);
        dao.setVersion(dto.version);
        dao.setName(dto.name);
        dao.setBrand(dto.brand);
        dao.setDescription(dto.description);
        dao.setColor(dto.color);
        dao.setHeightMm(dto.heightMm);
        dao.setWidthMm(dto.widthMm);
        dao.setLengthMm(dto.lengthMm);
        dao.setWeightGrams(dto.weightGrams);
        dao.setPrizeEuro(dto.prizeEuro);
        return dao;
    }

    private static ProductDto convert(ProductDao dao) {
        return new ProductDto(
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
    }





}
