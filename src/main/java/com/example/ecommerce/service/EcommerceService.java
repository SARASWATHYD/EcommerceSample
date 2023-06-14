package com.example.ecommerce.service;

import com.example.ecommerce.businessobject.ListAllOrderResponse;
import com.example.ecommerce.businessobject.OrderRequest;
import com.example.ecommerce.businessobject.Response;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class EcommerceService  {
    private final OrderService orderService;

    public EcommerceService(OrderService orderService) {
        this.orderService = orderService;
    }

    public Response processRequest(@Valid  OrderRequest orderRequest) {
        return switch (orderRequest.getType()) {
            case 1 -> new ListAllOrderResponse(orderService.getAllOrders());
            case 2 -> orderService.getOrderDetails(orderRequest.getOrder().getId());
            case 3 -> orderService.createOrder(orderRequest.getOrder());
            default -> throw new IllegalArgumentException("Unexpected value: " + orderRequest.getType());
        };
    }

}
