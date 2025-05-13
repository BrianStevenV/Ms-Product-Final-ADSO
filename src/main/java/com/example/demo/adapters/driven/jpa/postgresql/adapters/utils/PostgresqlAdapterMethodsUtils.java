package com.example.demo.adapters.driven.jpa.postgresql.adapters.utils;

import com.example.demo.domain.model.CustomPage;
import com.example.demo.domain.model.Product;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    public static CustomPage<Product> adaptNativeProductPage(
            Page<Object[]> resultsPage,
            Function<Long, Product> findProductById
    ) {
        List<Product> products = resultsPage.getContent().stream()
                .map(result -> {
                    Long productId = (Long) result[0];
                    return findProductById.apply(productId);
                })
                .collect(Collectors.toList());

        CustomPage custom = new CustomPage<>(
                products,
                resultsPage.getNumber(),
                resultsPage.getSize(),
                resultsPage.getTotalElements(),
                resultsPage.getTotalPages(),
                resultsPage.isFirst(),
                resultsPage.isLast()
        );

        return custom;
    }
}
