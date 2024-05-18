
package com.marketplace.marketplace.DTO;

import com.marketplace.marketplace.DTO.enums.Color;
import com.marketplace.marketplace.DTO.enums.Talle;
import com.marketplace.marketplace.DTO.enums.TypeMarc;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductDTOTest {

    private ProductDTO productDTO;

    @BeforeEach
    void setUp() {
        productDTO = new ProductDTO();
        productDTO.setNameProduct("Test Product");
        productDTO.setImage("test_image.png");
        productDTO.setDescription("This is a test description");
        productDTO.setPrice(99.99);
        productDTO.setTalle(Talle.TALLE_45);
        productDTO.setOffer(true);
        productDTO.setColor(Color.BLACK);
        productDTO.setTypeMarc(TypeMarc.NIKE);
    }

    @AfterEach
    void tearDown() {
        // Clean up after each test if necessary
    }

    // Implement test methods here

    @Test
    void testValorNumerico() {
        assertEquals(45, Talle.TALLE_45.getValorNumerico());
        assertEquals(46, Talle.TALLE_46.getValorNumerico());
        assertEquals(47, Talle.TALLE_47.getValorNumerico());
        assertEquals(48, Talle.TALLE_48.getValorNumerico());
        assertEquals(49, Talle.TALLE_49.getValorNumerico());
        assertEquals(50, Talle.TALLE_50.getValorNumerico());
        assertEquals(55, Talle.TALLE_55.getValorNumerico());
    }
    @Test
    void testEquals() {
        ProductDTO productDTO2 = new ProductDTO();
        productDTO2.setNameProduct("Test Product");
        productDTO2.setImage("test_image.png");
        productDTO2.setDescription("This is a test description");
        productDTO2.setPrice(99.99);
        productDTO2.setTalle(Talle.TALLE_45);
        productDTO2.setOffer(true);
        productDTO2.setColor(Color.BLACK);
        productDTO2.setTypeMarc(TypeMarc.NIKE);

        assertEquals(productDTO, productDTO2);
    }

    @Test
    void canEqual() {
        ProductDTO productDTO2 = new ProductDTO();
        assertTrue(productDTO.canEqual(productDTO2));
    }

    @Test
    void testHashCode() {
        ProductDTO productDTO2 = new ProductDTO();
        productDTO2.setNameProduct("Test Product");
        productDTO2.setImage("test_image.png");
        productDTO2.setDescription("This is a test description");
        productDTO2.setPrice(99.99);
        productDTO2.setTalle(Talle.TALLE_45);
        productDTO2.setOffer(true);
        productDTO2.setColor(Color.BLACK);
        productDTO2.setTypeMarc(TypeMarc.NIKE);

        assertEquals(productDTO.hashCode(), productDTO2.hashCode());
    }


    @Test
    void testToString() {
        // Asegúrate de que el orden de los campos coincida con el método toString en ProductDTO
        String expectedToString = "ProductDTO(nameProduct=Test Product, image=test_image.png, description=This is a test description, price=99.99, typeMarc=NIKE, talle=TALLE_45, offer=true, color=BLACK)";
        assertEquals(expectedToString, productDTO.toString());
    }


    @Test
    void builder() {
        ProductDTO builtProductDTO = ProductDTO.builder()
                .nameProduct("Built Product")
                .image("built_image.png")
                .description("Built description")
                .price(299.99)
                .talle(Talle.TALLE_45)
                .offer(true)
                .color(Color.BLACK)
                .typeMarc(TypeMarc.NIKE)
                .build();

        assertEquals("Built Product", builtProductDTO.getNameProduct());
        assertEquals("built_image.png", builtProductDTO.getImage());
        assertEquals("Built description", builtProductDTO.getDescription());
        assertEquals(299.99, builtProductDTO.getPrice());
        assertEquals(Talle.TALLE_45, builtProductDTO.getTalle());
        assertTrue(builtProductDTO.getOffer());
        assertEquals(Color.BLACK, builtProductDTO.getColor());
        assertEquals(TypeMarc.NIKE, builtProductDTO.getTypeMarc());
    }


}