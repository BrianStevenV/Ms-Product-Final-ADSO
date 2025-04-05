package com.example.demo.adapters.driven.jpa.postgresql.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "product")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "discount_id")
    private Long discountId;

    @ManyToOne
    @JoinColumn(name = "product_info_id", nullable = false)
    private ProductInfoEntity productInfo;

    @ManyToOne
    @JoinColumn(name = "product_inventory_id", nullable = false)
    private ProductInventoryEntity productInventory;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity category;




    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductAttributesEntity> attributes;

}
