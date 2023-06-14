package com.example.ecommerce.service;

import com.example.ecommerce.businessobject.Order;
import com.example.ecommerce.businessobject.Product;
import com.example.ecommerce.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@Slf4j
public class ProductService {

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
            Product product = updateProductQuantity(p);
            p.setName(product.getName());
            p.setPrice(product.getPrice());
            p.setProductType(product.getProductType());
        });
    }

    private Product updateProductQuantity(Product p) {

        Product product = Optional
                .ofNullable(productRepository.getProductById(p.getId()))
                .orElseThrow(()-> new IllegalArgumentException("Product " + p.getId() + " is not available "));

        log.info("ordered quantity :: "+ product.getQuantity()+" available quantity ::"+product.getQuantity());

        if(p.getQuantity() > product.getQuantity()){
            throw new IllegalArgumentException("Ordered item Quantity are unavailable in stock");
        }

        product.setQuantity(product.getQuantity() - p.getQuantity());
        log.info("product :: "+ product.getId()+" after placing order ::"+product.getQuantity());
        productRepository.addProduct(product);

        return product;
    }
}


