package com.example.sparksupport.myecommerce.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int quantity;
    private LocalDate saleDate;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonBackReference
    // @JsonManagedReference
    @JsonIgnore
    private Product product;


    public Sale(Integer id, int quantity, LocalDate saleDate, Product product) {
        this.id = id;
        this.quantity = quantity;
        this.saleDate = saleDate;
        this.product = product;
    }

    public Sale(Product product, Integer quantity, LocalDate saleDate) {
        this.product = product;
        this.quantity = quantity;
        this.saleDate = saleDate;
    }

    
    public Sale() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }
    @JsonIgnore
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Sale [id=" + id + ", quantity=" + quantity + ", saleDate=" + saleDate
                + ", product=" + product + "]";
    }

    
    
}
