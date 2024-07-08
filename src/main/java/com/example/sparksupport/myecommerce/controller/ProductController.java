package com.example.sparksupport.myecommerce.controller;

import com.example.sparksupport.myecommerce.entity.Product;
import com.example.sparksupport.myecommerce.services.ProductService;
import com.example.sparksupport.myecommerce.services.SaleService;
import com.example.sparksupport.myecommerce.exception.ErrorDetails;
import com.example.sparksupport.myecommerce.exception.ProductNotFoundException;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Product Controller", description = "Controller for Product management")
@RequestMapping("/api/products")
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);


    private final ProductService productService;
    private final SaleService saleService;

    public ProductController(ProductService productService, SaleService saleService) {
        this.productService = productService;
        this.saleService = saleService;
    }

    @GetMapping
    @SecurityRequirement(name = "sparksupport-data-api")
    public ResponseEntity<?> getAllProducts(@RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "10") int size) {
        try {
            Page<Product> products = productService.getAllProducts(page, size);
            return ResponseEntity.ok(products);
        } catch (Exception ex) {
            log.error("Error fetching all products", ex);
            ErrorDetails errorDetails = new ErrorDetails(
                    new Date(),
                    "Internal Server Error",
                    ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
        }
    }

    @GetMapping("/{id}")
    @SecurityRequirement(name = "sparksupport-data-api")
    public ResponseEntity<?> getProductById(@PathVariable int id) {
        try {
            Product product = productService.getProductById(id)
                    .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
            return ResponseEntity.ok(product);
        } catch (ProductNotFoundException ex) {
            log.error("ProductNotFoundException in getProductById with id: {}", id);
            return ResponseEntity.badRequest().body("Invalid request: " + ex.getMessage());
        } catch (Exception ex) {
            log.error("Error fetching product with id: {}", id, ex);
            ErrorDetails errorDetails = new ErrorDetails(
                    new Date(),
                    "Internal Server Error",
                    ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
        }
    }

    @PutMapping("/admin/update/{id}")
    @SecurityRequirement(name = "sparksupport-data-api")
    public ResponseEntity<?> updateProduct(@PathVariable int id, @RequestBody Product product) {
        try {
            Product updatedProduct = productService.updateProduct(id, product);
            return ResponseEntity.ok(updatedProduct);
        } catch (IllegalArgumentException ex) {
            log.error("IllegalArgumentException in updateProduct with id: {}", id);
            return ResponseEntity.badRequest().body("Invalid request: " + ex.getMessage());
        } catch (Exception ex) {           
            ErrorDetails errorDetails = new ErrorDetails(
                    new Date(),
                    "Internal Server Error",
                    ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
        }
    }

    @PostMapping("/admin/add")
    @SecurityRequirement(name = "sparksupport-data-api")
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
        try {
            Product createdProduct = productService.createProduct(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
        } catch (IllegalArgumentException ex) {
            log.error("IllegalArgumentException in createProduct with product: {}", product, ex);
            return ResponseEntity.badRequest().body("Invalid request: " + ex.getMessage());
        } catch (Exception ex) {
            log.error("Error on createProduct method with product: {}", product, ex);
            ErrorDetails errorDetails = new ErrorDetails(
                    new Date(),
                    "Internal Server Error",
                    ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
        }
    }

    @DeleteMapping("/admin/delete/{id}")
    @SecurityRequirement(name = "sparksupport-data-api")
    public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
        try {
            productService.deleteProduct(id);
            return ResponseEntity.noContent().build();
        } catch (ProductNotFoundException ex) {
            log.error("Product not found with id: {}", id, ex);
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            log.error("Error on deleteProduct with id: {}", id, ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    
    

    @GetMapping("/admin/totalRevenue")
    @SecurityRequirement(name = "sparksupport-data-api")
    public ResponseEntity<?> getTotalRevenue() {
        try {
            double totalRevenue = saleService.getTotalRevenue();
            return ResponseEntity.ok(totalRevenue);
        } catch (Exception ex) {
            log.error("Error fetching total revenue", ex);
            ErrorDetails errorDetails = new ErrorDetails(
                    new Date(),
                    "Internal Server Error",
                    ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
        }
    }

    @GetMapping("/admin/revenueByProduct/{productId}")
    @SecurityRequirement(name = "sparksupport-data-api")
    public ResponseEntity<?> getRevenueByProduct(@PathVariable int productId) {
        try {
            double revenue = saleService.getRevenueByProduct(productId);
            return ResponseEntity.ok("Revenue By Product is: " + revenue);
        } catch (Exception ex) {
            log.error("Error fetching revenue for product with id: {}", productId, ex);
            ErrorDetails errorDetails = new ErrorDetails(
                    new Date(),
                    "Internal Server Error",
                    ex.toString());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
        }
    }
    
}
