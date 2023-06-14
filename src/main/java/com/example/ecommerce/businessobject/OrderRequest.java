package com.example.ecommerce.businessobject;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@Builder
@ToString

public class OrderRequest implements Response{

    private int type;
    private int length;
    @Valid
    private Order order;
}
