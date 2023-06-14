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

public class OrderServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private OrderService orderService;

    @Mock
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void givenCreateOrderReturnOrder(){

        int productId = 123;
        Product product = new Product(productId, "Juice", new BigDecimal(115),15);
        Mockito.when(productRepository.getProductById(productId)).thenReturn(product);
        Mockito.doNothing().when(productService).updateQuantity(Mockito.any());

        Product orderedProduct = new Product(productId, "Jucie", new BigDecimal(115),10);

        Order order = orderService.createOrder( new Order(75, List.of(orderedProduct)));

        Assertions.assertNotNull(order);
        Assertions.assertNotEquals(product.getQuantity(),orderedProduct.getQuantity());

    }

}
