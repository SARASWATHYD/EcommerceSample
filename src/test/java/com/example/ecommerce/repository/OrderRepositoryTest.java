package com.example.ecommerce.repository;

import com.example.ecommerce.businessobject.Order;
import com.example.ecommerce.businessobject.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;

public class OrderRepositoryTest {

    @InjectMocks
    private OrderRepository orderRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void getAllOrdersReturnOrders(){
        List<Order> orders = orderRepository.getAllOrders();
        Assertions.assertNotNull(orders);
        Assertions.assertEquals(0,orders.size());
    }

    @Test
    public void getAllOrdersReturnValidOrders(){
        Product product = new Product(1, "Juice",new BigDecimal(30),5);
        orderRepository.addOrder(new Order(123,List.of(product)));
        List<Order> orders = orderRepository.getAllOrders();
        Assertions.assertNotNull(orders);
        Assertions.assertEquals(1,orders.size());
    }

    @Test
    public void addOrdersReturnValidOrders(){
        Product product = new Product(1, "Juice",new BigDecimal(30),5);
        Order order = new Order(123,List.of(product));
        orderRepository.addOrder(order);
        Order orders = orderRepository.getOrderById(123);
        Assertions.assertNotNull(orders);
        Assertions.assertEquals(123,orders.getId());

    }
}