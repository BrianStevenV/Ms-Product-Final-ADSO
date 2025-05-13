package com.example.demo.adapters.driving.http.mappers;

import com.example.demo.adapters.driving.http.dto.request.CreateProductAttributeRequestDto;
import com.example.demo.adapters.driving.http.handler.commands.CreateProductAttributeCommandRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IProductAttributeRequestMapper {
    CreateProductAttributeCommandRequest toCreateProductAttributeCommandRequest(CreateProductAttributeRequestDto createProductAttributeRequestDto);
}
