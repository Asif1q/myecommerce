package com.example.sparksupport.myecommerce.controller;

import com.example.sparksupport.myecommerce.auth.SaleDTO;
import com.example.sparksupport.myecommerce.entity.Sale;
import com.example.sparksupport.myecommerce.services.SaleService;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@SecurityRequirement(name = "sparksupport-data-api")
@Tag(name = "Sales Controller", description = "Controller for Sales management")
@RequestMapping("/api/sales")
public class SaleController {

    private static final Logger log = LoggerFactory.getLogger(SaleController.class);

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @PostMapping("/admin/add/{productId}")
    @SecurityRequirement(name = "sparksupport-data-api")
    public ResponseEntity<?> createSale(@PathVariable int productId, @RequestBody Sale sale) {
        Sale createdSale = saleService.createSale(productId, sale);
        log.info("Sale created successfully for productId: {"+productId+"}  :"+createdSale);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSale);

    }
    

    @GetMapping("/admin/salesByProduct/{productId}")
    public ResponseEntity<?> getSalesByProduct(@PathVariable int productId) {
        List<Sale> sales = saleService.getSalesByProductId(productId);
        log.info("Sale Results : {}", sales);
        return ResponseEntity.ok(sales);
        } 


    @PutMapping("/admin/update/{saleId}")
    @SecurityRequirement(name = "sparksupport-data-api")
    public ResponseEntity<?> updateSale(@PathVariable int saleId, @RequestBody SaleDTO saleDTO) {
        Sale updatedSale = saleService.updateSale(saleId, saleDTO);
        log.info("Updated Sales for sale id {"+saleId+ "}   :"+updatedSale);
        return ResponseEntity.ok(updatedSale);
        
    }
    

    @DeleteMapping("/admin/delete/{saleId}")
    @SecurityRequirement(name = "sparksupport-data-api")
    public ResponseEntity<Void> deleteSale(@PathVariable int saleId) {
        saleService.deleteSale(saleId);
        log.info("Deleted Sales with sale Id : {" +saleId+"}");
        return ResponseEntity.noContent().build();

    }

}

