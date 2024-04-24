package com.marketplace.marketplace.repository;

import com.marketplace.marketplace.model.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IProductRepository extends CrudRepository<ProductEntity, Long > {
//    Optional<ProductEntity> findById(Long id);
    //Iterable<ProductEntity> findAll();
}
