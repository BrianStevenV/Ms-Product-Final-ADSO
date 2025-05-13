package com.example.demo.adapters.driven.jpa.postgresql.mappers;

import com.example.demo.adapters.driven.jpa.postgresql.entities.CategoryEntity;
import com.example.demo.adapters.driven.jpa.postgresql.entities.DefinitionAttributesEntity;
import com.example.demo.adapters.driven.jpa.postgresql.entities.ProductAttributesEntity;
import com.example.demo.adapters.driven.jpa.postgresql.entities.ProductEntity;
import com.example.demo.adapters.driven.jpa.postgresql.entities.ProductInfoEntity;
import com.example.demo.adapters.driven.jpa.postgresql.entities.ProductInventoryEntity;
import com.example.demo.domain.model.Category;
import com.example.demo.domain.model.Product;
import com.example.demo.domain.model.value.object.CreationDate;
import com.example.demo.domain.model.value.object.DefinitionAttribute;
import com.example.demo.domain.model.value.object.DeletionDate;
import com.example.demo.domain.model.value.object.Discount;
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
        if(productEntity == null) {
            return null;
        }
        List<ProductAttribute> attributes = productEntity.getAttributes().stream()
                .map(attributeEntity -> {

                    DefinitionAttribute definitionAttribute = new DefinitionAttribute(
                            new Id(attributeEntity.getDefinitionAttributes().getId()),
                            attributeEntity.getDefinitionAttributes().getNameAttribute(),
                            attributeEntity.getDefinitionAttributes().getTypeDataOfValueAttribute()
                    );
                    return new ProductAttribute(
                            new Id(attributeEntity.getId()),
                            definitionAttribute,
                            attributeEntity.getValueAttribute()
                    );
                })
                .collect(Collectors.toList());

//                Product product = new Product(
//                        new Id(productEntity.getId()),
//                        productEntity.getProductInfo().getTitle(),
//                        productEntity.getProductInfo().getDescription(),
//                        new Money(productEntity.getProductInfo().getPrice()),
//
//                        (toCategory(productEntity.getCategory())),
//                        new Inventory(productEntity.getProductInventory().getQuantity(), productEntity.getProductInventory().getIsActive()),
//                        attributes,
//                        productEntity.getProductInfo().getUserProviderId()
//                );
        Product product = new Product(
                productEntity.getId() != null ? new Id(productEntity.getId()) : null,
                productEntity.getProductInfo() != null ? productEntity.getProductInfo().getTitle() : null,
                productEntity.getProductInfo() != null ? productEntity.getProductInfo().getDescription() : null,
                productEntity.getProductInfo() != null && productEntity.getProductInfo().getPrice() != null ? new Money(productEntity.getProductInfo().getPrice()) : null,
                (toCategory(productEntity.getCategory())),
                productEntity.getDiscountId() != null ? new Discount(new Id(productEntity.getDiscountId()), null) : null,
                (productEntity.getProductInventory() != null && (productEntity.getProductInventory().getQuantity() != null || productEntity.getProductInventory().getIsActive() != null)) ?
                        new Inventory(productEntity.getProductInventory().getQuantity(), productEntity.getProductInventory().getIsActive()) : null,
                attributes,
                productEntity.getProductInfo() != null ? productEntity.getProductInfo().getUserProviderId() : null,
                productEntity.getProductInfo().getCreatedAt() != null ? new CreationDate(productEntity.getProductInfo().getCreatedAt()) : null,
                productEntity.getProductInfo().getUpdatedAt() != null ? new ModificationDate(productEntity.getProductInfo().getUpdatedAt(), new CreationDate(productEntity.getProductInfo().getCreatedAt())) : null,
                productEntity.getProductInfo().getDeletedAt() != null ? new DeletionDate(productEntity.getProductInfo().getDeletedAt(), new CreationDate(productEntity.getProductInfo().getCreatedAt())) : null
        );
                        return product;
    }

    public Product toProductToStockResponseDto(ProductEntity productEntity){
        if(productEntity == null) {
            return null;
        }

        Product product = new Product(
                new Id(productEntity.getId()),
                productEntity.getProductInfo().getTitle(),
                productEntity.getProductInfo().getDescription(),
                new Money(productEntity.getProductInfo().getPrice()),
                (toCategory(productEntity.getCategory())),
                productEntity.getDiscountId() != null ? new Discount(new Id(productEntity.getDiscountId()), null) : null,
                new Inventory(productEntity.getProductInventory().getQuantity(), productEntity.getProductInventory().getIsActive()),
                productEntity.getProductInfo().getUserProviderId(),
                productEntity.getProductInfo().getCreatedAt() != null ? new CreationDate(productEntity.getProductInfo().getCreatedAt()) : null,
                productEntity.getProductInfo().getUpdatedAt() != null ? new ModificationDate(productEntity.getProductInfo().getUpdatedAt(), new CreationDate(productEntity.getProductInfo().getCreatedAt())) : null
        );
        return product;
    }

    public ProductEntity toProductEntity(Product product) {
        if (product == null) {
            return null;
        }

        ProductInfoEntity productInfo = new ProductInfoEntity(
                null,
                product.getName(),
                product.getDescription(),
                product.getPrice().getValue(),
                product.getCreationDate().getValue() != null ? product.getCreationDate().getValue() : null,
                product.getModificationDate() != null ? product.getModificationDate().getValue() : null,
                product.getDeletionDate() != null ? product.getDeletionDate().getValue() : null,
                product.getUserProviderId()
        );

        ProductInventoryEntity productInventory = new ProductInventoryEntity(
                null,
                product.getInventory().getQuantity(),
                product.getInventory().getIsActive(),
                product.getCreationDate().getValue(),
                product.getModificationDate() != null ? product.getModificationDate().getValue() : null,
                product.getDeletionDate() != null ? product.getDeletionDate().getValue() : null
        );

        CategoryEntity categoryEntity = toCategoryEntity(product.getCategory());

        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(product.getId() != null ? product.getId().getValue() : null);
        productEntity.setDiscountId(product.getDiscount() != null ? product.getDiscount().getId().getValue() : null);
        productEntity.setProductInfo(productInfo);
        productEntity.setProductInventory(productInventory);
        productEntity.setCategory(categoryEntity);

        List<ProductAttributesEntity> attributeEntities = product.getAttributes().stream()
                .map(attr -> {
                    ProductAttributesEntity attrEntity = new ProductAttributesEntity();
                    attrEntity.setId(attr.getId() != null ? attr.getId().getValue() : null);
                    attrEntity.setProduct(productEntity); // establecer relación inversa
                    attrEntity.setDefinitionAttributes(new DefinitionAttributesEntity(
                            attr.getDefinitionAttribute().getId().getValue(),
                            attr.getDefinitionAttribute().getNameAttribute(),
                            attr.getDefinitionAttribute().getTypeDataOfValueAttribute()
                    ));
                    attrEntity.setValueAttribute(attr.getValueAttribute());
                    return attrEntity;
                })
                .collect(Collectors.toList());

        productEntity.setAttributes(attributeEntities);

        return productEntity;
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

    private CategoryEntity toCategoryEntity(Category category) {
        if (category == null) {
            return null;
        }
        return new CategoryEntity(
                category.getId().getValue(),
                category.getTitle(),
                category.getDescription(),
                category.getIsActive(),
                category.getCreationDate().getValue(),
                category.getModificationDate() != null ? category.getModificationDate().getValue() : null,
                category.getDeletionDate() != null ? category.getDeletionDate().getValue() : null
        );
    }

}
