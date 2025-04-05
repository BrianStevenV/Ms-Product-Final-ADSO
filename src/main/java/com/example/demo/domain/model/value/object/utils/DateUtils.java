package com.example.demo.domain.model.value.object.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import static com.example.demo.domain.model.value.object.utils.ConstantsValueObjects.DATE_FORMAT_PATTERN;

public class DateUtils {

    public static String format(LocalDateTime date) {
        if (DATE_FORMAT_PATTERN == null || DATE_FORMAT_PATTERN.isEmpty()) {
            return date.toString();
        }
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT_PATTERN);
        return formatter.format(date);
    }
}
