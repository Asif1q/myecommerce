package com.example.sparksupport.myecommerce.controller;

import com.example.sparksupport.myecommerce.entity.Sale;
import com.example.sparksupport.myecommerce.services.SaleService;
import com.example.sparksupport.myecommerce.exception.ErrorDetails;
import com.example.sparksupport.myecommerce.exception.ProductNotFoundException;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
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
        try {
            Sale createdSale = saleService.createSale(productId, sale);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdSale);
        } catch (IllegalArgumentException ex) {
            log.error("IllegalArgumentException in createSale with productId: {} and sale: {}", productId, sale, ex);
            return ResponseEntity.badRequest().body("Invalid request: " + ex.getMessage());
        } catch (Exception ex) {
            log.error("Error on createSale method with productId: {} and sale: {}", productId, sale, ex);
            ErrorDetails errorDetails = new ErrorDetails(
                    new Date(),
                    "Internal Server Error",
                    ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
        }
    }
    

    @GetMapping("/admin/salesByProduct/{productId}")
    @SecurityRequirement(name = "sparksupport-data-api")
    public ResponseEntity<?> getSalesByProduct(@PathVariable int productId) {
        try {
            List<Sale> sales = saleService.getSalesByProductId(productId).
                            orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + productId));
            return ResponseEntity.ok(sales);
        } catch (Exception ex) {
            log.error("Error fetching sales for product with id: {}", productId, ex);
            ErrorDetails errorDetails = new ErrorDetails(
                    new Date(),
                    "Internal Server Error",
                    ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
        }
    }
    @PutMapping("/admin/update/{saleId}")
    @SecurityRequirement(name = "sparksupport-data-api")
    public ResponseEntity<?> updateSale(@PathVariable int saleId, @RequestBody Sale sale) {
        try {
            Sale updatedSale = saleService.updateSale(saleId, sale);
            return ResponseEntity.ok(updatedSale);
        } catch (IllegalArgumentException ex) {
            log.error("IllegalArgumentException in updateSale with id: {}", saleId, ex);
            return ResponseEntity.badRequest().body("Invalid request: " + ex.getMessage());
        } catch (Exception ex) {
            log.error("Error on updateSale method with id: {}", saleId, ex);
            ErrorDetails errorDetails = new ErrorDetails(
                    new Date(),
                    "Internal Server Error",
                    ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
        }
    }
    

    @DeleteMapping("/admin/delete/{saleId}")
    @SecurityRequirement(name = "sparksupport-data-api")
    public ResponseEntity<Void> deleteSale(@PathVariable int saleId) {
        try {
            saleService.deleteSale(saleId);
            return ResponseEntity.noContent().build();
        } catch (Exception ex) {
            log.error("Error on deleteSale with id: {}", saleId, ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}

