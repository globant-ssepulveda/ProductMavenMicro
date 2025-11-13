package product.micro.application.handler;

import product.micro.application.dto.ProductRequest;
import product.micro.application.dto.ProductResponse;
import product.micro.domain.models.Product;

import java.util.List;
import java.util.Optional;

public interface IProductHandler {
    ProductResponse createProduct(ProductRequest productRequest);
    void updateProduct(String uuid, ProductRequest productRequest);
    void deleteProduct(String id);
    Optional<ProductResponse> getProductByUuid(String uuid);
    List<ProductResponse> getAllProducts();
    List<ProductResponse> searchProductsByFantasyName(String keyword);
}
