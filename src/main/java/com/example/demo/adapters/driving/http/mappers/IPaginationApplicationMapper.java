//package com.example.demo.adapters.driving.http.mappers;
//
//import com.example.demo.adapters.driving.http.dto.response.PaginationResponseDto;
//import com.example.demo.adapters.driving.http.dto.response.ProductResponseDto;
//import com.example.demo.domain.model.CustomPage;
//import com.example.demo.domain.model.Product;
//import com.example.demo.domain.model.value.object.Id;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.Named;
//import org.mapstruct.ReportingPolicy;
//
//@Mapper(componentModel = "spring",
//        unmappedTargetPolicy = ReportingPolicy.IGNORE,
//        unmappedSourcePolicy = ReportingPolicy.IGNORE)
//public interface IPaginationApplicationMapper {
//
//    @Mapping(target = "id", source = "id", qualifiedByName = "mapIdProductToId")
//    PaginationResponseDto<ProductResponseDto>
//    toPaginationResponseDtoFromProductResponseDto(CustomPage<Product> customPageFromProduct);
//    @Named("mapIdProductToId")
//    static Id mapIdProductToId(Long id) {
//        return id != null ? new Id(id) : null;
//    }
//}
