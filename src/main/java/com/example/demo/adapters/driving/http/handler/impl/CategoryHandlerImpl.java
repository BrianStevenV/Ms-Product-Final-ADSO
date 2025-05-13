package com.example.demo.adapters.driving.http.handler.impl;

import com.example.demo.adapters.driving.http.dto.response.CategoryAttributeResponseDto;
import com.example.demo.adapters.driving.http.dto.response.CategoryResponseDto;
import com.example.demo.adapters.driving.http.handler.ICategoryHandler;
import com.example.demo.adapters.driving.http.mappers.ICategoryAttributeRequestMapper;
import com.example.demo.adapters.driving.http.mappers.ICategoryRequestMapper;
import com.example.demo.domain.api.ICategoryServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryHandlerImpl implements ICategoryHandler {
    private final ICategoryServicePort categoryServicePort;
    private final ICategoryRequestMapper categoryRequestMapper;
    private final ICategoryAttributeRequestMapper categoryAttributeRequestMapper;

    @Override
    public List<CategoryResponseDto> getAllCategories() {
        return categoryRequestMapper.toListCategoryResponseDto(categoryServicePort.getAllCategories());
    }

    @Override
    public List<CategoryAttributeResponseDto> getAllCategoryAttributesByCategoryId(long categoryId) {
        return categoryAttributeRequestMapper.
                toListCategoryAttributeResponseDto(
                        categoryServicePort.getAllCategoryAttributesByCategoryId(categoryId)
                );
    }

}
