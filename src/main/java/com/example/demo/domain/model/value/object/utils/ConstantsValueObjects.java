package com.example.demo.domain.model.value.object.utils;

public class ConstantsValueObjects {
    private ConstantsValueObjects(){ throw new IllegalStateException("Utility class"); }

    public static final String DATE_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String REGEX_REMOVE_SPACES_AND_DASHESH = "[\\s-]+";
}
