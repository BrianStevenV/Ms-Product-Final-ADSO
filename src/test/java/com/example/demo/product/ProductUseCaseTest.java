package com.example.demo.product;

import com.example.demo.adapters.driving.http.handler.commands.ProductAttributeCommandRequest;
import com.example.demo.domain.exceptions.CategoryNotFoundException;
import com.example.demo.domain.exceptions.ProductNotFoundException;
import com.example.demo.domain.model.Category;
import com.example.demo.domain.model.Product;
import com.example.demo.domain.model.value.object.Inventory;
import com.example.demo.domain.model.value.object.ProductAttribute;
import com.example.demo.domain.spi.ICategoryPersistencePort;
import com.example.demo.domain.spi.IProductPersistencePort;
import com.example.demo.domain.usecase.ProductUseCase;
import com.example.demo.domain.usecase.utils.UseCaseUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Nested;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProductUseCaseTest {

    @Mock
    private IProductPersistencePort productPersistencePort;

    @Mock
    private ICategoryPersistencePort categoryPersistencePort;

    @Mock
    private UseCaseUtils useCaseUtils;

    @InjectMocks
    private ProductUseCase productUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should return product when it exists and belongs to the user")
    void shouldReturnProductWhenItExistsAndBelongsToUser() {
        Long productId = 1L;
        Long userProviderId = 13L;
        Product product = new Product();
        product.setUserProviderId(userProviderId);

        when(productPersistencePort.findById(productId)).thenReturn(Optional.of(product));
        doNothing().when(useCaseUtils).validationProductBelongsToUserProviderId(userProviderId, productId);

        Product result = productUseCase.getProductByIdAndValidationUserProviderId(productId);

        assertNotNull(result);
        assertEquals(product, result);
        verify(productPersistencePort).findById(productId);
        verify(useCaseUtils).validationProductBelongsToUserProviderId(userProviderId, productId);
    }

    @Test
    @DisplayName("Should throw ProductNotFoundException when product does not exist")
    void shouldThrowProductNotFoundExceptionWhenProductDoesNotExist() {
        Long productId = 1L;

        when(productPersistencePort.findById(productId)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productUseCase.getProductByIdAndValidationUserProviderId(productId));
        verify(productPersistencePort).findById(productId);
        verifyNoInteractions(useCaseUtils);
    }

    @Test
    @DisplayName("Should disable product and set inventory to inactive")
    void shouldDisableProductAndSetInventoryToInactive() {
        Long productId = 1L;
        Long userProviderId = 13L;
        Product product = new Product();
        product.setUserProviderId(userProviderId);
        product.setInventory(new Inventory(10, true));

        when(productPersistencePort.findById(productId)).thenReturn(Optional.of(product));
        doNothing().when(useCaseUtils).validationProductBelongsToUserProviderId(userProviderId, productId);

        productUseCase.disableProduct(productId);

        assertNotNull(product.getInventory());
        assertFalse(product.getInventory().getIsActive());
        verify(productPersistencePort).findById(productId);
        verify(useCaseUtils).validationProductBelongsToUserProviderId(userProviderId, productId);
        verify(productPersistencePort).saveProduct(product);
    }


    @Test
    @DisplayName("Should throw CategoryNotFoundException when category does not exist")
    void shouldThrowCategoryNotFoundExceptionWhenCategoryDoesNotExist() {
        Long categoryId = 1L;
        Product product = new Product();
        ProductAttributeCommandRequest attributeRequest = new ProductAttributeCommandRequest(1L, "value");
        List<ProductAttributeCommandRequest> attributes = List.of(attributeRequest);

        when(categoryPersistencePort.findById(categoryId)).thenReturn(Optional.empty());

        assertThrows(CategoryNotFoundException.class, () -> productUseCase.createProduct(product, categoryId, attributes));
        verify(categoryPersistencePort).findById(categoryId);
        verifyNoInteractions(productPersistencePort);

    }
}
