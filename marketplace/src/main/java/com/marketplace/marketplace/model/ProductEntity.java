package com.marketplace.marketplace.model;

import com.marketplace.marketplace.DTO.enums.Color;
import com.marketplace.marketplace.DTO.enums.Talle;
import com.marketplace.marketplace.DTO.enums.TypeMarc;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "products")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name_product")
    private String nameProduct;
    private String image;
    private String description;
    private Double price;
    private Talle talle;
    private Boolean offer = false;
    private Color color;
    @Column(name = "type_marc")
    private TypeMarc typeMarc;
}
