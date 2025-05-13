package com.example.demo.domain.model.value.object;


public final class ProductAttribute {
    private Id id;
    private DefinitionAttribute definitionAttribute;
    private String valueAttribute;

    public ProductAttribute(Id id, DefinitionAttribute definitionAttribute, String valueAttribute) {
        this.id = id;
        this.definitionAttribute = definitionAttribute;
        this.valueAttribute = valueAttribute;
    }

    public Id getId() {
        return id;
    }

    public DefinitionAttribute getDefinitionAttribute() {
        return definitionAttribute;
    }

    public String getValueAttribute() {
        return valueAttribute;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public void setDefinitionAttribute(DefinitionAttribute definitionAttribute) {
        this.definitionAttribute = definitionAttribute;
    }

    public void setValueAttribute(String valueAttribute) {
        this.valueAttribute = valueAttribute;
    }

}
