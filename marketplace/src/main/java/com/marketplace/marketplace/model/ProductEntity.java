package com.marketplace.marketplace.model;

import com.marketplace.marketplace.DTO.enums.Type;
import com.marketplace.marketplace.DTO.enums.TypeProduct;
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
    private Integer amount;
    @Column(name = "type_product")
    private TypeProduct typeProduct;
    private Type type;
}
