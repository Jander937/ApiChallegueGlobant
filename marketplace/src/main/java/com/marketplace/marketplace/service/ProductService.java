package com.marketplace.marketplace.service;

import com.marketplace.marketplace.DTO.ProductDTO;
import com.marketplace.marketplace.mapper.ProductMapper;
import com.marketplace.marketplace.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.List;

@ControllerAdvice
@Service
public class ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private IProductRepository productRepository;

     public ProductDTO createProduct(ProductDTO productDTO){
         return productDTO;
     }

     public ProductDTO productById(Long id){
         return null;
     }

     public List<ProductDTO> productGetAll(){
         return null;
     }

     public static ProductDTO updateProduct(Long id, ProductDTO productDTO){
         return null;
     }

     public ProductDTO deleteProduct(Long id){
         return null;
     }
}
