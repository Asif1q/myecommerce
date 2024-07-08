package com.example.sparksupport.myecommerce.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.sparksupport.myecommerce.entity.Product;
import com.example.sparksupport.myecommerce.entity.Sale;
import com.example.sparksupport.myecommerce.repository.ProductRepository;
import com.example.sparksupport.myecommerce.repository.SaleRepository;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class SaleService {

    private final SaleRepository saleRepository;
    private final ProductRepository productRepository;


    private static final Logger log = LoggerFactory.getLogger(SaleService.class);


    public SaleService(SaleRepository saleRepository, ProductRepository productRepository) {
        this.saleRepository = saleRepository;
        this.productRepository = productRepository;
    }


    public double getTotalRevenue() {
        List<Sale> allSales = saleRepository.findAll();
        double totalRevenue = 0.0;
        for (Sale sale : allSales) {
            totalRevenue += sale.getQuantity() * sale.getProduct().getPrice();
        }
        return totalRevenue;
    }


    public double getRevenueByProduct(int productId) {
        log.info("Calculating revenue for product with id: {}", productId);

        Optional<List<Sale>> salesForProductOptional = saleRepository.findByProductId(productId);
        double revenue = 0.0;

        if (salesForProductOptional.isPresent()) {
            List<Sale> salesForProduct = salesForProductOptional.get();
            log.debug("Sales for product {}: {}", productId, salesForProduct);
            for (Sale sale : salesForProduct) {
                revenue += sale.getQuantity() * sale.getProduct().getPrice();
            }
        } else {
            log.error("No sales found for product with ID: {}", productId);
            throw new RuntimeException("No sales found for product with ID: " + productId);
        }

        return revenue;
    }

    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }

    public Sale getSaleById(int id) {
        return saleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Sale not found with id: " + id));
    }

    public Optional<List<Sale>> getSalesByProductId(int productId) {
        return saleRepository.findByProductId(productId);
    }

    @Transactional
    public Sale createSale(int productId, Sale sale) {
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + productId));
        sale.setProduct(existingProduct);

        return saleRepository.save(sale);
    }

    @Transactional
    public Sale updateSale(int saleId, Sale updatedSale) {
        Sale existingSale = saleRepository.findById(saleId)
                .orElseThrow(() -> new IllegalArgumentException("Sale not found with id: " + saleId));

        updatedSale.setProduct(existingSale.getProduct());

        existingSale.setQuantity(updatedSale.getQuantity());
        existingSale.setSaleDate(updatedSale.getSaleDate());
    
        return saleRepository.save(existingSale);
    }
    
    
    
    
    
    public void deleteSale(int id) {
        saleRepository.deleteById(id);
    }
}
