package com.example.ecommerce.businessobject;

import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@AllArgsConstructor
@Validated
public class Product {

    private int id;
    private String name;
    @PositiveOrZero(message = "Price must be non-negative")
    private BigDecimal price;
    private int quantity;

}

