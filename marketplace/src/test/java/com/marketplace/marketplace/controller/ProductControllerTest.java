package com.marketplace.marketplace.controller;
import com.marketplace.marketplace.DTO.ProductDTO;
import com.marketplace.marketplace.DTO.enums.Color;
import com.marketplace.marketplace.DTO.enums.Talle;
import com.marketplace.marketplace.DTO.enums.TypeMarc;
import com.marketplace.marketplace.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @Test
    public void testCreateProduct() throws Exception {
        // Given
        ProductDTO mockProductDTO = new ProductDTO();
        mockProductDTO.setNameProduct("Example Product");
        mockProductDTO.setTypeMarc(TypeMarc.valueOf("ADIDAS"));
        mockProductDTO.setTalle(Talle.valueOf("TALLE_45"));
        mockProductDTO.setColor(Color.valueOf("BLACK"));
        mockProductDTO.setPrice(100.0);
        mockProductDTO.setDescription("Example description");
        mockProductDTO.setImage("example.jpg");
        mockProductDTO.setOffer(true);

        ProductDTO createdProduct = new ProductDTO();
        // Assuming the ID is set upon creation

        when(productService.createProduct(mockProductDTO)).thenReturn(createdProduct);

        // When
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/products/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nameProduct\":\"Example Product\",\"image\":\"example.jpg\",\"description\":\"Example description\",\"price\":100.0,\"typeMarc\":\"ADIDAS\",\"talle\":\"TALLE_45\",\"offer\":true,\"color\":\"BLACK\"}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        // Then
        // You could assert further on the response if needed
    }
}
