package com.example.demo.config;

import com.example.demo.adapters.driven.jpa.postgresql.adapters.ProductPostgresqlAdapter;
import com.example.demo.adapters.driven.jpa.postgresql.mappers.ProductEntityCascadeMapper;
import com.example.demo.adapters.driven.jpa.postgresql.repositories.IProductRepository;
import com.example.demo.adapters.driving.http.mappers.PaginationApplicationMapper;
import com.example.demo.domain.api.IProductServicePort;
import com.example.demo.domain.spi.IProductPersistencePort;
import com.example.demo.domain.usecase.ProductUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IProductRepository productRepository;

    @Bean
    public ProductEntityCascadeMapper productEntityCascadeMapper(){
        return new ProductEntityCascadeMapper();
    }
    @Bean
    public PaginationApplicationMapper paginationApplicationMapper(){
        return new PaginationApplicationMapper();
    }
    @Bean
    public IProductPersistencePort productPersistencePort(){
        return new ProductPostgresqlAdapter(productRepository, productEntityCascadeMapper());
    }
    @Bean
    public IProductServicePort productServicePort(IProductPersistencePort productPersistencePort){
        return new ProductUseCase(productPersistencePort());
    }
}
