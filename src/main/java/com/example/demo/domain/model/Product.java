package com.example.demo.domain.model;

import com.example.demo.domain.model.value.object.CreationDate;
import com.example.demo.domain.model.value.object.DeletionDate;
import com.example.demo.domain.model.value.object.Discount;
import com.example.demo.domain.model.value.object.Id;
import com.example.demo.domain.model.value.object.Inventory;
import com.example.demo.domain.model.value.object.ModificationDate;
import com.example.demo.domain.model.value.object.Money;
import com.example.demo.domain.model.value.object.ProductAttribute;

import java.util.List;

public class Product {
    private Id id;

    private String name;
    private String description;
    private Money price;

    private Category category;
    private Discount discount;

    private Inventory inventory;

    private List<ProductAttribute> attributes;
    private Long userProviderId;

    private CreationDate creationDate;
    private ModificationDate modificationDate;
    private DeletionDate deletionDate;

    public Product(Id id, String name, String description, Money price, Category category, Discount discount, Inventory inventory, List<ProductAttribute> attributes, Long userProviderId, CreationDate creationDate, ModificationDate modificationDate, DeletionDate deletionDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.discount = discount;
        this.inventory = inventory;
        this.attributes = attributes;
        this.userProviderId = userProviderId;
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
        this.deletionDate = deletionDate;
    }
    public Product(Id id, String name, String description, Money price, Category category, Inventory inventory, List<ProductAttribute> attributes, Long userProviderId, CreationDate creationDate, ModificationDate modificationDate, DeletionDate deletionDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.inventory = inventory;
        this.attributes = attributes;
        this.userProviderId = userProviderId;
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
        this.deletionDate = deletionDate;
    }

    public Product(Id id, String name, String description, Money price, Category category, Inventory inventory, List<ProductAttribute> attributes, Long userProviderId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.inventory = inventory;
        this.attributes = attributes;
        this.userProviderId = userProviderId;
    }
    public Product(Id id, String name, String description, Money price, Category category, Inventory inventory, List<ProductAttribute> attributes) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.inventory = inventory;
        this.attributes = attributes;
    }

    public Product(Id id, String name, String description, Money price, Category category, Discount discount, Inventory inventory, Long userProviderId, CreationDate creationDate, ModificationDate modificationDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.discount = discount;
        this.inventory = inventory;
        this.userProviderId = userProviderId;
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
    }

    public Product(){}

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Money getPrice() {
        return price;
    }

    public void setPrice(Money price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public List<ProductAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<ProductAttribute> attributes) {
        this.attributes = attributes;
    }

    public Long getUserProviderId() {
        return userProviderId;
    }

    public void setUserProviderId(Long userProviderId) {
        this.userProviderId = userProviderId;
    }

    public CreationDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(CreationDate creationDate) {
        this.creationDate = creationDate;
    }

    public ModificationDate getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(ModificationDate modificationDate) {
        this.modificationDate = modificationDate;
    }

    public DeletionDate getDeletionDate() {
        return deletionDate;
    }

    public void setDeletionDate(DeletionDate deletionDate) {
        this.deletionDate = deletionDate;
    }
}
