package com.marketplace.marketplace.validation;

import com.marketplace.marketplace.DTO.enums.Color;
import com.marketplace.marketplace.DTO.enums.Talle;
import com.marketplace.marketplace.DTO.enums.TypeMarc;
import com.marketplace.marketplace.exception.ProductEmptyException;
import com.marketplace.marketplace.exception.ProductEqualException;
import com.marketplace.marketplace.exception.ProductIdExistException;
import com.marketplace.marketplace.model.ProductEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.Optional;

@ControllerAdvice
public class ProductValidation {

    public static void productPresentValidation(Optional<ProductEntity> existProduct, Long id){
        if (existProduct.isPresent())
            throw new ProductIdExistException(String.format("There is a product with the id : %s", id));
    }

    public static void productEmptyValidation(Optional<ProductEntity> product){
        if(product.isEmpty())
            throw new ProductEmptyException("The product doesn't exist");
    }

    public static void productEqualValidation(Optional<ProductEntity> existingProduct, ProductEntity productEntity){
        if (existingProduct.get().getNameProduct().equals(productEntity.getNameProduct()) &&
                existingProduct.get().getImage().equals(productEntity.getImage())&&
                existingProduct.get().getDescription().equals(productEntity.getDescription()) &&
                existingProduct.get().getPrice().equals(productEntity.getPrice()) &&
                existingProduct.get().getTypeMarc().equals(productEntity.getTypeMarc())&&
                existingProduct.get().getTalle().equals(productEntity.getTalle())&&
                existingProduct.get().getOffer() == productEntity.getOffer() &&
                existingProduct.get().getColor().equals(productEntity.getColor())
        ) {
            throw new ProductEqualException("The request failed because the product is equal, it doesn't have different values");
        }
    }


}
