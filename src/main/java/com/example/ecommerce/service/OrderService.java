package com.example.ecommerce.service;

import com.example.ecommerce.businessobject.Order;
import com.example.ecommerce.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductService productService;
    public OrderService(OrderRepository orderRepository, ProductService productService) {
        this.orderRepository = orderRepository;
        this.productService = productService;
    }

    public Order createOrder(Order order) {
        productService.updateQuantity(order);
        orderRepository.addOrder(order);
        return order;
    }

    public Order getOrderDetails(int id) {
       return orderRepository.getOrderById(id);
    }


    public List<Order> getAllOrders() {
        return orderRepository.getAllOrders();
    }


}
