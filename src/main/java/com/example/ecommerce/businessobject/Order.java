package com.example.ecommerce.businessobject;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Order implements Response{
    private int id;
    @Size(message = "needed atleast 1 Product", min = 1)
    @Valid
    private List<Product> products;
}