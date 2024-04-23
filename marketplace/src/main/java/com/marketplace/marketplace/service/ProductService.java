package com.marketplace.marketplace.service;

import com.marketplace.marketplace.DTO.ProductDTO;
import com.marketplace.marketplace.mapper.ProductMapper;
import com.marketplace.marketplace.model.ProductEntity;
import com.marketplace.marketplace.repository.IProductRepository;
import com.marketplace.marketplace.validation.ProductValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.List;
import java.util.Optional;

@ControllerAdvice
@Service
public class ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private IProductRepository productRepository;

     public ProductDTO createProduct(ProductDTO productDTO){
         ProductEntity productEntity  = productMapper.mapDtoToEntity(productDTO);
         ProductValidation.productPresentValidation(productRepository.findById(productEntity.getId()), productEntity.getId());
         ProductEntity saveProduct = productRepository.save(productEntity);
         return productMapper.mapEntityToDto(saveProduct);
     }

     public ProductDTO productById(Long id){
         Optional<ProductEntity> product = productRepository.findById(id);
         ProductValidation.productEmptyValidation(product);
         return productMapper.mapEntityToDto(product.get());
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
