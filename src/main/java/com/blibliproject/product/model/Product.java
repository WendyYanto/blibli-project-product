package com.blibliproject.product.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")

public class Product {

    @Id
    @NotBlank
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private float price;

    @NotBlank
    private String main_image;

    @NotBlank
    private String thumbnail_image;

    @NotBlank
    private int quantity;

    @NotBlank
    private int rating;

    private int CategoryId;

}
