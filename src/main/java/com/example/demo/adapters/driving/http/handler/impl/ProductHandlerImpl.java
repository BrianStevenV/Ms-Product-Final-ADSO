package com.example.demo.adapters.driving.http.handler.impl;

import com.example.demo.adapters.driving.http.dto.request.AddProductQuantityInventoryRequestDto;
import com.example.demo.adapters.driving.http.dto.request.CreateProductRequestDto;
import com.example.demo.adapters.driving.http.dto.response.PaginationResponseDto;
import com.example.demo.adapters.driving.http.dto.response.ProductResponseDto;
import com.example.demo.adapters.driving.http.dto.response.StockResponseDto;
import com.example.demo.adapters.driving.http.handler.IProductHandler;
import com.example.demo.adapters.driving.http.handler.commands.ProductAttributeCommandRequest;
import com.example.demo.adapters.driving.http.mappers.PaginationApplicationMapper;
import com.example.demo.domain.api.IProductServicePort;
import com.example.demo.domain.model.Product;
import com.example.demo.domain.model.value.object.Inventory;
import com.example.demo.domain.model.value.object.Money;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductHandlerImpl implements IProductHandler {
    private final IProductServicePort productServicePort;
    private final PaginationApplicationMapper paginationApplicationMapper;
    @Override
    public PaginationResponseDto<ProductResponseDto> getProductFeed() {
        return paginationApplicationMapper.toPaginationResponseDtoFromProductResponseDto(
                productServicePort.getProductFeed());
    }

    @Override
    public void createProduct(CreateProductRequestDto createProductRequestDto) {
        Money price = new Money(
                createProductRequestDto.price()
        );
        Inventory inventory = new Inventory(
                createProductRequestDto.amountAboutInventory(),
                true
        );
        ProductAttributeCommandRequest[] attributes = createProductRequestDto.attributes()
                .stream()
                .map(attribute -> new ProductAttributeCommandRequest(
                        attribute.definitionAttributeId(),
                        attribute.valueAttribute()
                ))
                .toArray(ProductAttributeCommandRequest[]::new);

        Product product = new Product(
                null,
                createProductRequestDto.name(),
                createProductRequestDto.description(),
                price,
                null,
                inventory,
                null
        );

        productServicePort.createProduct(product,
                createProductRequestDto.categoryId(),
                List.of(attributes)
        );
    }

    @Override
    public void disableProduct(Long productId) {
        productServicePort.disableProduct(productId);
    }

    @Override
    public PaginationResponseDto<StockResponseDto> getAllStockByProviderId() {
        return paginationApplicationMapper.toPaginationResponseDtoFromStockResponseDto(
                productServicePort.getAllStockByProviderId());
    }

    @Override
    public void addProductQuantity(AddProductQuantityInventoryRequestDto addProductQuantityInventoryRequestDto) {
        productServicePort.addProductQuantity(
                addProductQuantityInventoryRequestDto.productId(),
                addProductQuantityInventoryRequestDto.quantity()
        );
    }

    @Override
    public ProductResponseDto getProductById(Long productId) {
        return paginationApplicationMapper.toProductResponseDto(
                productServicePort.getProductByIdAndValidationUserProviderId(productId)
        );
    }
}
