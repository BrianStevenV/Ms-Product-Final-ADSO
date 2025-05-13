package com.example.demo.adapters.driving.http.controller;

import com.example.demo.adapters.driving.http.dto.response.CategoryAttributeResponseDto;
import com.example.demo.adapters.driving.http.dto.response.CategoryResponseDto;
import com.example.demo.adapters.driving.http.handler.ICategoryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.example.demo.adapters.driving.http.controller.utils.CategoryRestControllerConstants.CATEGORY_REQUEST_MAPPING_CONTROLLER;
import static com.example.demo.adapters.driving.http.controller.utils.CategoryRestControllerConstants.GET_ALL_CATEGORIES;
import static com.example.demo.adapters.driving.http.controller.utils.CategoryRestControllerConstants.GET_ALL_CATEGORIES_ATTRIBUTES_ACCORDING_TO_CATEGORY_ID;
import static com.example.demo.adapters.driving.http.controller.utils.CategoryRestControllerConstants.PATH_VARIABLE_GET_ALL_CATEGORIES_ATTRIBUTES_ACCORDING_TO_CATEGORY_ID;

@RestController
@RequestMapping(CATEGORY_REQUEST_MAPPING_CONTROLLER)
@RequiredArgsConstructor
public class CategoryRestController {

    private final ICategoryHandler categoryHandler;

    @GetMapping(GET_ALL_CATEGORIES)
    public ResponseEntity<List<CategoryResponseDto>> getAllCategories(){
        return ResponseEntity.ok(categoryHandler.getAllCategories());
    }

    @PreAuthorize("hasAuthority('PROVIDER')")
    @GetMapping(GET_ALL_CATEGORIES_ATTRIBUTES_ACCORDING_TO_CATEGORY_ID)
    public ResponseEntity<List<CategoryAttributeResponseDto>> getAllCategoryAttributesByCategoryId(
            @PathVariable(PATH_VARIABLE_GET_ALL_CATEGORIES_ATTRIBUTES_ACCORDING_TO_CATEGORY_ID) long categoryId
    ){
        System.out.println("Ingrese al otro Endpoint categoryId = " + categoryId);
        return ResponseEntity.ok(categoryHandler.getAllCategoryAttributesByCategoryId(categoryId));
    }
}
