package com.example.demo.config.controllerAdvisor.utils;

public class ConstantsExceptions {
    private ConstantsExceptions(){ throw new IllegalStateException("Utility class"); }

    public static final String PRICE_IS_NOT_POSITIVE_EXCEPTION = "Price is not positive";
    public static final String DISCOUNT_PERCENTAGE_IS_NOT_IN_RANGE_EXCEPTION = "Discount percentage must be between 0 and 100";
    public static final String INVENTORY_QUANTITY_IS_NOT_POSITIVE_EXCEPTION = "Inventory quantity cannot be negative.";
    public static final String TYPE_DATA_VALUE_ATTRIBUTE_IS_DIFFERENT_OF_DEFINITION_ATTRIBUTE_EXCEPTION = "Type data value attribute is different of definition attribute, you must check the type of the attribute.";
    public static final String DELETION_DATE_AFTER_CREATION_DATE_EXCEPTION = "Deletion date cannot be before creation date.";
    public static final String MODIFICATION_DATE_AFTER_CREATION_DATE_EXCEPTION = "Modification date cannot be before creation date.";
    public static final String PRODUCT_NOT_FOUND_EXCEPTION = "Product not found";
    public static final String CATEGORY_NOT_FOUND_EXCEPTION = "Category not found";
    public static final String DEFINITION_ATTRIBUTE_NOT_FOUND_EXCEPTION = "Definition attribute not found";
    public static final String DEFINITION_ATTRIBUTE_NOT_FOUND_FOR_ID_EXCEPTION = "Definition attribute not found for id: ";
    public static final String PRODUCT_BELONGS_OTHER_USER_EXCEPTION = "Product belongs to another user. The product: ";

}
