package product.micro.application.mapper;

import product.micro.application.dto.ProductRequest;
import product.micro.domain.models.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductRequestMapper {

    Product toProduct(ProductRequest productRequest);

}
