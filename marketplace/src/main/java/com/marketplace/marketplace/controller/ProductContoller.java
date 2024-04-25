package com.marketplace.marketplace.controller;

import com.marketplace.marketplace.DTO.ProductDTO;
import com.marketplace.marketplace.DTO.enums.Color;
import com.marketplace.marketplace.endPoinst.IProductEndPoint;
import com.marketplace.marketplace.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(IProductEndPoint.PRODUCT_BASE_URL)
@Tag(name = "Zone Shoes", description = "Endpoints for managing products")
public class ProductContoller {
    @Autowired
    private ProductService productService;

    @Operation(summary = "Create a new product", description = "Create a new product with the provided details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Product created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid product data provided")
    })
    @PostMapping(IProductEndPoint.PRODUCT_CREATE_URL)
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        ProductDTO newProductEntity = productService.createProduct(productDTO);
        return new ResponseEntity<>(newProductEntity, HttpStatus.CREATED);
    }

    @Operation(summary = "Get product by ID", description = "Get product details by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product found"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    @GetMapping(IProductEndPoint.PRODUCT_GET_URL)
    public ResponseEntity<ProductDTO> productById(@PathVariable("id") Long id){
        ProductDTO productDTO = productService.productById(id);
        return new ResponseEntity<ProductDTO>(productDTO, HttpStatus.OK);
    }
    @Operation(summary = "Get products by color", description = "Retrieve a list of products by color")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of products by color retrieved successfully")
    })

    @GetMapping(IProductEndPoint.PRODUCT_GET_BY_COLOR_URL)
    public ResponseEntity<List<ProductDTO>> productGetByColor(@RequestParam("color") String color){
        List<ProductDTO> productDTOList = productService.productGetByColor(Color.valueOf(color));
        return ResponseEntity.ok(productDTOList);
    }


    @Operation(summary = "Get all products", description = "Retrieve a list of all products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of products retrieved successfully")
    })
    @GetMapping(IProductEndPoint.PRODUCT_GET_ALL_URL)
    public ResponseEntity<List<ProductDTO>> productGetAll(){
        List<ProductDTO> productDTOList = productService.productGetAll();
        return ResponseEntity.ok(productDTOList);
    }

    @Operation(summary = "Deactivate a product", description = "Deactivate a product by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product deactivated successfully"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    @PatchMapping(IProductEndPoint.PRODUCT_DEACTIVATE_URL)
    public ResponseEntity<ProductDTO> deactivateProduct(@PathVariable("id") Long id) {
        ProductDTO productDTO = productService.desactiveProduct(id);
        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }


    @Operation(summary = "Update an existing product", description = "Update an existing product with the provided details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Product updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid product data provided"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    @PutMapping(IProductEndPoint.PRODUCT_UPDATE_URL)
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable("id") Long id, @RequestBody ProductDTO productDTO){
        ProductDTO productDTO1 = productService.updateProduct(id, productDTO);
        return new ResponseEntity<>(productDTO1, HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Delete a product", description = "Delete a product by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Product deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    @DeleteMapping(IProductEndPoint.PRODUCT_DELETE_URL)
    public ResponseEntity<ProductDTO> deleteProduct(@PathVariable("id") Long id){
        ProductDTO productDTO = productService.deleteProduct(id);
        return new ResponseEntity<ProductDTO>(productDTO, HttpStatus.OK);
    }

}
