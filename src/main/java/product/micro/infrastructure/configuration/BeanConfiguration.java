package product.micro.infrastructure.configuration;

import product.micro.domain.api.IProductServicePort;
import product.micro.domain.spi.IProductPersistencePort;
import product.micro.domain.usecase.ProductUseCase;
import product.micro.infrastructure.jpa.adapter.ProductJpaAdapter;
import product.micro.infrastructure.jpa.mapper.ProductEntityMapper;
import product.micro.infrastructure.jpa.repository.IProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IProductRepository productRepository;
    private final ProductEntityMapper productEntityMapper;

    @Bean
    public IProductServicePort productServicePort() {
        return new ProductUseCase(productPersistencePort());
    }

    @Bean
    public IProductPersistencePort productPersistencePort() {
        return new ProductJpaAdapter(productRepository, productEntityMapper);
    }

}
