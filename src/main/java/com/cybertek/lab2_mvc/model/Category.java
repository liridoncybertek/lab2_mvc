package com.cybertek.lab2_mvc.model;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Category {

    private Integer id;

    private String name;

    private String description;
}
