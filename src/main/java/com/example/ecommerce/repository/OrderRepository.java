package com.example.ecommerce.repository;

import com.example.ecommerce.businessobject.Order;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class OrderRepository {

    private static Map<Integer, Order> orders;

    public OrderRepository() {
        orders = new HashMap<>();
    }

    public void addOrder(Order order) {
        orders.put(order.getId(), order);
    }

    public Order getOrderById(int id) {
        return orders.get(id);
    }

    public List<Order> getAllOrders() {
       return orders.values().stream().collect(Collectors.toList());
    }

}
