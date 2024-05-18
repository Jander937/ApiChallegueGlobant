package com.marketplace.marketplace.mapper;

import com.marketplace.marketplace.DTO.ProductDTO;
import com.marketplace.marketplace.model.ProductEntity;
import com.marketplace.marketplace.DTO.enums.Color;
import com.marketplace.marketplace.DTO.enums.Talle;
import com.marketplace.marketplace.DTO.enums.TypeMarc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductMapperTest {

    private ProductMapper productMapper;

    @BeforeEach
    void setUp() {
        productMapper = new ProductMapper();
    }

    @Test
    void mapDtoToEntity() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setNameProduct("Test Product");
        productDTO.setImage("test_image.png");
        productDTO.setDescription("This is a test description");
        productDTO.setPrice(99.99);
        productDTO.setTypeMarc(TypeMarc.NIKE);
        productDTO.setTalle(Talle.TALLE_45);
        productDTO.setOffer(true);
        productDTO.setColor(Color.BLACK);

        ProductEntity productEntity = productMapper.mapDtoToEntity(productDTO);

        assertEquals(productDTO.getNameProduct(), productEntity.getNameProduct());
        assertEquals(productDTO.getImage(), productEntity.getImage());
        assertEquals(productDTO.getDescription(), productEntity.getDescription());
        assertEquals(productDTO.getPrice(), productEntity.getPrice());
        assertEquals(productDTO.getTypeMarc(), productEntity.getTypeMarc());
        assertEquals(productDTO.getTalle(), productEntity.getTalle());
        assertEquals(productDTO.getOffer(), productEntity.getOffer());
        assertEquals(productDTO.getColor(), productEntity.getColor());
    }

    @Test
    void mapEntityToDto() {
        ProductEntity productEntity = ProductEntity.builder()
                .nameProduct("Test Product")
                .image("test_image.png")
                .description("This is a test description")
                .price(99.99)
                .typeMarc(TypeMarc.NIKE)
                .talle(Talle.TALLE_45)
                .offer(true)
                .color(Color.BLACK)
                .build();

        ProductDTO productDTO = productMapper.mapEntityToDto(productEntity);

        assertEquals(productEntity.getNameProduct(), productDTO.getNameProduct());
        assertEquals(productEntity.getImage(), productDTO.getImage());
        assertEquals(productEntity.getDescription(), productDTO.getDescription());
        assertEquals(productEntity.getPrice(), productDTO.getPrice());
        assertEquals(productEntity.getTypeMarc(), productDTO.getTypeMarc());
        assertEquals(productEntity.getTalle(), productDTO.getTalle());
        assertEquals(productEntity.getOffer(), productDTO.getOffer());
        assertEquals(productEntity.getColor(), productDTO.getColor());
    }
}
