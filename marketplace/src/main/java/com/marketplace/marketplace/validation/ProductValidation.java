package com.marketplace.marketplace.validation;

import com.marketplace.marketplace.DTO.enums.Color;
import com.marketplace.marketplace.DTO.enums.Talle;
import com.marketplace.marketplace.DTO.enums.TypeMarc;
import com.marketplace.marketplace.exception.*;
import com.marketplace.marketplace.model.ProductEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.Optional;

@ControllerAdvice
public class ProductValidation {

    public static void productPresentValidation(Optional<ProductEntity> existProduct, Long id){
        if (existProduct.isPresent() && existProduct.get().getId().equals(id)) {
            throw new ProductIdExistException(String.format("There is already a product with the id : %s", id));
        }
    }

    public static void productTotalValidation(ProductEntity productEntity){
        if (productEntity.getId() == null ||
                productEntity.getDescription() == null ||
                productEntity.getPrice() == null ||
                productEntity.getPrice() <= 0) {
            throw new ProductAttributesFormatException("Incorrect format or empty attribute");
        }
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

    public static void productIdValidation(Optional<ProductEntity> otherProduct){
        if(otherProduct.isPresent())
            throw new ProductIdExistException("There is a product with the same name in the data base");
    }

    public static void productValidation(Optional<ProductEntity> otherProduct, Long id){
        if(otherProduct.isPresent() && !otherProduct.get().getId().equals(id)) {
            throw new ProductExistException("There is a product with the same name in the database");
        }
    }

}
