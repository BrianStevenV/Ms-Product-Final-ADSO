package com.example.demo.adapters.driven.jpa.postgresql.repositories;

import com.example.demo.adapters.driven.jpa.postgresql.entities.CategoryAttributesEntityInt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICategoryAttributeIntRepository extends JpaRepository<CategoryAttributesEntityInt, Long> {
    List<CategoryAttributesEntityInt> findByCategory_Id(long categoryId);
}
