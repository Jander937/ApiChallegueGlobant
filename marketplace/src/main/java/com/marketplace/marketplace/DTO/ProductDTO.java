package com.marketplace.marketplace.DTO;

import com.marketplace.marketplace.DTO.enums.Type;
import com.marketplace.marketplace.DTO.enums.TypeProduct;
import lombok.*;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
  private String nameProduct;
  private String image;
  private String description;
  private Double price;
  private Integer amount;
  private TypeProduct typeProduct;
  private Type type;


}
