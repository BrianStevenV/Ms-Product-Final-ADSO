package com.example.demo.domain.model.value.object;

public class DefinitionAttribute {
    private Id id;
    private String nameAttribute;
//    private String T typeDataOfValueAttribute;
    private String typeDataOfValueAttribute;

    public DefinitionAttribute(Id id, String nameAttribute, String typeDataOfValueAttribute) {
        this.id = id;
        this.nameAttribute = nameAttribute;
        this.typeDataOfValueAttribute = typeDataOfValueAttribute;
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public String getNameAttribute() {
        return nameAttribute;
    }

    public void setNameAttribute(String nameAttribute) {
        this.nameAttribute = nameAttribute;
    }

    public String getTypeDataOfValueAttribute() {
        return typeDataOfValueAttribute;
    }

    public void setTypeDataOfValueAttribute(String typeDataOfValueAttribute) {
        this.typeDataOfValueAttribute = typeDataOfValueAttribute;
    }
}
