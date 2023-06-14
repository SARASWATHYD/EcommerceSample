package com.example.ecommerce.controller;

import com.example.ecommerce.businessobject.Order;
import com.example.ecommerce.businessobject.OrderRequest;
import com.example.ecommerce.businessobject.Product;
import com.example.ecommerce.businessobject.ProductType;
import com.example.ecommerce.service.EcommerceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class EcommerceControllerTest {

    @MockBean
    private EcommerceService ecommerceService;
    private static final String PATH = "/api/v1/ecommerce";
    @Autowired
    private MockMvc mockMvc;
    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void createOrder() throws Exception{

        Product product = new Product(123, "Juice", new BigDecimal(115),15, ProductType.SPORTS);
        Mockito.when(ecommerceService.processRequest(Mockito.any())).thenReturn( new Order(75, List.of(product)));
        mockMvc.perform(MockMvcRequestBuilders.post(PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(buildSampleOrderRequest(product)))
                     )
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void givenNullStringAsPayloadReturnsInvalidRequest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post(PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("null")
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

    }

    @Test
    public void givenEmptyPayloadReturnsInvalidRequest() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.post(PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("")
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

    }

    @Test
    public void createOrderWithNoProduct() throws Exception{
        OrderRequest orderRequest =  OrderRequest.builder().type(3).length(5).order(new Order(75, new ArrayList<>())).build();
        mockMvc.perform(MockMvcRequestBuilders.post(PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderRequest))
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

    }

    private  OrderRequest  buildSampleOrderRequest(Product product) {
        return OrderRequest.builder().type(1).length(5).order(new Order(75, List.of(product))).build();

    }
}
