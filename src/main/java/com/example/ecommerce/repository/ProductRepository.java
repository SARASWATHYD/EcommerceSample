package com.example.ecommerce.repository;

import com.example.ecommerce.businessobject.Product;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ProductRepository {

    private static Map<Integer, Product> products;

    public ProductRepository() {
        products = new HashMap<>();
    }

    public void addProduct(Product product) {
        products.put(product.getId(), product);
    }

    public Product getProductById(int id) {
        return products.get(id);
    }
}
