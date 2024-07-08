package com.example.sparksupport.myecommerce.auth;


import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class SaleDTO {
    private int id;
    private int quantity;
    private LocalDate saleDate;
    private double totalRevenue;
}
