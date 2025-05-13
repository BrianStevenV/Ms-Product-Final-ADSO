package com.example.demo.config;

import com.example.demo.adapters.driven.jpa.postgresql.adapters.CategoryAttributesPostgresqlAdapter;
import com.example.demo.adapters.driven.jpa.postgresql.adapters.CategoryPostgresqlAdapter;
import com.example.demo.adapters.driven.jpa.postgresql.adapters.DefinitionAttributesPostgresqlAdapter;
import com.example.demo.adapters.driven.jpa.postgresql.adapters.ProductAttributesPostgresqlAdapter;
import com.example.demo.adapters.driven.jpa.postgresql.adapters.ProductPostgresqlAdapter;
import com.example.demo.adapters.driven.jpa.postgresql.mappers.ICategoryAttributeEntityMapper;
import com.example.demo.adapters.driven.jpa.postgresql.mappers.ICategoryEntityMapper;
import com.example.demo.adapters.driven.jpa.postgresql.mappers.IDefinitionAttributeEntityMapper;
import com.example.demo.adapters.driven.jpa.postgresql.mappers.IProductAttributeEntityMapper;
import com.example.demo.adapters.driven.jpa.postgresql.mappers.ProductEntityCascadeMapper;
import com.example.demo.adapters.driven.jpa.postgresql.repositories.ICategoryAttributeIntRepository;
import com.example.demo.adapters.driven.jpa.postgresql.repositories.ICategoryRepository;
import com.example.demo.adapters.driven.jpa.postgresql.repositories.IDefinitionAttributeRepository;
import com.example.demo.adapters.driven.jpa.postgresql.repositories.IProductAttributeRepository;
import com.example.demo.adapters.driven.jpa.postgresql.repositories.IProductRepository;
import com.example.demo.adapters.driving.http.mappers.PaginationApplicationMapper;
import com.example.demo.config.security.utils.IAuthenticationContextService;
import com.example.demo.domain.api.ICategoryServicePort;
import com.example.demo.domain.api.IProductAttributeServicePort;
import com.example.demo.domain.api.IProductServicePort;
import com.example.demo.domain.spi.ICategoryAttributePersistencePort;
import com.example.demo.domain.spi.ICategoryPersistencePort;
import com.example.demo.domain.spi.IDefinitionAttributePersistencePort;
import com.example.demo.domain.spi.IProductAttributesPersistencePort;
import com.example.demo.domain.spi.IProductPersistencePort;
import com.example.demo.domain.usecase.CategoryUseCase;
import com.example.demo.domain.usecase.ProductAttributeUseCase;
import com.example.demo.domain.usecase.ProductUseCase;
import com.example.demo.domain.usecase.utils.UseCaseUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IProductRepository productRepository;

    private final ICategoryEntityMapper categoryEntityMapper;
    private final ICategoryRepository categoryRepository;

    private final IDefinitionAttributeRepository definitionAttributeRepository;
    private final IDefinitionAttributeEntityMapper definitionAttributeEntityMapper;

    private final IProductAttributeRepository productAttributeRepository;
    private final IProductAttributeEntityMapper productAttributeEntityMapper;

    private final ICategoryAttributeIntRepository categoryAttributeRepository;
    private final ICategoryAttributeEntityMapper categoryAttributeEntityMapper;

    private final IAuthenticationContextService authenticationContextService;

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
    public IProductServicePort productServicePort(){
        return new ProductUseCase(productPersistencePort(), categoryPersistencePort(), definitionAttributePersistencePort(), productAttributesPersistencePort(), useCaseUtils());
    }
    @Bean
    public ICategoryPersistencePort categoryPersistencePort(){
        return new CategoryPostgresqlAdapter(categoryRepository, categoryEntityMapper);
    }
    @Bean
    public ICategoryServicePort categoryServicePort(){
        return new CategoryUseCase(categoryPersistencePort(), categoryAttributePersistencePort());
    }
    @Bean
    public IDefinitionAttributePersistencePort definitionAttributePersistencePort(){
        return new DefinitionAttributesPostgresqlAdapter(definitionAttributeRepository, definitionAttributeEntityMapper);
    }
    @Bean
    public IProductAttributesPersistencePort productAttributesPersistencePort(){
        return new ProductAttributesPostgresqlAdapter(productAttributeRepository, productAttributeEntityMapper);
    }
    @Bean
    public ICategoryAttributePersistencePort categoryAttributePersistencePort(){
        return new CategoryAttributesPostgresqlAdapter(categoryAttributeRepository, categoryAttributeEntityMapper);
    }
    @Bean
    public IProductAttributeServicePort productAttributeServicePort(){
        return new ProductAttributeUseCase(definitionAttributePersistencePort(), categoryAttributePersistencePort(), categoryPersistencePort());
    }
    @Bean
    public UseCaseUtils useCaseUtils(){
        return new UseCaseUtils(authenticationContextService);
    }
}
