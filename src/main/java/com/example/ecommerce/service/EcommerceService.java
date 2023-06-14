package com.example.ecommerce.service;

import com.example.ecommerce.businessobject.ListAllOrderResponse;
import com.example.ecommerce.businessobject.OrderRequest;
import com.example.ecommerce.businessobject.Response;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EcommerceService  {
    private final OrderService orderService;

    public EcommerceService(OrderService orderService) {
        this.orderService = orderService;
    }

    public Response processRequest(@Valid  OrderRequest orderRequest) {
        log.info("Request type : "+orderRequest.getType());

        if(isOrderSizeGreaterThanGivenLength(orderRequest)) {
            throw new IllegalArgumentException("Order size can't be greater than the specified length");
        }

        return switch (orderRequest.getType()) {
            case 1 -> new ListAllOrderResponse(orderService.getAllOrders());
            case 2 -> orderService.getOrderDetails(orderRequest.getOrder().getId());
            case 3 -> orderService.createOrder(orderRequest.getOrder());
            default -> throw new IllegalArgumentException("Unexpected value: " + orderRequest.getType());
        };
    }

    private boolean isOrderSizeGreaterThanGivenLength(OrderRequest orderRequest) {
        return orderRequest.getType() > 1 &&
                orderRequest.getOrder().toString().getBytes().length > orderRequest.getLength();
    }
}
