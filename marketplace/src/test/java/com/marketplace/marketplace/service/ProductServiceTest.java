package com.marketplace.marketplace.service;

import com.marketplace.marketplace.DTO.ProductDTO;
import com.marketplace.marketplace.DTO.enums.Color;
import com.marketplace.marketplace.exception.*;
import com.marketplace.marketplace.exception.ProductEqualException;
import com.marketplace.marketplace.mapper.ProductMapper;
import com.marketplace.marketplace.model.ProductEntity;
import com.marketplace.marketplace.repository.IProductRepository;
import com.marketplace.marketplace.validation.ProductValidation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import com.marketplace.marketplace.exception.ProductEqualException;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @Mock
    private ProductMapper productMapper;

    @Mock
    private IProductRepository productRepository;
    @Mock
    private ProductValidation productValidation;
    @InjectMocks
    private ProductService productService;

    @Test
    void createProduct() {
        // Arrange
        ProductDTO productDTO = new ProductDTO();
        productDTO.setNameProduct("Test Product");
        // ... set other properties on productDTO as needed

        ProductEntity productEntity = new ProductEntity();
        productEntity.setNameProduct("Test Product");
        // ... set other properties on productEntity as needed

        when(productMapper.mapDtoToEntity(any(ProductDTO.class))).thenReturn(productEntity);
        when(productRepository.save(any(ProductEntity.class))).thenReturn(productEntity);
        when(productMapper.mapEntityToDto(any(ProductEntity.class))).thenReturn(productDTO);

        // Act
        ProductDTO result = productService.createProduct(productDTO);

        // Assert
        assertNotNull(result);
        assertEquals(productDTO.getNameProduct(), result.getNameProduct());
        // ... assert other properties as needed

        verify(productMapper).mapDtoToEntity(any(ProductDTO.class));
        verify(productRepository).save(any(ProductEntity.class));
        verify(productMapper).mapEntityToDto(any(ProductEntity.class));
    }
    @Test
    void productByIdShouldReturnProductDTOWhenFound() {
        // Arrange
        Long id = 1L;
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(id);
        productEntity.setNameProduct("Test Product");
        // ... set other properties on productEntity as needed

        ProductDTO productDTO = new ProductDTO();
        productDTO.setNameProduct("Test Product");
        // ... set other properties on productDTO as needed

        when(productRepository.findById(id)).thenReturn(Optional.of(productEntity));
        when(productMapper.mapEntityToDto(any(ProductEntity.class))).thenReturn(productDTO);

        // Act
        ProductDTO result = productService.productById(id);

        // Assert
        assertNotNull(result);
        assertEquals(productDTO.getNameProduct(), result.getNameProduct());
        // ... assert other properties as needed

        verify(productRepository).findById(id);
        verify(productMapper).mapEntityToDto(any(ProductEntity.class));
    }

    @Test
    void productByIdShouldThrowExceptionWhenNotFound() {
        // Arrange
        Long id = 1L;
        when(productRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ProductEmptyException.class, () -> productService.productById(id));

        verify(productRepository).findById(id);
        verify(productMapper, never()).mapEntityToDto(any(ProductEntity.class));
    }

    @Test
    void productGetByColorShouldReturnListOfProductDTOs() {
        // Arrange
        Color color = Color.BLACK; // Use the actual enum value you have defined
        List<ProductEntity> productEntities = List.of(
                new ProductEntity(), // Set properties as needed
                new ProductEntity()  // Set properties as needed
        );
        List<ProductDTO> productDTOs = productEntities.stream()
                .map(entity -> new ProductDTO()) // Replace with actual conversion logic
                .collect(Collectors.toList());

        when(productRepository.findByColor(color)).thenReturn(productEntities);
        when(productMapper.mapEntityToDto(any(ProductEntity.class))).thenAnswer(invocation -> new ProductDTO()); // Replace with actual conversion logic

        // Act
        List<ProductDTO> result = productService.productGetByColor(color);

        // Assert
        assertNotNull(result);
        assertEquals(productDTOs.size(), result.size());
        // You can add more detailed assertions here to check the contents of the list

        verify(productRepository).findByColor(color);
        verify(productMapper, times(productEntities.size())).mapEntityToDto(any(ProductEntity.class));
    }

    @Test
    void productGetByColorShouldThrowExceptionWhenNoProductsFound() {
        // Arrange
        Color color = Color.BLACK; // Use the actual enum value you have defined
        when(productRepository.findByColor(color)).thenReturn(Collections.emptyList());

        // Act & Assert
        assertThrows(ProductEmptyException.class, () -> productService.productGetByColor(color));

        verify(productRepository).findByColor(color);
        verify(productMapper, never()).mapEntityToDto(any(ProductEntity.class));
    }
    @Test
    void productGetAllShouldReturnListOfProductDTOs() {
        // Arrange
        List<ProductEntity> productEntities = List.of(
                new ProductEntity(), // Set properties as needed
                new ProductEntity()  // Set properties as needed
        );
        List<ProductDTO> productDTOs = productEntities.stream()
                .map(entity -> new ProductDTO()) // Replace with actual conversion logic
                .collect(Collectors.toList());

        when(productRepository.findAll()).thenReturn(productEntities);
        when(productMapper.mapEntityToDto(any(ProductEntity.class))).thenAnswer(invocation -> new ProductDTO()); // Replace with actual conversion logic

        // Act
        List<ProductDTO> result = productService.productGetAll();

        // Assert
        assertNotNull(result);
        assertEquals(productDTOs.size(), result.size());
        // You can add more detailed assertions here to check the contents of the list

        verify(productRepository).findAll();
        verify(productMapper, times(productEntities.size())).mapEntityToDto(any(ProductEntity.class));
    }

    @Test
    void productGetAllShouldReturnEmptyListWhenNoProductsFound() {
        // Arrange
        when(productRepository.findAll()).thenReturn(Collections.emptyList());

        // Act
        List<ProductDTO> result = productService.productGetAll();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(productRepository).findAll();
        verify(productMapper, never()).mapEntityToDto(any(ProductEntity.class));
    }
    @Test
    void updateProductShouldThrowProductAttributesFormatExceptionForIncorrectAttributes() {
        // Arrange
        Long id = 1L;
        ProductDTO productDTO = new ProductDTO();
        // Intentionally set incorrect or empty attributes to trigger the exception
        productDTO.setNameProduct(""); // Empty name to trigger the exception
        // ... set other properties on productDTO as needed

        ProductEntity existingProductEntity = new ProductEntity();
        existingProductEntity.setId(id);
        existingProductEntity.setNameProduct("Existing Product Name");
        // ... set other properties on existingProductEntity as needed

        when(productRepository.findById(id)).thenReturn(Optional.of(existingProductEntity));
        when(productMapper.mapDtoToEntity(productDTO)).thenThrow(new ProductAttributesFormatException("Incorrect format or empty attribute"));

        // Act & Assert
        assertThrows(ProductAttributesFormatException.class, () -> productService.updateProduct(id, productDTO));

        verify(productRepository).findById(id);
        verify(productMapper).mapDtoToEntity(productDTO);
        verify(productRepository, never()).save(any(ProductEntity.class));
        verify(productMapper, never()).mapEntityToDto(any(ProductEntity.class));
    }
    @Test
    void updateProductShouldThrowExceptionWhenProductNotFound() {
        // Arrange
        Long id = 1L;
        ProductDTO productDTO = new ProductDTO();
        // ... set properties on productDTO as needed

        when(productRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ProductEmptyException.class, () -> productService.updateProduct(id, productDTO));

        verify(productRepository).findById(id);
        verify(productMapper, never()).mapDtoToEntity(productDTO);
        verify(productRepository, never()).save(any(ProductEntity.class));
        verify(productMapper, never()).mapEntityToDto(any(ProductEntity.class));
    }
    @Test
    void updateProductShouldThrowExceptionWhenProductEmptyValidationFails() {
        // Arrange
        Long id = 1L;
        ProductDTO productDTO = new ProductDTO();
        // ... set properties on productDTO

        when(productRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ProductEmptyException.class, () -> productService.updateProduct(id, productDTO));

        verify(productRepository).findById(id);
        verify(productMapper, never()).mapDtoToEntity(productDTO);
        verify(productRepository, never()).save(any(ProductEntity.class));
    }
    @Test
    void desactiveProductShouldReturnProductDTOWithOfferToggled() {
        // Arrange
        Long id = 1L;
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(id);
        productEntity.setOffer(true); // Assuming 'true' means the product is active

        ProductDTO productDTO = new ProductDTO();
        // Set properties on productDTO as needed

        when(productRepository.findById(id)).thenReturn(Optional.of(productEntity));
        when(productRepository.save(any(ProductEntity.class))).thenReturn(productEntity);
        when(productMapper.mapEntityToDto(any(ProductEntity.class))).thenReturn(productDTO);

        // Act
        ProductDTO result = productService.desactiveProduct(id);

        // Assert
        assertNotNull(result);
        assertFalse(productEntity.getOffer()); // The offer should now be toggled to 'false'

        verify(productRepository).findById(id);
        verify(productRepository).save(productEntity);
        verify(productMapper).mapEntityToDto(productEntity);
    }

    @Test
    void desactiveProductShouldThrowNotFoundExceptionWhenProductNotFound() {
        // Arrange
        Long id = 1L;
        when(productRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        ResponseStatusException thrown = assertThrows(
                ResponseStatusException.class,
                () -> productService.desactiveProduct(id),
                "Expected desactiveProduct to throw, but it didn't"
        );

        assertTrue(thrown.getStatusCode() == HttpStatus.NOT_FOUND);

        verify(productRepository).findById(id);
        verify(productRepository, never()).save(any(ProductEntity.class));
        verify(productMapper, never()).mapEntityToDto(any(ProductEntity.class));
    }
    @Test
    void deleteProductShouldReturnProductDTO() {
        // Arrange
        Long id = 1L;
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(id);
        // Set other properties on productEntity as needed

        ProductDTO productDTO = new ProductDTO();
        // Set properties on productDTO as needed

        when(productRepository.findById(id)).thenReturn(Optional.of(productEntity));
        doNothing().when(productRepository).deleteById(id);
        when(productMapper.mapEntityToDto(any(ProductEntity.class))).thenReturn(productDTO);

        // Act
        ProductDTO result = productService.deleteProduct(id);

        // Assert
        assertNotNull(result);
        assertEquals(productDTO, result);

        verify(productRepository).findById(id);
        verify(productRepository).deleteById(id);
        verify(productMapper).mapEntityToDto(productEntity);
    }

    @Test
    void deleteProductShouldThrowNotFoundExceptionWhenProductNotFound() {
        // Arrange
        Long id = 1L;
        when(productRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        ResponseStatusException thrown = assertThrows(
                ResponseStatusException.class,
                () -> productService.deleteProduct(id),
                "Expected deleteProduct to throw, but it didn't"
        );

        assertTrue(thrown.getStatusCode() == HttpStatus.NOT_FOUND);

        verify(productRepository).findById(id);
        verify(productRepository, never()).deleteById(id);
        verify(productMapper, never()).mapEntityToDto(any(ProductEntity.class));
    }

}