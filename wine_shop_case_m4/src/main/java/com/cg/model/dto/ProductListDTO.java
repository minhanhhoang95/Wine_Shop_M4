package com.cg.model.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductListDTO {

    private String id;
    private String title;
    private String slug;
    private String avatar;
    private String fileFolder;
    private BigDecimal price;
}
