package com.marketplace.marketplace.mapper;

import com.marketplace.marketplace.DTO.ProductDTO;
import com.marketplace.marketplace.DTO.enums.Type;
import com.marketplace.marketplace.DTO.enums.TypeProduct;
import com.marketplace.marketplace.model.ProductEntity;
import jakarta.persistence.Column;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public ProductEntity mapDtoToEntity(ProductDTO productDTO){
        return ProductEntity.builder()
                .nameProduct(productDTO.getNameProduct())
                .image(productDTO.getImage())
                .description(productDTO.getDescription())
                .price(productDTO.getPrice())
                .amount(productDTO.getAmount())
                .typeProduct(productDTO.getTypeProduct())
                .type(productDTO.getType())
                .build();
    }

    public ProductDTO mapEntityToDto(ProductEntity productEntity){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setNameProduct(productEntity.getNameProduct());
        productDTO.setImage(productEntity.getImage());
        productDTO.setDescription(productEntity.getDescription());
        productDTO.setPrice(productEntity.getPrice());
        productDTO.setAmount(productEntity.getAmount());
        productDTO.setTypeProduct(productEntity.getTypeProduct());
        productDTO.setType(productEntity.getType());
        return productDTO;
    }
}


