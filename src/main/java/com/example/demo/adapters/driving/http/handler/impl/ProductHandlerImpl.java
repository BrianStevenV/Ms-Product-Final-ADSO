package com.example.demo.adapters.driving.http.handler.impl;

import com.example.demo.adapters.driving.http.dto.response.PaginationResponseDto;
import com.example.demo.adapters.driving.http.dto.response.ProductResponseDto;
import com.example.demo.adapters.driving.http.handler.IProductHandler;
import com.example.demo.adapters.driving.http.mappers.PaginationApplicationMapper;
import com.example.demo.domain.api.IProductServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductHandlerImpl implements IProductHandler {
    private final IProductServicePort productServicePort;
    private final PaginationApplicationMapper paginationApplicationMapper;
    @Override
    public PaginationResponseDto<ProductResponseDto> getProductFeed() {
        productServicePort.getProductFeed();
        return paginationApplicationMapper.toPaginationResponseDtoFromProductResponseDto(
                productServicePort.getProductFeed());
    }
}
