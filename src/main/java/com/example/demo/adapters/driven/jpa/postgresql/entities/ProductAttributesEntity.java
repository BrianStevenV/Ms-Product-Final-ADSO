package com.example.demo.adapters.driven.jpa.postgresql.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Table(name = "product_attributes")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(exclude = "product")
public class ProductAttributesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;

    @ManyToOne
    @JoinColumn(name = "definition_attribute_id", nullable = false)
    private DefinitionAttributesEntity definitionAttributes;

    @Column(name = "value_attribute")
    private String valueAttribute;
}
