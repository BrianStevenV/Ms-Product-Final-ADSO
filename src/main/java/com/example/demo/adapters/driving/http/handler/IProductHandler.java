package com.example.demo.adapters.driving.http.handler;

import com.example.demo.adapters.driving.http.dto.request.AddProductQuantityInventoryRequestDto;
import com.example.demo.adapters.driving.http.dto.request.CreateProductRequestDto;
import com.example.demo.adapters.driving.http.dto.response.PaginationResponseDto;
import com.example.demo.adapters.driving.http.dto.response.ProductResponseDto;
import com.example.demo.adapters.driving.http.dto.response.StockResponseDto;

public interface IProductHandler {
    PaginationResponseDto<ProductResponseDto> getProductFeed();
    void createProduct(CreateProductRequestDto createProductRequestDto);
    void disableProduct(Long productId);
    PaginationResponseDto<StockResponseDto> getAllStockByProviderId();
    void addProductQuantity(AddProductQuantityInventoryRequestDto addProductQuantityInventoryRequestDto);
    ProductResponseDto getProductById(Long productId);
}
