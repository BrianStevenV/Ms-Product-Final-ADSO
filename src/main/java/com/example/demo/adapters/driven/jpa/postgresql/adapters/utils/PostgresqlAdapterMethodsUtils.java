package com.example.demo.adapters.driven.jpa.postgresql.adapters.utils;

import com.example.demo.domain.model.CustomPage;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.function.Function;

public class PostgresqlAdapterMethodsUtils {
    public static <T, E> CustomPage<T> createCustomPage(Page<E> page, Function<E, T> mapEntityToDomain) {
        List<T> content = page.getContent()
                .stream()
                .map(mapEntityToDomain)
                .toList();

        return new CustomPage<>(
                content,
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isFirst(),
                page.isLast()
        );
    }
}
