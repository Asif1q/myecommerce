package com.example.sparksupport.myecommerce.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.example.sparksupport.myecommerce.entity.Product;
import com.example.sparksupport.myecommerce.repository.ProductRepository;
import jakarta.transaction.Transactional;


@Service
@Transactional
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

      public Page<Product> getAllProducts(int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        return productRepository.findAll(pageable);
    }

    public Optional<Product> getProductById(int id) {
        return productRepository.findById(id);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(int id, Product product) {
        if (!productRepository.existsById(id)) {
            throw new IllegalArgumentException("Product not found with id: " + id);
        }
        product.setId(id); 
       return productRepository.save(product);
    }

    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }


}



