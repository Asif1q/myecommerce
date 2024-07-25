package com.example.sparksupport.myecommerce.controller;

import com.example.sparksupport.myecommerce.auth.ProductDTO;
import com.example.sparksupport.myecommerce.entity.Product;
import com.example.sparksupport.myecommerce.services.ProductService;
import com.example.sparksupport.myecommerce.services.SaleService;
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
@SecurityRequirement(name = "sparksupport-data-api")
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
    public ResponseEntity<?> getAllProducts(@RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "10") int size) {
        Page<Product> products = productService.getAllProducts(page, size);
        log.info("Product listed successfully {} :"+products);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable int id) {
        Product product = productService.getProductById(id);
        log.info("Product Details Result : {}", product);
        return ResponseEntity.ok(product);
        
    }

    @PutMapping("/admin/update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable int id, @RequestBody ProductDTO productDTO) {
        Product updatedProduct = productService.updateProduct(id, productDTO);
        log.info("Updated Product Details for id : {"+id+"}", updatedProduct);
        return ResponseEntity.ok(updatedProduct);     
    }

    @PostMapping("/admin/add")
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
        Product createdProduct = productService.createProduct(product);
        log.info("Created Product Details {}", createdProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }
  

    @DeleteMapping("/admin/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
        log.info("Deleted Product {}", id);
        return ResponseEntity.noContent().build();

    }
       
    @GetMapping("/admin/totalRevenue")
    public ResponseEntity<?> getTotalRevenue() {
        double totalRevenue = saleService.getTotalRevenue();
        String formattedRevenue = String.format("%.2f", totalRevenue);
        log.info("totalRevenue {}", formattedRevenue);
        return ResponseEntity.ok(formattedRevenue);
    }

    @GetMapping("/admin/revenueByProduct/{productId}")
    public ResponseEntity<?> getRevenueByProduct(@PathVariable int productId) {
        double revenue = saleService.getRevenueByProduct(productId);
        String formattedRevenue = String.format("%.2f", revenue);
        log.info("Returned getRevenueByProduct {}", formattedRevenue);
        return ResponseEntity.ok("Revenue By Product is: " + formattedRevenue);

    }
    
}
