package com.example.ecommerce.service;

import com.example.ecommerce.businessobject.Order;
import com.example.ecommerce.businessobject.Product;
import com.example.ecommerce.repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;
import java.util.logging.Logger;

@Service
public class ProductService {

    private static final Logger log = Logger.getLogger(ProductService.class.getName());
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getProduct(int id) {
        return productRepository.getProductById(id);
    }

    public void addProduct(Product product) {
        productRepository.addProduct(product);
    }

    public void updateQuantity(Order order) {
        order.getProducts().forEach(p -> {
            Product product = Optional
                    .ofNullable(productRepository.getProductById(p.getId()))
                    .orElseThrow(()-> new IllegalArgumentException("Product " + p.getId() + " is not available "));

            log.info(
                    "ordered quantity :: "+ p.getQuantity()+" available quantity ::"+product.getQuantity()

            );
            if(p.getQuantity() > product.getQuantity()){
                throw new IllegalArgumentException("Ordered item Quantity are unavailable in stock");
            }
            product.setQuantity(product.getQuantity() - p.getQuantity());
            log.info(
                    "product :: "+ p.getId()+" after placing order ::"+product.getQuantity()

            );
            productRepository.addProduct(product);
        });
    }

}


