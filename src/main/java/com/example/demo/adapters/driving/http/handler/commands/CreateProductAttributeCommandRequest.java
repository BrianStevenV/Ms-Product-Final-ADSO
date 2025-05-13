package com.example.demo.adapters.driving.http.handler.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class CreateProductAttributeCommandRequest {
    private final String name;
    private final String type;
    private final List<Long> categoriesId;
}
