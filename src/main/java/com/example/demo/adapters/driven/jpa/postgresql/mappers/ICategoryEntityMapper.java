package com.example.demo.adapters.driven.jpa.postgresql.mappers;

import com.example.demo.adapters.driven.jpa.postgresql.entities.CategoryEntity;
import com.example.demo.domain.model.Category;
import com.example.demo.domain.model.value.object.CreationDate;
import com.example.demo.domain.model.value.object.DeletionDate;
import com.example.demo.domain.model.value.object.Id;
import com.example.demo.domain.model.value.object.ModificationDate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ICategoryEntityMapper {

    @Mapping(target = "id", source = "id", qualifiedByName = "mapCategoryId")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "isActive", source = "isActive")
    @Mapping(target = "creationDate", source = "createdAt", qualifiedByName = "mapCreationDate")
    @Mapping(target = "modificationDate", source = "updatedAt", qualifiedByName = "mapModificationDate")
    @Mapping(target = "deletionDate", source = "deletedAt", qualifiedByName = "mapDeletionDate")
    Category toCategory(CategoryEntity categoryEntity);

    @Named("mapCategoryId")
    default Id mapId(Long id) {
        return new Id(id);
    }

    @Named("mapCreationDate")
    default CreationDate mapCreationDate(LocalDateTime createdAt) {
        return createdAt != null ? new CreationDate(createdAt) : null;
    }

    @Named("mapModificationDate")
    default ModificationDate mapModificationDate(LocalDateTime updatedAt) {
        return updatedAt != null ? new ModificationDate(updatedAt, null) : null;
    }

    @Named("mapDeletionDate")
    default DeletionDate mapDeletionDate(LocalDateTime deletedAt) {
        return deletedAt != null ? new DeletionDate(deletedAt, null) : null;
    }

    @Mapping(target = "id", source = "id.value")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "isActive", source = "isActive")
    @Mapping(target = "createdAt", source = "creationDate.value")
    @Mapping(target = "updatedAt", source = "modificationDate.value")
    @Mapping(target = "deletedAt", source = "deletionDate.value")
    CategoryEntity toCategoryEntity(Category category);
}
