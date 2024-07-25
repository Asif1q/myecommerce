package com.example.sparksupport.myecommerce.services;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.sparksupport.myecommerce.auth.ProductDTO;
import com.example.sparksupport.myecommerce.entity.Product;
import com.example.sparksupport.myecommerce.exception.ProductNotFoundException;
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

    public Product getProductById(int id) {
        return productRepository.findById(id)
        .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(int id, ProductDTO productDTO) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));

        // Update the existing product with values from the DTO
        existingProduct.setName(productDTO.getName());
        existingProduct.setDescription(productDTO.getDescription());
        existingProduct.setPrice(productDTO.getPrice());
        existingProduct.setQuantity(productDTO.getQuantity());

        return productRepository.save(existingProduct);
    }


    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }


}



