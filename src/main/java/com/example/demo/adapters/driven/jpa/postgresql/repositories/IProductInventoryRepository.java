package com.example.demo.adapters.driven.jpa.postgresql.repositories;

import com.example.demo.adapters.driven.jpa.postgresql.entities.ProductInventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductInventoryRepository extends JpaRepository<ProductInventoryEntity, Long> {
}
