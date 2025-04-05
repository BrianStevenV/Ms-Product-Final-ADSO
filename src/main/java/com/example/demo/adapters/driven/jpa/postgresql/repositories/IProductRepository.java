package com.example.demo.adapters.driven.jpa.postgresql.repositories;

import com.example.demo.adapters.driven.jpa.postgresql.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends JpaRepository<ProductEntity, Long> {
}
