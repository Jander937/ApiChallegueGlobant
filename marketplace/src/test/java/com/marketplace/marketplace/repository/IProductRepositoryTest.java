package com.marketplace.marketplace.repository;

import com.marketplace.marketplace.DTO.enums.Color;
import com.marketplace.marketplace.model.ProductEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class IProductRepositoryTest {
    @Autowired
    private IProductRepository productRepository;

    @Test
    public void whenFindByColor_thenReturnListOfProducts() {
        // Given
        ProductEntity product1 = new ProductEntity();
        product1.setColor(Color.BLACK);
        // Set other properties as needed...
        productRepository.save(product1);

        ProductEntity product2 = new ProductEntity();
        product2.setColor(Color.BLACK);
        // Set other properties as needed...
        productRepository.save(product2);

        // When
        List<ProductEntity> blueProducts = productRepository.findByColor(Color.BLACK);

        // Then
        assertFalse(blueProducts.isEmpty());
        assertTrue(blueProducts.stream().allMatch(product -> product.getColor() == Color.BLACK));
    }
}