package com.marketplace.marketplace.mapper;

import com.marketplace.marketplace.DTO.ProductDTO;
import com.marketplace.marketplace.model.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public ProductEntity mapDtoToEntity(ProductDTO productDTO){
        return ProductEntity.builder()
                .nameProduct(productDTO.getNameProduct())
                .image(productDTO.getImage())
                .description(productDTO.getDescription())
                .price(productDTO.getPrice())
                .typeMarc(productDTO.getTypeMarc())
                .talle(productDTO.getTalle())
                .offer(productDTO.getOffer())
                .color(productDTO.getColor())
                .build();
    }

    public ProductDTO mapEntityToDto(ProductEntity productEntity){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setNameProduct(productEntity.getNameProduct());
        productDTO.setImage(productEntity.getImage());
        productDTO.setDescription(productEntity.getDescription());
        productDTO.setPrice(productEntity.getPrice());
        productDTO.setTypeMarc(productEntity.getTypeMarc());
        productDTO.setTalle(productEntity.getTalle());
        productDTO.setOffer(productEntity.getOffer());
        productDTO.setColor(productEntity.getColor());
        return productDTO;
    }

}


