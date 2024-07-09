package com.example.sparksupport.myecommerce.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class ProductDTO {

    private String name;
    private String description;
    private double price;
    private int quantity;


}
