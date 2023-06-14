package com.example.ecommerce.businessobject;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ListAllOrderResponse implements Response{
    private List<Order> orders;
}
