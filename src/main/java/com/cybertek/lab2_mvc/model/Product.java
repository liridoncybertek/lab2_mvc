package com.cybertek.lab2_mvc.model;

import lombok.*;

import java.math.BigDecimal;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    private Integer id;

    private String name;

    private Integer quantity;

    private BigDecimal price;

    private String description;

    private Category category;
}
