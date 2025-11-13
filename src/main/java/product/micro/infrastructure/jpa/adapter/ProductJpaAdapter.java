package product.micro.infrastructure.jpa.adapter;

import product.micro.domain.exceptions.DuplicateFantasyNameException;
import product.micro.domain.models.Product;
import product.micro.domain.spi.IProductPersistencePort;
import product.micro.infrastructure.exception.NoDataFoundException;
import product.micro.infrastructure.jpa.entity.ProductEntity;
import product.micro.infrastructure.jpa.mapper.ProductEntityMapper;
import product.micro.infrastructure.jpa.repository.IProductRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import static product.micro.utils.ErrorMessages.*;

@RequiredArgsConstructor
public class ProductJpaAdapter implements IProductPersistencePort {

    private final IProductRepository productRepository;
    private final ProductEntityMapper productEntityMapper;


    @Override
    public void createProduct(Product product) {
        if(productRepository.findByFantasyName(product.getFantasyName()).isPresent()){
            throw new DuplicateFantasyNameException(DUPLICATE_FANTASY_NAME_EXCEPTION);
        }
        ProductEntity productEntity = productEntityMapper.toEntity(product);
        productRepository.save(productEntity);
    }

    @Override
    public void updateProduct(Product product) {
        ProductEntity existingProduct = productRepository.findByUuid(product.getUuid())
                .orElseThrow(() -> new NoDataFoundException(NO_UUID_PRODUCT_FOUND_EXCEPTION));

       existingProduct.setFantasyName(product.getFantasyName());
       existingProduct.setCategory(product.getCategory());
       existingProduct.setDescription(product.getDescription());
       existingProduct.setPrice(product.getPrice());
       existingProduct.setAvailable(product.isAvailable());

       productRepository.save(existingProduct);

    }

    @Override
    public void deleteProduct(Product product) {
        ProductEntity existingProduct = productRepository.findByUuid(product.getUuid())
                .orElseThrow(() -> new NoDataFoundException(NO_UUID_PRODUCT_FOUND_EXCEPTION));
        productRepository.delete(existingProduct);
    }

    @Override
    public List<Product> getAllProducts() {
        List<ProductEntity> productEntities = productRepository.findAll();
        if(productEntities.isEmpty()){
            throw new NoDataFoundException(NO_DATA_FOUND_EXCEPTION);
        }
        return productEntityMapper.toProductList(productEntities);
    }

    @Override
    public Optional<Product> getProductByUuid(String uuid) {
        return productRepository.findByUuid(uuid)
                .map(productEntityMapper::toProduct);
    }

    @Override
    public Optional<Product> getProductByFantasyName(String fantasyName) {
        return productRepository.findByFantasyName(fantasyName)
                .map(productEntityMapper::toProduct);
    }

    @Override
    public List<Product> searchProductsByFantasyName(String keyword) {
        List<ProductEntity> productEntities = productRepository.findByFantasyNameContainingIgnoreCase(keyword);
        if(productEntities.isEmpty()){
            throw new NoDataFoundException(NO_DATA_FOUND_EXCEPTION);
        }
        return productEntities.stream()
                .map(productEntityMapper::toProduct)
                .toList();

    }
}
