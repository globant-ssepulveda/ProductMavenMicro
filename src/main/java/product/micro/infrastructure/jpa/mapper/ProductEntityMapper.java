package product.micro.infrastructure.jpa.mapper;

import product.micro.domain.models.Product;
import product.micro.infrastructure.jpa.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductEntityMapper {


    ProductEntity toEntity(Product product);


    Product toProduct(ProductEntity productEntity);


    List<Product> toProductList(List<ProductEntity> productEntities);
}
