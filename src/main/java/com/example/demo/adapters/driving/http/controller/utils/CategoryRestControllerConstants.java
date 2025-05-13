package com.example.demo.adapters.driving.http.controller.utils;

public class CategoryRestControllerConstants {
    private CategoryRestControllerConstants(){ throw new IllegalStateException("Utility class"); }

    public final static String CATEGORY_REQUEST_MAPPING_CONTROLLER = "/category";
    public final static String GET_ALL_CATEGORIES = "/all";
    public final static String GET_ALL_CATEGORIES_ATTRIBUTES_ACCORDING_TO_CATEGORY_ID = "/{categoryId}/attributes";

    public final static String PATH_VARIABLE_GET_ALL_CATEGORIES_ATTRIBUTES_ACCORDING_TO_CATEGORY_ID = "categoryId";
}
