package com.example.ecommerce.service;

import com.example.ecommerce.businessobject.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.math.BigDecimal;
import java.util.List;

public class EcommerceServiceTest {

    @Mock
    private OrderService orderService;

    @InjectMocks
    private EcommerceService ecommerceService;

    private Order order;

    @BeforeEach
    void setUp() {
        Product orderedProduct = new Product(1234, "Jucie", new BigDecimal(115),10, ProductType.BOOK);
        order = new Order(75, List.of(orderedProduct));

        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void processRequest_givenInvalidExpectedException(){
        OrderRequest orderRequest = OrderRequest.builder().type(6).order(order).build();
        Assertions.assertThrows(IllegalArgumentException.class,
                ()->ecommerceService.processRequest(orderRequest),"Unexpected value: 6");
    }

    @Test
    public void processRequest_givenValidExpectedValidOrder(){
        Mockito.when(orderService.createOrder(Mockito.any())).thenReturn(order);
        OrderRequest orderRequest = OrderRequest.builder()
                                            .type(3).length(50067890)
                                            .order(order).build();

        ecommerceService.processRequest(orderRequest);
        Mockito.verify(orderService, Mockito.times(1)).createOrder(order);
    }

    @Test
    public void processRequest_givenInValidLengthExpectedException(){
        Mockito.when(orderService.createOrder(Mockito.any())).thenReturn(order);
        OrderRequest orderRequest = OrderRequest.builder()
                .type(3).length(94)
                .order(order).build();

        Assertions.assertThrows(IllegalArgumentException.class,
                ()->ecommerceService.processRequest(orderRequest),"Order size can't be greater than the specified length");
    }


    @Test
    public void processRequest_getOrders(){
        Mockito.when(orderService.createOrder(Mockito.any())).thenReturn(order);
        OrderRequest orderRequest = OrderRequest.builder()
                .type(1).length(4567)
                .order(order).build();

        ecommerceService.processRequest(orderRequest);
//        assert orders
        Mockito.verify(orderService, Mockito.times(1)).getAllOrders();
    }

    @Test
    public void processRequest_getOrderById(){
        Mockito.when(orderService.createOrder(Mockito.any())).thenReturn(order);
        OrderRequest orderRequest = OrderRequest.builder()
                .type(2).length(789)
                .order(order).build();

        ecommerceService.processRequest(orderRequest);
//        assert orders
        Mockito.verify(orderService, Mockito.times(1)).getOrderDetails(75);
    }

}
