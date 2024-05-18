package com.marketplace.marketplace.repository;

import com.marketplace.marketplace.model.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.marketplace.marketplace.DTO.enums.Color;

import java.util.List;

@Repository
public interface IProductRepository extends CrudRepository<ProductEntity, Long > {
    List<ProductEntity> findByColor(Color color);

}
