package com.example.demo.adapters.driving.http.mappers;

import com.example.demo.adapters.driving.http.dto.response.CategoryResponseDto;
import com.example.demo.domain.model.Category;
import com.example.demo.domain.model.value.object.Id;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ICategoryRequestMapper {

    CategoryResponseDto toCategoryResponseDto(Category category);

    List<CategoryResponseDto> toListCategoryResponseDto(List<Category> categories);

    default Long mapIdToLong(Id id) {
        return id != null ? id.getValue() : null;
    }

    default Id mapLongToId(Long id) {
        return id != null ? new Id(id) : null;
    }
}
