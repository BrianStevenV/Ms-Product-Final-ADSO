package com.example.demo.adapters.driven.jpa.postgresql.mappers;

import com.example.demo.adapters.driven.jpa.postgresql.entities.CategoryEntity;
import com.example.demo.adapters.driven.jpa.postgresql.entities.ProductEntity;
import com.example.demo.domain.model.Category;
import com.example.demo.domain.model.Product;
import com.example.demo.domain.model.value.object.CreationDate;
import com.example.demo.domain.model.value.object.DefinitionAttribute;
import com.example.demo.domain.model.value.object.DeletionDate;
import com.example.demo.domain.model.value.object.Id;
import com.example.demo.domain.model.value.object.Inventory;
import com.example.demo.domain.model.value.object.ModificationDate;
import com.example.demo.domain.model.value.object.Money;
import com.example.demo.domain.model.value.object.ProductAttribute;

import java.util.List;
import java.util.stream.Collectors;

public class ProductEntityCascadeMapper {
    public ProductEntityCascadeMapper(){};

    public Product toProduct(ProductEntity productEntity){
        System.out.println("Ingrese al EntityMapperCascade");
        if(productEntity == null) {
            return null;
        }

        System.out.println("Pase el IF del Entyty Mapper");

//        List<ProductAttribute> attributes = productEntity.getAttributes().stream()
//                .map(attributeEntity -> {
//                    // Mapear DefinitionAttributesEntity a DefinitionAttribute
//                    DefinitionAttribute definitionAttribute = new DefinitionAttribute(
//                            new Id(attributeEntity.getDefinitionAttributes().getId()),
//                            attributeEntity.getDefinitionAttributes().getNameAttribute(), attributeEntity.getDefinitionAttributes().getTypeData());
//                    )
//
//                    // Crear ProductAttribute con el valor adecuado
//                    return new ProductAttribute(
//                            new Id(attributeEntity.getId()),
//                            definitionAttribute,
//                            attributeEntity.getValueAttribute()
//                    );
//                })
//                .collect(Collectors.toList());

        //TODO REVISAR QUE CUMPLA, CADA PRODUCTO TIENE SU PROPIA LISTA DE PRODUCT ATTRIBUTES, REVISAR QUE COINCIDA EL PRODUCTO CON SUS ATTRIBUTES CORRESPONDIENTES.

        List<ProductAttribute> attributes = productEntity.getAttributes().stream()
                .map(attributeEntity -> {
                    // Mapear DefinitionAttributesEntity a DefinitionAttribute
                    DefinitionAttribute definitionAttribute = new DefinitionAttribute(
                            new Id(attributeEntity.getDefinitionAttributes().getId()),
                            attributeEntity.getDefinitionAttributes().getNameAttribute(),
                            attributeEntity.getDefinitionAttributes().getTypeData()
                    ); // Closing parenthesis added here

                    // Crear ProductAttribute con el valor adecuado
                    return new ProductAttribute(
                            new Id(attributeEntity.getId()),
                            definitionAttribute,
                            attributeEntity.getValueAttribute()
                    );
                })
                .collect(Collectors.toList());

                Product product = new Product(
                        new Id(productEntity.getId()),
                        productEntity.getProductInfo().getTitle(),
                        productEntity.getProductInfo().getDescription(),
                        new Money(productEntity.getProductInfo().getPrice()),

                        (toCategory(productEntity.getCategory())),
                        new Inventory(productEntity.getProductInventory().getQuantity(), productEntity.getProductInventory().getIsActive()),
                        attributes,
                        productEntity.getProductInfo().getUserProviderId()
                );
                        return product;
    }

    private Category toCategory(CategoryEntity categoryEntity) {
        if (categoryEntity == null) {
            return null;
        }
        return new Category(
                new Id(categoryEntity.getId()),
                categoryEntity.getTitle(),
                categoryEntity.getDescription(),
                categoryEntity.getIsActive(),
                new CreationDate(categoryEntity.getCreatedAt()),
                categoryEntity.getUpdatedAt() != null ? new ModificationDate(categoryEntity.getUpdatedAt(), new CreationDate(categoryEntity.getCreatedAt())) : null,
                categoryEntity.getDeletedAt() != null ? new DeletionDate(categoryEntity.getDeletedAt(), new CreationDate(categoryEntity.getCreatedAt())) : null
        );
    }
}
