package com.example.demo.domain.model.value.object;

import com.example.demo.domain.model.Category;

public class CategoryAttribute {
    private Id id;
    private Category category;
    private DefinitionAttribute definitionAttribute;

    public CategoryAttribute(Id id, Category category, DefinitionAttribute definitionAttribute) {
        this.id = id;
        this.category = category;
        this.definitionAttribute = definitionAttribute;
    }
    public CategoryAttribute(Category category, DefinitionAttribute definitionAttribute) {
        this.category = category;
        this.definitionAttribute = definitionAttribute;
    }

    public CategoryAttribute(){}

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public DefinitionAttribute getDefinitionAttribute() {
        return definitionAttribute;
    }

    public void setDefinitionAttribute(DefinitionAttribute definitionAttribute) {
        this.definitionAttribute = definitionAttribute;
    }

}
