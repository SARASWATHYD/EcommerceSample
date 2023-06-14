package com.example.ecommerce.controller;

import com.example.ecommerce.businessobject.ErrorResponse;
import com.example.ecommerce.businessobject.OrderRequest;
import com.example.ecommerce.businessobject.Response;
import com.example.ecommerce.service.EcommerceService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Validated
@RequestMapping("/v1/ecommerce")
public class EcommerceController {

    private final EcommerceService ecommerceService;

    public EcommerceController(EcommerceService ecommerceService) {
        this.ecommerceService = ecommerceService;
    }

    @PostMapping
    public Response processOrder(@Validated @RequestBody OrderRequest orderRequest) {
        System.out.println(
                "req::"+orderRequest.getOrder().getProducts().get(0).getPrice()

        );

        return ecommerceService.processRequest(orderRequest);
    }

    @ExceptionHandler
            (MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidationException(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());

        return new ErrorResponse("Validation Error", errors);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        ErrorResponse errorResponse = new ErrorResponse("Invalid Argument", List.of(ex.getMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

}
