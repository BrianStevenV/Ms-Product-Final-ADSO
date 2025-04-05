package com.example.demo.adapters.driven.jpa.postgresql.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "definition_attributes")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DefinitionAttributesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameAttribute;
    private String typeData;
}
