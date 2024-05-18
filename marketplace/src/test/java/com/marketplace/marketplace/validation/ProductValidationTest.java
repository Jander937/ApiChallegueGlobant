package com.marketplace.marketplace.validation;

import static org.junit.jupiter.api.Assertions.*;

import com.marketplace.marketplace.DTO.enums.Color;
import com.marketplace.marketplace.DTO.enums.Talle;
import com.marketplace.marketplace.DTO.enums.TypeMarc;
import com.marketplace.marketplace.exception.*;
import com.marketplace.marketplace.model.ProductEntity;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


class ProductValidationTest {
    @Test
    public void productPresentValidation_ShouldThrowProductIdExistException_WhenProductIsPresent() {
        // Arrange
        Long id = 1L;
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(id);
        Optional<ProductEntity> existProduct = Optional.of(productEntity);

        // Act & Assert
        assertThrows(ProductIdExistException.class, () -> ProductValidation.productPresentValidation(existProduct, id),
                "Expected productPresentValidation to throw, but it didn't");
    }

    @Test
    public void productPresentValidation_ShouldNotThrowException_WhenProductIsNotPresent() {
        // Arrange
        Long id = 1L;
        Optional<ProductEntity> existProduct = Optional.empty();

        // Act & Assert
        try {
            ProductValidation.productPresentValidation(existProduct, id);
        } catch (ProductIdExistException ex) {
            throw new AssertionError("Expected productPresentValidation not to throw, but it did", ex);
        }
    }

    @Test
    public void productPresentValidation_ShouldNotThrowException_WhenProductPresentWithDifferentId() {
        // Arrange
        Long productId = 1L;
        Long differentId = 2L;
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(productId);
        Optional<ProductEntity> existProduct = Optional.of(productEntity);

        // Act & Assert
        try {
            ProductValidation.productPresentValidation(existProduct, differentId);
        } catch (ProductIdExistException ex) {
            throw new AssertionError("Expected productPresentValidation not to throw for different id, but it did", ex);
        }
    }
    @Test
    public void productTotalValidation_ShouldThrowException_WhenDescriptionIsNull() {
        // Arrange
        ProductEntity productEntity = new ProductEntity();
        productEntity.setDescription(null);
        productEntity.setPrice(10.0);

        // Act & Assert
        assertThrows(ProductAttributesFormatException.class,
                () -> ProductValidation.productTotalValidation(productEntity),
                "Expected productTotalValidation to throw when description is null, but it didn't");
    }

    @Test
    public void productTotalValidation_ShouldThrowException_WhenPriceIsNull() {
        // Arrange
        ProductEntity productEntity = new ProductEntity();
        productEntity.setDescription("Some description");
        productEntity.setPrice(null);

        // Act & Assert
        assertThrows(ProductAttributesFormatException.class,
                () -> ProductValidation.productTotalValidation(productEntity),
                "Expected productTotalValidation to throw when price is null, but it didn't");
    }

    @Test
    public void productTotalValidation_ShouldThrowException_WhenPriceIsLessThanOrEqualToZero() {
        // Arrange
        ProductEntity productEntity = new ProductEntity();
        productEntity.setDescription("Some description");
        productEntity.setPrice(-1.0); // Test with a negative price

        // Act & Assert
        assertThrows(ProductAttributesFormatException.class,
                () -> ProductValidation.productTotalValidation(productEntity),
                "Expected productTotalValidation to throw when price is less than or equal to zero, but it didn't");
    }

    @Test
    public void productTotalValidation_ShouldNotThrowException_WhenAttributesAreValid() {
        // Arrange
        ProductEntity productEntity = new ProductEntity();
        productEntity.setDescription("Some description");
        productEntity.setPrice(10.0); // Positive price

        // Act & Assert
        assertDoesNotThrow(() -> ProductValidation.productTotalValidation(productEntity),
                "Expected productTotalValidation not to throw when attributes are valid, but it did");
    }

    @Test
    public void productEmptyValidation_ShouldThrowException_WhenProductIsEmpty() {
        // Arrange
        Optional<ProductEntity> product = Optional.empty();

        // Act & Assert
        assertThrows(ProductEmptyException.class,
                () -> ProductValidation.productEmptyValidation(product),
                "Expected productEmptyValidation to throw when product is empty, but it didn't");
    }

