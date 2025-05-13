package com.example.demo.config.security.utils;

public class ConstantsRoutesSecurity {
    private ConstantsRoutesSecurity() { throw new IllegalStateException("Utility class"); }

    public static final String PRODUCT_CONTROLLER = "/product";
    public static final String PRODUCT_ATTRIBUTE_CONTROLLER = "/product-attributes";
    public static final String CATEGORY_CONTROLLER = "/category";

    // PRODUCT ENDPOINTS

    public static final String POST_PRODUCT_CREATE = PRODUCT_CONTROLLER + "/";
    public static final String POST_DISABLE_PRODUCT = PRODUCT_CONTROLLER + "/{productId}";
    public static final String GET_ALL_PRODUCTS_BY_PROVIDER_ID = PRODUCT_CONTROLLER + "/provider/";
    public static final String PATCH_ADD_PRODUCT_QUANTITY = PRODUCT_CONTROLLER + "/provider/add-inventory";
    public static final String GET_PRODUCT_BY_ID = GET_ALL_PRODUCTS_BY_PROVIDER_ID + POST_DISABLE_PRODUCT;

    public static final String FREE_GET_FEEDBACK_PRODUCT = PRODUCT_CONTROLLER + "/feedback";


    // PRODUCT ATTRIBUTE ENDPOINTS

    public static final String POST_PRODUCT_ATTRIBUTE_CREATE = PRODUCT_ATTRIBUTE_CONTROLLER + "/";


    // CATEGORY ENDPOINTS

    public static final String GET_ALL_CATEGORIES = CATEGORY_CONTROLLER + "/all";
    public static final String GET_ALL_CATEGORIES_ATTRIBUTES_ACCORDING_TO_CATEGORY_ID = CATEGORY_CONTROLLER + "/{categoryId}/attributes";
}
