package com.example.sparksupport.myecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sparksupport.myecommerce.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    
    
}
