package com.marketplace.marketplace.controller;

import com.marketplace.marketplace.DTO.ProductDTO;
import com.marketplace.marketplace.controller.ProductController;
import com.marketplace.marketplace.endPoinst.IProductEndPoint;
import com.marketplace.marketplace.exception.ProductNotFoundException;
import com.marketplace.marketplace.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.awt.*;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import com.marketplace.marketplace.DTO.ProductDTO;
import com.marketplace.marketplace.DTO.enums.Color;
import com.marketplace.marketplace.controller.ProductController;
import com.marketplace.marketplace.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
 class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createProduct_ShouldReturnCreatedProductAndStatusCreated() {
        // Arrange
        ProductDTO productDTO = new ProductDTO();
        productDTO.setNameProduct("Test Product");
        // Set other properties of productDTO as needed

        ProductDTO createdProductDTO = new ProductDTO();
        createdProductDTO.setNameProduct("Test Product");
        // Set other properties of createdProductDTO as needed

        when(productService.createProduct(any(ProductDTO.class))).thenReturn(createdProductDTO);

        // Act
        ResponseEntity<ProductDTO> response = productController.createProduct(productDTO);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(createdProductDTO, response.getBody());
    }
    @Test
    public void productById_ShouldReturnProductAndStatusOk_WhenProductIsFound() {
        // Arrange
        Long id = 1L;
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(id);
        // Set other properties of productDTO as needed

        when(productService.productById(anyLong())).thenReturn(productDTO);

        // Act
        ResponseEntity<ProductDTO> response = productController.productById(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productDTO, response.getBody());
    }

    @Test
    public void productById_ShouldThrowNotFoundException_WhenProductIsNotFound() {
        // Arrange
        Long id = 1L;
        when(productService.productById(anyLong())).thenThrow(new ProductNotFoundException("Product not found"));

        // Act & Assert
        assertThrows(ProductNotFoundException.class, () -> productController.productById(id));
    }
    @Test
    public void productGetByColor_ShouldReturnProductListAndStatusOk_WhenProductsAreFound() {
        // Arrange
        String color = "BLACK";
        ProductDTO productDTO1 = new ProductDTO();
        productDTO1.setColor(Color.BLACK);
        // Set other properties of productDTO1 as needed

        ProductDTO productDTO2 = new ProductDTO();
        productDTO2.setColor(Color.BLACK);
        // Set other properties of productDTO2 as needed

        List<ProductDTO> productDTOList = Arrays.asList(productDTO1, productDTO2);

        when(productService.productGetByColor(any(Color.class))).thenReturn(productDTOList);

        // Act
        ResponseEntity<List<ProductDTO>> response = productController.productGetByColor(color);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productDTOList, response.getBody());
    }

    @Test
    public void productGetByColor_ShouldReturnEmptyListAndStatusOk_WhenNoProductsAreFound() {
        // Arrange
        String color = "BLACK";
        List<ProductDTO> productDTOList = Collections.emptyList();

        when(productService.productGetByColor(any(Color.class))).thenReturn(productDTOList);

        // Act
        ResponseEntity<List<ProductDTO>> response = productController.productGetByColor(color);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productDTOList, response.getBody());
    }
    @Test
    public void productGetAll_ShouldReturnProductListAndStatusOk_WhenProductsAreAvailable() {
        // Arrange
        ProductDTO productDTO1 = new ProductDTO();
        // Set properties of productDTO1 as needed

        ProductDTO productDTO2 = new ProductDTO();
        // Set properties of productDTO2 as needed

        List<ProductDTO> productDTOList = Arrays.asList(productDTO1, productDTO2);

        when(productService.productGetAll()).thenReturn(productDTOList);

        // Act
        ResponseEntity<List<ProductDTO>> response = productController.productGetAll();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productDTOList, response.getBody());
    }

    @Test
    public void productGetAll_ShouldReturnEmptyListAndStatusOk_WhenNoProductsAreAvailable() {
        // Arrange
        List<ProductDTO> productDTOList = Collections.emptyList();

        when(productService.productGetAll()).thenReturn(productDTOList);

        // Act
        ResponseEntity<List<ProductDTO>> response = productController.productGetAll();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productDTOList, response.getBody());
    }
    @Test
    public void deactivateProduct_ShouldReturnDeactivatedProductAndStatusOk_WhenProductIsFound() {
        // Arrange
        Long id = 1L;
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(id);
        // Set other properties of productDTO as needed
        productDTO.setOffer(false); // Assuming 'active' is a field that indicates if the product is active or not

        when(productService.desactiveProduct(anyLong())).thenReturn(productDTO);

        // Act
        ResponseEntity<ProductDTO> response = productController.deactivateProduct(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productDTO, response.getBody());
    }

    @Test
    public void deactivateProduct_ShouldThrowNotFoundException_WhenProductIsNotFound() {
        // Arrange
        Long id = 1L;
        when(productService.desactiveProduct(anyLong())).thenThrow(new ProductNotFoundException("Product not found"));

        // Act & Assert
        assertThrows(ProductNotFoundException.class, () -> productController.deactivateProduct(id));
    }

    @Test
    public void updateProduct_ShouldReturnStatusNoContent_WhenProductIsUpdatedSuccessfully() {
        // Arrange
        Long id = 1L;
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(id);
        // Set other properties of productDTO as needed

        when(productService.updateProduct(anyLong(), any(ProductDTO.class))).thenReturn(productDTO);

        // Act
        ResponseEntity<ProductDTO> response = productController.updateProduct(id, productDTO);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void updateProduct_ShouldThrowNotFoundException_WhenProductIsNotFound() {
        // Arrange
        Long id = 1L;
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(id);
        // Set other properties of productDTO as needed
        when(productService.updateProduct(anyLong(), any(ProductDTO.class)))
                .thenThrow(new ProductNotFoundException("Product not found"));

        // Act & Assert
        assertThrows(ProductNotFoundException.class, () -> productController.updateProduct(id, productDTO));
    }

    @Test
    public void deleteProduct_ShouldReturnProductAndStatusOk_WhenProductIsDeletedSuccessfully() {
        // Arrange
        Long id = 1L;
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(id);
        // Set other properties of productDTO as needed

        when(productService.deleteProduct(anyLong())).thenReturn(productDTO);

        // Act
        ResponseEntity<ProductDTO> response = productController.deleteProduct(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productDTO, response.getBody());
    }
    @Test
    public void deleteProduct_ShouldThrowNotFoundException_WhenProductIsNotFound() {
        // Arrange
        Long id = 1L;
        when(productService.deleteProduct(anyLong())).thenThrow(new ProductNotFoundException("Product not found"));

        // Act & Assert
        assertThrows(ProductNotFoundException.class, () -> productController.deleteProduct(id));
    }

}