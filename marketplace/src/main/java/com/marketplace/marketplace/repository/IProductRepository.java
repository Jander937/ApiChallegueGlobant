package com.marketplace.marketplace.repository;

import com.marketplace.marketplace.model.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.marketplace.marketplace.DTO.enums.Color;

import java.awt.*;
import java.util.List;

@Repository
public interface IProductRepository extends CrudRepository<ProductEntity, Long > {
//    Optional<ProductEntity> findById(Long id);
    List<ProductEntity> findByColor(Color color);
        //Iterable<ProductEntity> findAll();
}
