package com.example.demo.adapters.driven.jpa.postgresql.adapters;

import com.example.demo.adapters.driven.jpa.postgresql.entities.ProductEntity;
import com.example.demo.adapters.driven.jpa.postgresql.mappers.ProductEntityCascadeMapper;
import com.example.demo.adapters.driven.jpa.postgresql.repositories.IProductRepository;
import com.example.demo.domain.model.CustomPage;
import com.example.demo.domain.model.Product;
import com.example.demo.domain.spi.IProductPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import static com.example.demo.adapters.driven.jpa.postgresql.adapters.utils.PostgresqlAdapterMethodsUtils.createCustomPage;

@RequiredArgsConstructor
public class ProductPostgresqlAdapter implements IProductPersistencePort {
    private final IProductRepository productRepository;
    private final ProductEntityCascadeMapper productEntityCascadeMapper;

    @Override
    public void saveProduct(Product product) {
        System.out.println("From Persistence Product: " + product.getCreationDate().getValue());
        ProductEntity productEntity = productEntityCascadeMapper.toProductEntity(product);
        System.out.println("ProductEntity: " + productEntity);
        productRepository.save(productEntity);
    }

    @Override
    public Optional<Product> findById(long productId) {
        return productRepository.findById(productId)
                .map(productEntityCascadeMapper::toProduct);
    }

    @Override
    public CustomPage<Product> getProductFeed() {
        Pageable pageable = PageRequest.of(0,20);
        Page<ProductEntity> productEntityPage = productRepository.findAll(pageable);
        return createCustomPage(productEntityPage,
                productEntityCascadeMapper::toProduct);
    }

    @Override
    public CustomPage<Product> getAllProductsByProviderId(Long userProviderId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ProductEntity> productEntityPage = productRepository.findAllByProductInfo_UserProviderId(userProviderId, pageable);
        return createCustomPage(productEntityPage,
                productEntityCascadeMapper::toProductToStockResponseDto);
    }

}
