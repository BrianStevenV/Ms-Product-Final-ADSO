package com.example.demo.adapters.driven.jpa.postgresql.mappers;

import com.example.demo.adapters.driven.jpa.postgresql.entities.DefinitionAttributesEntity;
import com.example.demo.domain.model.value.object.DefinitionAttribute;
import com.example.demo.domain.model.value.object.Id;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IDefinitionAttributeEntityMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "nameAttribute", source = "nameAttribute")
    DefinitionAttributesEntity toDefinitionAttributesEntity(DefinitionAttribute definitionAttribute);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "nameAttribute", source = "nameAttribute")
    DefinitionAttribute toDefinitionAttributes(DefinitionAttributesEntity definitionAttributesEntity);

    default Long mapIdToLong(Id id) {
        return id != null ? id.getValue() : null;
    }

    default Id mapLongToId(Long id) {
        return id != null ? new Id(id) : null;
    }
}
