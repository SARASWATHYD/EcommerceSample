package com.example.ecommerce.initialize;

import com.example.ecommerce.businessobject.Product;
import com.example.ecommerce.businessobject.ProductType;
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
        productRepository
                .addProduct(new Product(1, "Steve Jobs",new BigDecimal(30),5, ProductType.BOOK));
        productRepository
                .addProduct(new Product(2, "Effective Java",new BigDecimal(15),15, ProductType.BOOK));
        productRepository
                .addProduct(new Product(3, "Head First Java",new BigDecimal(15),15, ProductType.BOOK));
        productRepository
                .addProduct(new Product(4, "Head First Object-Oriented Analysis Design",new BigDecimal(15),15, ProductType.BOOK));
        productRepository
                .addProduct(new Product(5, "Building Microservices",new BigDecimal(15),15, ProductType.BOOK));

        // Add more products as needed
    }

}
