package com.example.demo.config.controllerAdvisor;

import com.example.demo.domain.exceptions.CategoryNotFoundException;
import com.example.demo.domain.exceptions.DefinitionAttributeNotFound;
import com.example.demo.domain.exceptions.DefinitionAttributeNotFoundForIdException;
import com.example.demo.domain.exceptions.DeletionDateAfterCreationDateException;
import com.example.demo.domain.exceptions.DiscountPercentageIsNotInRangeException;
import com.example.demo.domain.exceptions.InventoryQuantityCannotBeNegativeException;
import com.example.demo.domain.exceptions.ModificationDateAfterCreationDateException;
import com.example.demo.domain.exceptions.PriceIsNotPossitiveException;
import com.example.demo.domain.exceptions.ProductBelongsOtherUserException;
import com.example.demo.domain.exceptions.ProductNotFoundException;
import com.example.demo.domain.exceptions.TypeDataValueAttributeIsDifferentOfDefinitionAttributeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.example.demo.config.controllerAdvisor.utils.ConstantsExceptions.CATEGORY_NOT_FOUND_EXCEPTION;
import static com.example.demo.config.controllerAdvisor.utils.ConstantsExceptions.DEFINITION_ATTRIBUTE_NOT_FOUND_EXCEPTION;
import static com.example.demo.config.controllerAdvisor.utils.ConstantsExceptions.DEFINITION_ATTRIBUTE_NOT_FOUND_FOR_ID_EXCEPTION;
import static com.example.demo.config.controllerAdvisor.utils.ConstantsExceptions.DELETION_DATE_AFTER_CREATION_DATE_EXCEPTION;
import static com.example.demo.config.controllerAdvisor.utils.ConstantsExceptions.DISCOUNT_PERCENTAGE_IS_NOT_IN_RANGE_EXCEPTION;
import static com.example.demo.config.controllerAdvisor.utils.ConstantsExceptions.INVENTORY_QUANTITY_IS_NOT_POSITIVE_EXCEPTION;
import static com.example.demo.config.controllerAdvisor.utils.ConstantsExceptions.MODIFICATION_DATE_AFTER_CREATION_DATE_EXCEPTION;
import static com.example.demo.config.controllerAdvisor.utils.ConstantsExceptions.PRICE_IS_NOT_POSITIVE_EXCEPTION;
import static com.example.demo.config.controllerAdvisor.utils.ConstantsExceptions.PRODUCT_BELONGS_OTHER_USER_EXCEPTION;
import static com.example.demo.config.controllerAdvisor.utils.ConstantsExceptions.PRODUCT_NOT_FOUND_EXCEPTION;
import static com.example.demo.config.controllerAdvisor.utils.ConstantsExceptions.TYPE_DATA_VALUE_ATTRIBUTE_IS_DIFFERENT_OF_DEFINITION_ATTRIBUTE_EXCEPTION;

@ControllerAdvice
public class ProductControllerAdvisor {
    @ExceptionHandler(PriceIsNotPossitiveException.class)
    public ResponseEntity<String> handlePriceIsNotPossitiveException(PriceIsNotPossitiveException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(PRICE_IS_NOT_POSITIVE_EXCEPTION);
    }
    @ExceptionHandler(DiscountPercentageIsNotInRangeException.class)
    public ResponseEntity<String> handleDiscountPercentageIsNotInRangeException(DiscountPercentageIsNotInRangeException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(DISCOUNT_PERCENTAGE_IS_NOT_IN_RANGE_EXCEPTION);
    }
    @ExceptionHandler(InventoryQuantityCannotBeNegativeException.class)
    public ResponseEntity<String> handleInventoryQuantityIsNotPositiveException(InventoryQuantityCannotBeNegativeException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(INVENTORY_QUANTITY_IS_NOT_POSITIVE_EXCEPTION);
    }
    @ExceptionHandler(TypeDataValueAttributeIsDifferentOfDefinitionAttributeException.class)
    public ResponseEntity<String> handleTypeDataValueAttributeIsDifferentOfDefinitionAttributeException(TypeDataValueAttributeIsDifferentOfDefinitionAttributeException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(TYPE_DATA_VALUE_ATTRIBUTE_IS_DIFFERENT_OF_DEFINITION_ATTRIBUTE_EXCEPTION);
    }
    @ExceptionHandler(DeletionDateAfterCreationDateException.class)
    public ResponseEntity<String> handleDeletionDateAfterCreationDateException(DeletionDateAfterCreationDateException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(DELETION_DATE_AFTER_CREATION_DATE_EXCEPTION);
    }
    @ExceptionHandler(ModificationDateAfterCreationDateException.class)
    public ResponseEntity<String> handleModificationDateAfterCreationDateException(ModificationDateAfterCreationDateException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(MODIFICATION_DATE_AFTER_CREATION_DATE_EXCEPTION);
    }
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleProductNotFound(ProductNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(PRODUCT_NOT_FOUND_EXCEPTION);
    }
    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<String> handleCategoryNotFound(CategoryNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(CATEGORY_NOT_FOUND_EXCEPTION);
    }
    @ExceptionHandler(DefinitionAttributeNotFound.class)
    public ResponseEntity<String> handleDefinitionAttributeNotFound(DefinitionAttributeNotFound exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(DEFINITION_ATTRIBUTE_NOT_FOUND_EXCEPTION);
    }
    @ExceptionHandler(DefinitionAttributeNotFoundForIdException.class)
    public ResponseEntity<String> handleDefinitionAttributeNotFoundForId(DefinitionAttributeNotFoundForIdException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(DEFINITION_ATTRIBUTE_NOT_FOUND_FOR_ID_EXCEPTION);
    }
    @ExceptionHandler(ProductBelongsOtherUserException.class)
    public ResponseEntity<String> handleProductBelongsOtherUserException(ProductBelongsOtherUserException exception) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(PRODUCT_BELONGS_OTHER_USER_EXCEPTION);
    }
}
