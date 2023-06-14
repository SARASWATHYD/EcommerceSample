package com.example.ecommerce.service;

import com.example.ecommerce.businessobject.Order;
import com.example.ecommerce.businessobject.OrderRequest;
import com.example.ecommerce.businessobject.Product;
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

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void givenInvalidExpectedException(){
        OrderRequest orderRequest = OrderRequest.builder().build();
        Assertions.assertThrows(IllegalArgumentException.class,
                ()->ecommerceService.processRequest(orderRequest),"Unexpected value: 0");
    }


    @Test
    public void givenInvalidExpectedValidOrder(){
        Product orderedProduct = new Product(1234, "Jucie", new BigDecimal(115),10);
        Order order = new Order(75, List.of(orderedProduct));
        Mockito.when(orderService.createOrder(Mockito.any())).thenReturn(order);
        OrderRequest orderRequest = OrderRequest.builder()
                                            .type(3).length(5)
                                            .order(order).build();

        ecommerceService.processRequest(orderRequest);

        Mockito.verify(orderService, Mockito.times(1)).createOrder(order);

    }
}
