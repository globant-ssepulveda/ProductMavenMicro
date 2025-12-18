package product.micro.application.handler;

import product.micro.application.dto.ProductRequest;
import product.micro.application.dto.ProductResponse;
import product.micro.application.mapper.ProductRequestMapper;
import product.micro.application.mapper.ProductResponseMapper;
import product.micro.domain.api.IProductServicePort;
import product.micro.domain.models.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductHandler implements IProductHandler {

    private final IProductServicePort productServicePort;
    private final ProductRequestMapper productRequestMapper;
    private final ProductResponseMapper productResponseMapper;

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = productRequestMapper.toProduct(productRequest);
        productServicePort.createProduct(product);
        return productResponseMapper.toResponse(product);
    }

    @Override
    public void updateProduct(String uuid, ProductRequest productRequest) {
        Product product = productRequestMapper.toProduct(productRequest);
        product.setUuid(uuid);
        productServicePort.updateProduct(product);
    }

    @Override
    public void deleteProduct(String id) {
        productServicePort.deleteProduct(id);
    }

    @Override
    public Optional<ProductResponse> getProductByUuid(String uuid) {
        return productServicePort.getProductByUuid(uuid)
                .map(productResponseMapper::toResponse);
    }


    @Override
    public List<ProductResponse> getAllProducts() {
        return productServicePort.getAllProducts()
                .stream()
                .map(productResponseMapper::toResponse)
                .toList();
    }

    @Override
    public List<ProductResponse> searchProductsByFantasyName(String keyword) {
        List <Product> products = productServicePort.searchProductsByFantasyName(keyword);
        return products.stream()
                .map(productResponseMapper::toResponse)
                .toList();

    }
}
