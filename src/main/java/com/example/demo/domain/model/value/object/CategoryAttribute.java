package com.example.demo.domain.model.value.object;

import com.example.demo.domain.model.Category;

public class CategoryAttribute {
    private Long id;
    private Category category;
    private DefinitionAttribute definitionAttribute;

    public CategoryAttribute(Long id, Category category, DefinitionAttribute definitionAttribute) {
        this.id = id;
        this.category = category;
        this.definitionAttribute = definitionAttribute;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