    @Test
    public void productEmptyValidation_ShouldNotThrowException_WhenProductIsPresent() {
        // Arrange
        ProductEntity productEntity = new ProductEntity();
        Optional<ProductEntity> product = Optional.of(productEntity);

        // Act & Assert
        assertDoesNotThrow(() -> ProductValidation.productEmptyValidation(product),
                "Expected productEmptyValidation not to throw when product is present, but it did");
    }
    @Test
    public void productEqualValidation_ShouldThrowException_WhenProductsAreEqual() {
        // Arrange
        ProductEntity existingProductEntity = new ProductEntity();
        existingProductEntity.setNameProduct("Product Name");
        existingProductEntity.setImage("image.jpg");
        existingProductEntity.setDescription("Description");
        existingProductEntity.setPrice(100.0);
        existingProductEntity.setTypeMarc(TypeMarc.NIKE);
        existingProductEntity.setTalle(Talle.TALLE_45);
        existingProductEntity.setOffer(false);
        existingProductEntity.setColor(Color.BLACK);

        ProductEntity newProductEntity = new ProductEntity();
        newProductEntity.setNameProduct("Product Name");
        newProductEntity.setImage("image.jpg");
        newProductEntity.setDescription("Description");
        newProductEntity.setPrice(100.0);
        newProductEntity.setTypeMarc(TypeMarc.NIKE);
        newProductEntity.setTalle(Talle.TALLE_45);
        newProductEntity.setOffer(false);
        newProductEntity.setColor(Color.BLACK);

        Optional<ProductEntity> existingProduct = Optional.of(existingProductEntity);

        // Act & Assert
        assertThrows(ProductEqualException.class,
                () -> ProductValidation.productEqualValidation(existingProduct, newProductEntity),
                "Expected productEqualValidation to throw when products are equal, but it didn't");
    }

    @Test
    public void productEqualValidation_ShouldNotThrowException_WhenProductsAreDifferent() {
        // Arrange
        ProductEntity existingProductEntity = new ProductEntity();
        existingProductEntity.setNameProduct("Product Name");
        existingProductEntity.setImage("image.jpg");
        existingProductEntity.setDescription("Description");
        existingProductEntity.setPrice(100.0);
        existingProductEntity.setTypeMarc(TypeMarc.NIKE);
        existingProductEntity.setTalle(Talle.TALLE_45);
        existingProductEntity.setOffer(false);
        existingProductEntity.setColor(Color.BLACK);

        ProductEntity newProductEntity = new ProductEntity();
        newProductEntity.setNameProduct("Different Product Name");
        newProductEntity.setImage("different_image.jpg");
        newProductEntity.setDescription("Different Description");
        newProductEntity.setPrice(200.0);
        newProductEntity.setTypeMarc(TypeMarc.NIKE);
        newProductEntity.setTalle(Talle.TALLE_45);
        newProductEntity.setOffer(true);
        newProductEntity.setColor(Color.BLACK);

        Optional<ProductEntity> existingProduct = Optional.of(existingProductEntity);

        // Act & Assert
        assertDoesNotThrow(() -> ProductValidation.productEqualValidation(existingProduct, newProductEntity),
                "Expected productEqualValidation not to throw when products are different, but it did");
    }
    @Test
    public void productColorValidation_ShouldThrowException_WhenProductListIsEmpty() {
        // Arrange
        List<ProductEntity> productList = Collections.emptyList();

        // Act & Assert
        assertThrows(ProductEmptyException.class,
                () -> ProductValidation.productColorValidation(productList),
                "Expected productColorValidation to throw when product list is empty, but it didn't");
    }

    @Test
    public void productColorValidation_ShouldNotThrowException_WhenProductListIsNotEmpty() {
        // Arrange
        ProductEntity productEntity = new ProductEntity();
        productEntity.setColor(Color.BLACK);
        List<ProductEntity> productList = Collections.singletonList(productEntity);

        // Act & Assert
        assertDoesNotThrow(() -> ProductValidation.productColorValidation(productList),
                "Expected productColorValidation not to throw when product list is not empty, but it did");
    }
    @Test
    public void productValidation_ShouldThrowException_WhenOtherProductIsPresentWithDifferentId() {
        // Arrange
        Long id = 1L;
        ProductEntity otherProductEntity = new ProductEntity();
        otherProductEntity.setId(2L); // Different ID
        Optional<ProductEntity> otherProduct = Optional.of(otherProductEntity);

        // Act & Assert
        assertThrows(ProductExistException.class,
                () -> ProductValidation.productValidation(otherProduct, id),
                "Expected productValidation to throw when other product is present with a different ID, but it didn't");
    }

    @Test
    public void productValidation_ShouldNotThrowException_WhenOtherProductIsEmpty() {
        // Arrange
        Long id = 1L;
        Optional<ProductEntity> otherProduct = Optional.empty();

        // Act & Assert
        assertDoesNotThrow(() -> ProductValidation.productValidation(otherProduct, id),
                "Expected productValidation not to throw when other product is empty, but it did");
    }

    @Test
    public void productValidation_ShouldNotThrowException_WhenOtherProductHasSameId() {
        // Arrange
        Long id = 1L;
        ProductEntity otherProductEntity = new ProductEntity();
        otherProductEntity.setId(id); // Same ID
        Optional<ProductEntity> otherProduct = Optional.of(otherProductEntity);

        // Act & Assert
        assertDoesNotThrow(() -> ProductValidation.productValidation(otherProduct, id),
                "Expected productValidation not to throw when other product has the same ID, but it did");
    }
}