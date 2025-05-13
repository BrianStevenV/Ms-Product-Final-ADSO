package com.example.demo.adapters.driving.http.controller;

import com.example.demo.adapters.driving.http.dto.request.CreateProductAttributeRequestDto;
import com.example.demo.adapters.driving.http.handler.IProductAttributeHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.demo.adapters.driving.http.controller.utils.ProductAttributeRestControllerConstants.POST_PRODUCT_ATTRIBUTE_CREATE;
import static com.example.demo.adapters.driving.http.controller.utils.ProductAttributeRestControllerConstants.PRODUCT_ATTRIBUTE_CONTROLLER_REQUEST_MAPPING;

@RestController
@RequestMapping(PRODUCT_ATTRIBUTE_CONTROLLER_REQUEST_MAPPING)
@RequiredArgsConstructor
public class ProductAttributeRestController {
    private final IProductAttributeHandler productAttributeHandler;

    @PreAuthorize("hasAuthority('PROVIDER')")
    @PostMapping(POST_PRODUCT_ATTRIBUTE_CREATE)
    public ResponseEntity<Void> createProductAttribute(@Valid @RequestBody CreateProductAttributeRequestDto createProductAttributeRequestDto){
        productAttributeHandler.createProductAttribute(createProductAttributeRequestDto);
        return ResponseEntity.ok().build();
    }
}
