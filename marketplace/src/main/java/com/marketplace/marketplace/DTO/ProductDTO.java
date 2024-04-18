package com.marketplace.marketplace.DTO;

import com.marketplace.marketplace.DTO.enums.Color;
import com.marketplace.marketplace.DTO.enums.Talle;
import com.marketplace.marketplace.DTO.enums.TypeMarc;
import jakarta.persistence.Column;
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
  private TypeMarc typeMarc;
  private Talle talle;
  private Boolean offer = false;
  private Color color;
}
