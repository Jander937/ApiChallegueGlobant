package com.marketplace.marketplace.model;

import com.marketplace.marketplace.DTO.enums.Color;
import com.marketplace.marketplace.DTO.enums.Talle;
import com.marketplace.marketplace.DTO.enums.TypeMarc;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductEntityTest {

    private ProductEntity product;

    @BeforeEach
    void setUp() {
        product = new ProductEntity();
        product.setId(1L);
        product.setNameProduct("Test Product");
        product.setImage("test_image.png");
        product.setDescription("This is a test description");
        product.setPrice(99.99);
        product.setTalle(Talle.TALLE_45);
        product.setOffer(true);
        product.setColor(Color.BLACK);
        product.setTypeMarc(TypeMarc.NIKE);
    }

    @AfterEach
    void tearDown() {
        // Clean up after each test if necessary
    }

    @Test
    void testEquals() {
        ProductEntity product2 = new ProductEntity();
        product2.setId(product.getId());
        product2.setNameProduct(product.getNameProduct());
        product2.setImage(product.getImage());
        product2.setDescription(product.getDescription());
        product2.setPrice(product.getPrice());
        product2.setTalle(product.getTalle());
        product2.setOffer(product.getOffer());
        product2.setColor(product.getColor());
        product2.setTypeMarc(product.getTypeMarc());

        assertEquals(product, product2);
    }

    @Test
    void canEqual() {
        ProductEntity product2 = new ProductEntity();
        assertTrue(product.canEqual(product2));
    }

    @Test
    void testHashCode() {
        ProductEntity product1 = new ProductEntity();
        product1.setId(1L);
        product1.setNameProduct("Test Product");
        product1.setImage("test_image.png");
        product1.setDescription("This is a test description");
        product1.setPrice(99.99);
        product1.setTalle(Talle.TALLE_45);
        product1.setOffer(true);
        product1.setColor(Color.BLACK);
        product1.setTypeMarc(TypeMarc.NIKE);

        ProductEntity product2 = new ProductEntity();
        product2.setId(product1.getId());
        product2.setNameProduct(product1.getNameProduct());
        product2.setImage(product1.getImage());
        product2.setDescription(product1.getDescription());
        product2.setPrice(product1.getPrice());
        product2.setTalle(product1.getTalle());
        product2.setOffer(product1.getOffer());
        product2.setColor(product1.getColor());
        product2.setTypeMarc(product1.getTypeMarc());

        assertEquals(product1.hashCode(), product2.hashCode());
    }

    @Test
    void getId() {
        assertEquals(1L, product.getId());
    }

    @Test
    void getNameProduct() {
        assertEquals("Test Product", product.getNameProduct());
    }

    @Test
    void getImage() {
        assertEquals("test_image.png", product.getImage());
    }

    @Test
    void getDescription() {
        assertEquals("This is a test description", product.getDescription());
    }

    @Test
    void getPrice() {
        assertEquals(99.99, product.getPrice());
    }

    @Test
    void getTalle() {
        assertEquals(Talle.TALLE_45, product.getTalle());
    }

    @Test
    void getOffer() {
        assertTrue(product.getOffer());
    }

    @Test
    void getColor() {
        assertEquals(Color.BLACK, product.getColor());
    }

    @Test
    void getTypeMarc() {
        assertEquals(TypeMarc.NIKE, product.getTypeMarc());
    }

    @Test
    void setId() {
        product.setId(2L);
        assertEquals(2L, product.getId());
    }

    @Test
    void setNameProduct() {
        product.setNameProduct("New Name");
        assertEquals("New Name", product.getNameProduct());
    }

    @Test
    void setImage() {
        product.setImage("new_image.png");
        assertEquals("new_image.png", product.getImage());
    }

    @Test
    void setDescription() {
        product.setDescription("New description");
        assertEquals("New description", product.getDescription());
    }

    @Test
    void setPrice() {
        product.setPrice(199.99);
        assertEquals(199.99, product.getPrice());
    }

    @Test
    void setTalle() {
        product.setTalle(Talle.TALLE_45);
        assertEquals(Talle.TALLE_45, product.getTalle());
    }

    @Test
    void setOffer() {
        product.setOffer(false);
        assertFalse(product.getOffer());
    }

    @Test
    void setColor() {
        product.setColor(Color.BLACK);
        assertEquals(Color.BLACK, product.getColor());
    }

    @Test
    void setTypeMarc() {
        product.setTypeMarc(TypeMarc.NIKE);
        assertEquals(TypeMarc.NIKE, product.getTypeMarc());
    }

    @Test
    void testToString() {
        String expectedToString = "ProductEntity(id=1, nameProduct=Test Product, image=test_image.png, description=This is a test description, price=99.99, talle=TALLE_45, offer=true, color=BLACK, typeMarc=NIKE)";
        assertEquals(expectedToString, product.toString());
    }

    @Test
    void builder() {
        ProductEntity builtProduct = ProductEntity.builder()
                .id(3L)
                .nameProduct("Built Product")
                .image("built_image.png")
                .description("Built description")
                .price(299.99)
                .talle(Talle.TALLE_45)
                .offer(true)
                .color(Color.GREEN)
                .typeMarc(TypeMarc.NIKE)
                .build();

        assertEquals(3L, builtProduct.getId());
        assertEquals("Built Product", builtProduct.getNameProduct());
        assertEquals("built_image.png", builtProduct.getImage());
        assertEquals("Built description", builtProduct.getDescription());
        assertEquals(299.99, builtProduct.getPrice());
        assertEquals(Talle.TALLE_45, builtProduct.getTalle());
        assertTrue(builtProduct.getOffer());
        assertEquals(Color.GREEN, builtProduct.getColor());
        assertEquals(TypeMarc.NIKE, builtProduct.getTypeMarc());
    }

}