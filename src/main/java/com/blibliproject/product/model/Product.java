package com.blibliproject.product.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Product {

    private int id;
    private String name;
    private float price;
    private String main_image;
    private String thumbnail_image;
    private int quantity;
    private int rating;

}
