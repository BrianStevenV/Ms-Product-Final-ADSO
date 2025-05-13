package com.example.demo.adapters.driven.jpa.postgresql.mappers;

import com.example.demo.adapters.driven.jpa.postgresql.entities.CategoryAttributesEntityInt;
import com.example.demo.adapters.driven.jpa.postgresql.entities.CategoryEntity;
import com.example.demo.domain.model.Category;
import com.example.demo.domain.model.value.object.CategoryAttribute;
import com.example.demo.domain.model.value.object.Id;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ICategoryAttributeEntityMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "category", source = "category")
    @Mapping(target = "definitionAttributes", source = "definitionAttribute")
    CategoryAttributesEntityInt toCategoryAttributeEntityInt(CategoryAttribute categoryAttribute);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "category", source = "category")
    @Mapping(target = "definitionAttribute", source = "definitionAttributes")
    CategoryAttribute toCategoryAttribute(CategoryAttributesEntityInt categoryAttributesEntityInt);

    // Mapeo para la entidad Category a objeto de dominio Category
    @Mapping(target = "id", source = "id")
    Category toCategory(CategoryEntity categoryEntity);

    // Mapeo para el objeto de dominio Category a entidad Category
    @Mapping(target = "id", source = "id.value")
    CategoryEntity toCategoryEntity(Category category);

    // Mapeo para el Id value object a Long
    default Long mapIdToLong(Id id) {
        return id != null ? id.getValue() : null;
    }

    // Mapeo para Long a Id value object
    default Id mapLongToId(Long id) {
        return id != null ? new Id(id) : null;
    }
}
