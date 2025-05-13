package com.example.demo.config.security.utils;

public class ConstantsSecurity {

    private ConstantsSecurity() { throw new IllegalStateException("Utility class"); }

    public static final String ADMINISTRATOR_ROLE = "ADMINISTRATOR";
    public static final String CUSTOMER_ROLE = "CUSTOMER";
    public static final String PROVIDER_ROLE = "PROVIDER";

    public static final String ERROR_EXTRACTING_ID_FROM_INFRASTRUCTURE_CONTEXT = "Error to get ID from infrastructure context: ";

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_TOKEN = "Bearer ";
    public static final int AUTHORIZATION_HEADER_SUBSTRING = 7;

    public static final String DELIMETER_JOINING_AUTH_GET_AUTHORITIES = ", ";

    public static final int PREFIX_RECURSIVE = 0;
    public static final int PREFIX_RECURSIVE_NEXT = 1;
}
