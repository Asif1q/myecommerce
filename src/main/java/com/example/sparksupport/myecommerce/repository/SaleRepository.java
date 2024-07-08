package com.example.sparksupport.myecommerce.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sparksupport.myecommerce.entity.Sale;

@Repository
public interface SaleRepository extends JpaRepository<Sale,Integer>{

    Optional<List<Sale>> findByProductId(int productId);
    List<Sale> getSalesByProductId(int productId);



}
