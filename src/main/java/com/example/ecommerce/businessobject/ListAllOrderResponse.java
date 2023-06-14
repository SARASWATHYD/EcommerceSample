package com.example.ecommerce.businessobject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ListAllOrderResponse implements Response{
    private List<Order> orders;
}
