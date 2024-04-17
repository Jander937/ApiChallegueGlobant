package com.marketplace.marketplace.controller;

import com.marketplace.marketplace.DTO.ProductDTO;
import com.marketplace.marketplace.endPoinst.IProductEndPoint;
import com.marketplace.marketplace.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(IProductEndPoint.PRODUCT_BASE_URL)
public class ProductContoller {
    @Autowired
    private ProductService productService;

    @PostMapping(IProductEndPoint.PRODUCT_CREATE_URL)
    public ResponseEntity<ProductDTO> crearProduct(@RequestBody ProductDTO productDTO) {
        ProductDTO newProductEntity = productService.createProduct(productDTO);
        return new ResponseEntity<>(newProductEntity, HttpStatus.CREATED);
    }

    @GetMapping(IProductEndPoint.PRODUCT_GET_URL)
    public ResponseEntity<ProductDTO> productById(@PathVariable("id") Long id){
        ProductDTO productDTO = productService.findById(id);
        return new ResponseEntity<ProductDTO>(productDTO, HttpStatus.OK);
    }

    @GetMapping(IProductEndPoint.PRODUCT_GET_ALL_URL)
    public ResponseEntity<List<ProductDTO>> productGetAll(){
        List<ProductDTO> productDTO = productService.productGetAll();
        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

    @PostMapping(IProductEndPoint.PRODUCT_UPDATE_URL)
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable("id") Long id, @RequestBody ProductDTO productDTO){
        ProductDTO productDTO1 = ProductService.updateProduct(id, productDTO);
        return new ResponseEntity<>(productDTO1, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(IProductEndPoint.PRODUCT_DELETE_URL)
    public ResponseEntity<ProductDTO> deleteProduct(@PathVariable("id") Long id){
        ProductDTO productDTO = productService.deleteProduct(id);
        return new ResponseEntity<ProductDTO>(productDTO, HttpStatus.NO_CONTENT);
    }

}
