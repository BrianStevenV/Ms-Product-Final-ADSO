package com.example.demo.adapters.driving.http.mappers;

import com.example.demo.adapters.driving.http.dto.response.CategoryResponseDto;
import com.example.demo.adapters.driving.http.dto.response.PaginationResponseDto;
import com.example.demo.adapters.driving.http.dto.response.ProductAttributeResponseDto;
import com.example.demo.adapters.driving.http.dto.response.ProductResponseDto;
import com.example.demo.adapters.driving.http.dto.response.StockResponseDto;
import com.example.demo.domain.model.Category;
import com.example.demo.domain.model.CustomPage;
import com.example.demo.domain.model.Product;
import com.example.demo.domain.model.value.object.ProductAttribute;

import java.util.List;
import java.util.stream.Collectors;

public class PaginationApplicationMapper {

    public PaginationResponseDto<ProductResponseDto> toPaginationResponseDtoFromProductResponseDto(CustomPage<Product> customPage) {
        if (customPage == null) {
            return null;
        }

        List<ProductResponseDto> productResponseDtos = customPage.getContent().stream()
                .map(this::toProductResponseDto)
                .collect(Collectors.toList());

        return new PaginationResponseDto<>(
                productResponseDtos,
                customPage.getPageNumber(),
                customPage.getPageSize(),
                customPage.getTotalElements(),
                customPage.getTotalPages(),
                customPage.isFirst(),
                customPage.isLast()
        );
    }

    public PaginationResponseDto<StockResponseDto> toPaginationResponseDtoFromStockResponseDto(CustomPage<Product> customPage){
        if (customPage == null) {
            return null;
        }

        List<StockResponseDto> stockResponseDtos = customPage.getContent().stream()
                .map(product -> new StockResponseDto(
                        product.getId() != null ? product.getId().getValue() : null,
                        product.getName(),
                        product.getPrice() != null ? product.getPrice().getValue() : null,
                        product.getInventory() != null ? product.getInventory().getQuantity() : null,
                        product.getCreationDate().getValue(),
                        product.getModificationDate() != null ? product.getModificationDate().getValue() : null,
                        product.getCategory().getTitle(),
                        product.getInventory().getIsActive() != null ? product.getInventory().getIsActive(): null

                ))
                .collect(Collectors.toList());

        return new PaginationResponseDto<>(
                stockResponseDtos,
                customPage.getPageNumber(),
                customPage.getPageSize(),
                customPage.getTotalElements(),
                customPage.getTotalPages(),
                customPage.isFirst(),
                customPage.isLast()
        );
    }

    public ProductResponseDto toProductResponseDto(Product product) {
        return new ProductResponseDto(
                product.getId() != null ? product.getId().getValue() : null,
                product.getName(),
                product.getDescription(),
                product.getPrice() != null ? product.getPrice().getValue() : null,
                product.getCategory() != null ? toCategoryResponseDto(product.getCategory()) : null,
                product.getAttributes() != null ? product.getAttributes().stream()
                        .map(this::toProductAttributeResponseDto)
                        .collect(Collectors.toList()) : null,
                product.getUserProviderId()
        );
    }

    private CategoryResponseDto toCategoryResponseDto(Category category) {
        return new CategoryResponseDto(
                category.getId() != null ? category.getId().getValue() : null,
                category.getTitle(),
                category.getDescription()
        );
    }

    private ProductAttributeResponseDto toProductAttributeResponseDto(ProductAttribute attribute) {
        return new ProductAttributeResponseDto(
                attribute.getId() != null ? attribute.getId().getValue() : null,
                attribute.getValueAttribute()
        );
    }
}
