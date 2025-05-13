package com.example.demo.domain.model;

import com.example.demo.domain.model.value.object.CreationDate;
import com.example.demo.domain.model.value.object.DeletionDate;
import com.example.demo.domain.model.value.object.Id;
import com.example.demo.domain.model.value.object.ModificationDate;

public class Category {
    private Id id;
    private String title;
    private String description;
    private Boolean isActive;
    private CreationDate creationDate;
    private ModificationDate modificationDate;
    private DeletionDate deletionDate;

    public Category(Id id, String title, String description, Boolean isActive, CreationDate creationDate, ModificationDate modificationDate, DeletionDate deletionDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.isActive = isActive;
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
        this.deletionDate = deletionDate;
    }

    public Category(){};

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
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
