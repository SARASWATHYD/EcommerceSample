package com.example.ecommerce.repository;

import com.example.ecommerce.businessobject.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

public class ProductRepositoryTest {

    @InjectMocks
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void addProductReturnProduct(){
        Product product = new Product(1, "Juice",new BigDecimal(30),5);
        productRepository.addProduct(product);
        Product actualProduct = productRepository.getProductById(1);
        Assertions.assertNotNull(actualProduct);
        Assertions.assertEquals(product.getId(), actualProduct.getId());
        Assertions.assertEquals(product.getName(), actualProduct.getName());
    }

    @Test
    public void getProductIdReturnProduct(){
        Product product = new Product(1, "Juice",new BigDecimal(30),5);
        productRepository.addProduct(product);
        Product actualProduct = productRepository.getProductById(1);
        Assertions.assertNotNull(actualProduct);
        Assertions.assertEquals(product.getId(), actualProduct.getId());
        Assertions.assertEquals(product.getName(), actualProduct.getName());
    }

}
