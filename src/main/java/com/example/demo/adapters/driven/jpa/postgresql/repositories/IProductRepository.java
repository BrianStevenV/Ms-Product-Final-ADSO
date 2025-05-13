package com.example.demo.adapters.driven.jpa.postgresql.repositories;

import com.example.demo.adapters.driven.jpa.postgresql.entities.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends JpaRepository<ProductEntity, Long> {
    @Query("SELECT p FROM ProductEntity p WHERE p.productInfo.userProviderId = :userProviderId")
    Page<ProductEntity> findAllByProductInfo_UserProviderId(@Param("userProviderId") Long userProviderId, Pageable pageable);

}
