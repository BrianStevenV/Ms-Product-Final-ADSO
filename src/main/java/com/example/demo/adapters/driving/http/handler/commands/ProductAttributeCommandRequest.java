package com.example.demo.adapters.driving.http.handler.commands;

public class ProductAttributeCommandRequest {
    private final Long definitionAttributeId;
    private final String value;

    public ProductAttributeCommandRequest(Long definitionAttributeId, String value) {
        this.definitionAttributeId = definitionAttributeId;
        this.value = value;
    }

    public Long getDefinitionAttributeId() {
        return definitionAttributeId;
    }

    public String getValue() {
        return value;
    }
}
