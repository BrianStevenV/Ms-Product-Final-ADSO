package com.example.demo.adapters.driving.http.controller;

import com.example.demo.adapters.driving.http.dto.request.AddProductQuantityInventoryRequestDto;
import com.example.demo.adapters.driving.http.dto.request.CreateProductRequestDto;
import com.example.demo.adapters.driving.http.dto.response.PaginationResponseDto;
import com.example.demo.adapters.driving.http.dto.response.ProductResponseDto;
import com.example.demo.adapters.driving.http.dto.response.StockResponseDto;
import com.example.demo.adapters.driving.http.handler.IProductHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.demo.adapters.driving.http.controller.utils.ProductRestControllerConstants.FREE_GET_FEEDBACK_PRODUCT;
import static com.example.demo.adapters.driving.http.controller.utils.ProductRestControllerConstants.GET_ALL_PRODUCTS_BY_PROVIDER_ID;
import static com.example.demo.adapters.driving.http.controller.utils.ProductRestControllerConstants.GET_PRODUCT_BY_ID;
import static com.example.demo.adapters.driving.http.controller.utils.ProductRestControllerConstants.PATCH_ADD_PRODUCT_QUANTITY;
import static com.example.demo.adapters.driving.http.controller.utils.ProductRestControllerConstants.PATH_VARIABLE_PRODUCT_ID;
import static com.example.demo.adapters.driving.http.controller.utils.ProductRestControllerConstants.POST_DISABLE_PRODUCT;
import static com.example.demo.adapters.driving.http.controller.utils.ProductRestControllerConstants.POST_PRODUCT_CREATE;
import static com.example.demo.adapters.driving.http.controller.utils.ProductRestControllerConstants.PRODUCT_CONTROLLER_REQUEST_MAPPING;

@RestController
@RequiredArgsConstructor
@RequestMapping(PRODUCT_CONTROLLER_REQUEST_MAPPING)
public class ProductRestController {
    private final IProductHandler productHandler;

    @GetMapping(FREE_GET_FEEDBACK_PRODUCT)
    public PaginationResponseDto<ProductResponseDto> getProductFeed() {
        return productHandler.getProductFeed();
    }

    @PreAuthorize("hasAuthority('PROVIDER')")
    @PostMapping(POST_PRODUCT_CREATE)
    public ResponseEntity<Void> createProduct(@Valid @RequestBody CreateProductRequestDto createProductRequestDto) {
        productHandler.createProduct(createProductRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize("hasAuthority('PROVIDER')")
    @PostMapping(POST_DISABLE_PRODUCT)
    public ResponseEntity<Void> disableProduct(@PathVariable(PATH_VARIABLE_PRODUCT_ID) Long productId) {
        System.out.println("disableProduct");
        productHandler.disableProduct(productId);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAuthority('PROVIDER')")
    @GetMapping(GET_ALL_PRODUCTS_BY_PROVIDER_ID)
    public PaginationResponseDto<StockResponseDto> getAllStockByProviderId(){
        return productHandler.getAllStockByProviderId();
    }
    @PreAuthorize("hasAuthority('PROVIDER')")
    @PatchMapping(PATCH_ADD_PRODUCT_QUANTITY)
    public ResponseEntity<Void> addProductQuantity(@Valid @RequestBody AddProductQuantityInventoryRequestDto addProductQuantityInventoryRequestDto) {
        productHandler.addProductQuantity(addProductQuantityInventoryRequestDto);
        return ResponseEntity.ok().build();
    }
    @PreAuthorize("hasAuthority('PROVIDER')")
    @GetMapping(GET_PRODUCT_BY_ID)
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable(PATH_VARIABLE_PRODUCT_ID) Long productId) {
        // Este endpoint es cuando se vaya a actualizar un proucto, cargar la tabla con todos los datos listos.
        // Verificar que producto corresponda a su id de proveedor.
        return ResponseEntity.ok(productHandler.getProductById(productId));
    }

    //TODO: Falta metodo Update para actualizar un producto.
}
