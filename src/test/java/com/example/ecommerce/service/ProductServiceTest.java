package com.example.ecommerce.service;

import com.example.ecommerce.businessobject.Order;
import com.example.ecommerce.businessobject.Product;
import com.example.ecommerce.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;

public class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }



    @Test
    public void givenProductIdReturnProduct() {
        int productId = 123;
        Product expectedProduct = new Product(productId, "Jucie", new BigDecimal(15),15);

        Mockito.when(productRepository.getProductById(productId)).thenReturn(expectedProduct);

        Product actualProduct= productService.getProduct(productId);

        Assertions.assertNotNull(actualProduct);
        Assertions.assertEquals(expectedProduct.getId(), actualProduct.getId());
        Assertions.assertEquals(expectedProduct.getName(), actualProduct.getName());

        Mockito.verify(productRepository, Mockito.times(1)).getProductById(productId);
    }

    @Test
    public void addProduct_givenProductIdReturnProduct() {
        int productId = 12343;
        Product product = new Product(productId, "Jucie", new BigDecimal(15),15);
        productService.addProduct(product);
        Mockito.when(productRepository.getProductById(productId)).thenReturn(product);

        Product actualProduct= productService.getProduct(productId);

        Assertions.assertNotNull(actualProduct);
        Assertions.assertEquals(product.getId(), actualProduct.getId());
        Assertions.assertEquals(product.getName(), actualProduct.getName());
        Mockito.verify(productRepository, Mockito.times(1)).addProduct(
                product);
    }

    @Test
    public void updateQuantity_givenProductIdReturnProduct_UnavailableProduct() {
        int productId = 123;
        Product product = new Product(productId, "Jucie", new BigDecimal(115),15);
        Assertions.assertThrows(IllegalArgumentException.class,()->productService.updateQuantity( new Order(12, List.of(product))),
                "Product 123 is not available");
        Mockito.verify(productRepository, Mockito.times(1)).getProductById(
                productId);
        Mockito.verify(productRepository, Mockito.times(0)).addProduct(
                product);
    }

    @Test
    public void updateQuantity_givenProductIdReturnProduct() {
        int productId = 123;
        Product product = new Product(productId, "Jucie", new BigDecimal(115),15);
        Mockito.when(productRepository.getProductById(productId)).thenReturn(product);
        productService.updateQuantity( new Order(12, List.of(product)));
        Mockito.verify(productRepository, Mockito.times(1)).getProductById(
                productId);
        Mockito.verify(productRepository, Mockito.times(1)).addProduct(
                product);
    }

    @Test
    public void updateQuantity_givenProductIdReturnProduct_MaxQuantityProduct() {
        int productId = 123;
        Product product = new Product(productId, "Jucie", new BigDecimal(115),15);
        Mockito.when(productRepository.getProductById(productId)).thenReturn(product);
        Product orderedProduct = new Product(productId, "Jucie", new BigDecimal(115),75);

        Assertions.assertThrows(IllegalArgumentException.class,()->productService.updateQuantity( new Order(75, List.of(orderedProduct))),
                "Ordered item Quantity are unavailable in stock");
        Mockito.verify(productRepository, Mockito.times(1)).getProductById(
                productId);
        Mockito.verify(productRepository, Mockito.times(0)).addProduct(
                product);
    }
}
