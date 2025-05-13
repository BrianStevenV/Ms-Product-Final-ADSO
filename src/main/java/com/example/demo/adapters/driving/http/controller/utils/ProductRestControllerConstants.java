package com.example.demo.adapters.driving.http.controller.utils;

public class ProductRestControllerConstants {
    private ProductRestControllerConstants(){ throw new IllegalStateException("Utility class"); }

    public static final String PRODUCT_CONTROLLER_REQUEST_MAPPING = "/product";
    public static final String POST_PRODUCT_CREATE = "/";
    public static final String GET_ALL_PRODUCTS_BY_PROVIDER_ID = "/provider/";
    public static final String PATCH_ADD_PRODUCT_QUANTITY = "/provider/add-inventory";
    public static final String POST_DISABLE_PRODUCT = "/{productId}";
    public static final String GET_PRODUCT_BY_ID = GET_ALL_PRODUCTS_BY_PROVIDER_ID + POST_DISABLE_PRODUCT;
    public static final String FREE_GET_FEEDBACK_PRODUCT = "/feedback";

    public static final String PATH_VARIABLE_PRODUCT_ID = "productId";
}
