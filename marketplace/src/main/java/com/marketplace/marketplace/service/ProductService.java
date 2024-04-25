package com.marketplace.marketplace.service;
import com.marketplace.marketplace.DTO.ProductDTO;
import com.marketplace.marketplace.DTO.enums.Color;
import com.marketplace.marketplace.endPoinst.response.IResponse;
import com.marketplace.marketplace.exception.NotFoundException;
import com.marketplace.marketplace.mapper.ProductMapper;
import com.marketplace.marketplace.model.ProductEntity;
import com.marketplace.marketplace.repository.IProductRepository;
import com.marketplace.marketplace.validation.ProductValidation;
import jakarta.validation.Valid;
import jakarta.validation.Validation;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ControllerAdvice
@Service
public class ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private IProductRepository productRepository;

     public ProductDTO createProduct(ProductDTO productDTO){
         ProductEntity productEntity  = productMapper.mapDtoToEntity(productDTO);
         ProductEntity saveProduct = productRepository.save(productEntity);
         return productMapper.mapEntityToDto(saveProduct);
     }

     public ProductDTO productById(Long id){
         Optional<ProductEntity> product = productRepository.findById(id);
         ProductValidation.productEmptyValidation(product);
         return productMapper.mapEntityToDto(product.get());
     }
    public List<ProductDTO> productGetByColor(Color color){
        List<ProductEntity> product = productRepository.findByColor(color);
        ProductValidation.productColorValidation(product);
        return product.stream()
                .map(productMapper::mapEntityToDto)
                .collect(Collectors.toList());
    }
     public List<ProductDTO> productGetAll(){
         List<ProductEntity> products = (List<ProductEntity>) productRepository.findAll();
         return products.stream()
                 .map(productMapper::mapEntityToDto)
                 .collect(Collectors.toList());

     }

    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Optional<ProductEntity> existingProduct = productRepository.findById(id);
        ProductValidation.productEmptyValidation(existingProduct);
        ProductEntity productEntity = productMapper.mapDtoToEntity(productDTO);
        ProductValidation.productEqualValidation(existingProduct, productEntity);
         Optional<ProductEntity> otherProduct;
        otherProduct = productRepository.findById(id);
        ProductValidation.productValidation(otherProduct, id);
        ProductValidation.productTotalValidation(productEntity);
        ProductEntity product = existingProduct.get();
        product.setId(id);
        product.setNameProduct(productDTO.getNameProduct());
        product.setImage(productDTO.getImage());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setTypeMarc(productDTO.getTypeMarc());
        product.setTalle(productDTO.getTalle());
        product.setOffer(productDTO.getOffer());
        product.setColor(productDTO.getColor());

        return productMapper.mapEntityToDto(productRepository.save(product));
    }

    public ProductDTO desactiveProduct(Long id){
         Optional<ProductEntity> result = this.productRepository.findById(id);
         if(result.isPresent()){
             result.get().setOffer((!result.get().getOffer()));
             return productMapper.mapEntityToDto(this.productRepository.save(result.get()));
         }
         else{
             throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(IResponse.NOT_FOUND));
         }
     }

     public ProductDTO deleteProduct(Long id){
        Optional<ProductEntity> result = this.productRepository.findById(id);
         if(result.isPresent()){
             ProductEntity deletedProduct = result.get();
             this.productRepository.deleteById(id);
             return productMapper.mapEntityToDto(deletedProduct);
         } else {
             throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(IResponse.NOT_FOUND));
         }
     }
}
