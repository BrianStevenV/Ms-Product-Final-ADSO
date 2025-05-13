package com.example.demo.adapters.driven.jpa.postgresql.mappers;

import com.example.demo.adapters.driven.jpa.postgresql.entities.ProductAttributesEntity;
import com.example.demo.domain.model.value.object.Id;
import com.example.demo.domain.model.value.object.ProductAttribute;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IProductAttributeEntityMapper {

    ProductAttribute toProductAttribute(ProductAttributesEntity productAttributesEntity);

    ProductAttributesEntity toProductAttributeEntity(ProductAttribute productAttribute);

    default Long mapIdToLong(Id id) {
        return id != null ? id.getValue() : null;
    }

    default Id mapLongToId(Long id) {
        return id != null ? new Id(id) : null;
    }
}
