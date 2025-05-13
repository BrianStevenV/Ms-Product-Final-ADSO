package com.example.demo.adapters.driving.http.handler;

import com.example.demo.adapters.driving.http.dto.response.CategoryAttributeResponseDto;
import com.example.demo.adapters.driving.http.dto.response.CategoryResponseDto;

import java.util.List;

public interface ICategoryHandler {

    List<CategoryResponseDto> getAllCategories();
    List<CategoryAttributeResponseDto> getAllCategoryAttributesByCategoryId(long categoryId);
}
