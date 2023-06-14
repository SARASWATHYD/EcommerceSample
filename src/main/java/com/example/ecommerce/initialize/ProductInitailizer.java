package com.example.ecommerce.initialize;

import com.example.ecommerce.businessobject.Product;
import com.example.ecommerce.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ProductInitailizer {
    private final ProductRepository productRepository;

    public ProductInitailizer(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @PostConstruct
    public void init() {
        productRepository.addProduct(new Product(1, "Juice",new BigDecimal(30),5));
        productRepository.addProduct(new Product(123, "Chips",new BigDecimal(15),15));

        // Add more products as needed
    }

}
