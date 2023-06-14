package com.example.ecommerce.service;

import com.example.ecommerce.businessobject.Order;
import com.example.ecommerce.businessobject.Product;
import com.example.ecommerce.businessobject.ProductType;
import com.example.ecommerce.repository.OrderRepository;
import com.example.ecommerce.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import java.math.BigDecimal;
import java.util.List;

public class OrderServiceTest {

    private ProductRepository productRepository = new ProductRepository();

    private OrderRepository orderRepository = new OrderRepository();

    private ProductService productService = new ProductService(productRepository);

    private OrderService orderService = new OrderService(orderRepository,productService);

    private Product product;

    private int PRODUCT_ID = 123;

    private int ORDER_ID = 75;

    private String PRODUCT_NAME = "MysteryBook";

    private Order order;

    @BeforeEach
    void setUp() {
        product = new Product(PRODUCT_ID, PRODUCT_NAME, new BigDecimal(15),75, ProductType.BOOK);
        productService.addProduct(product);
        order = new Order( 0,List.of(product));
        orderRepository.addOrder(order);
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createOrder_givenCreateOrderReturnOrder(){
        Product orderedProduct = new Product(PRODUCT_ID, PRODUCT_NAME, new BigDecimal(115),10, ProductType.BOOK);

        Order order = orderService.createOrder(new Order(ORDER_ID+1, List.of(orderedProduct)));

        Assertions.assertNotNull(order);
        Assertions.assertEquals(10,orderedProduct.getQuantity());
        Assertions.assertEquals(65,product.getQuantity());

    }

    @Test
    public void createOrder_givenCreateOrderReturnException_MaxQuantityOrder(){
        Product orderedProduct = new Product(PRODUCT_ID, PRODUCT_NAME, new BigDecimal(115),90, ProductType.BOOK);

        Assertions.assertThrows(IllegalArgumentException.class,()->orderService.createOrder( new Order(75, List.of(orderedProduct))),
                "Ordered item Quantity are unavailable in stock");

    }

    @Test
    public void createOrder_givenCreateOrderReturnException_InvalidProductId(){

        Product orderedProduct = new Product(PRODUCT_ID+20, PRODUCT_NAME, new BigDecimal(115),5, ProductType.BOOK);

        Assertions.assertThrows(IllegalArgumentException.class,()->orderService.createOrder( new Order(75, List.of(orderedProduct))),
                "Product 133 is not available");

    }

    @Test
    public void getOrder_givenCreateOrderReturnOrder(){

        Order order = orderService.getOrderDetails(1);
        Assertions.assertNotNull(order);
        Assertions.assertEquals(1,order.getProducts().size());

    }

    @Test
    public void getOrder_givenCreateOrderReturnException_InvalidOrder(){

        Assertions.assertThrows(IllegalArgumentException.class,()->orderService.getOrderDetails( 75),
                "Order Not Found::75");

    }

    @Test
    public void getALLOrders_givenCreateOrderReturnOrder(){

        List<Order> orders = orderService.getAllOrders();
        Assertions.assertNotNull(orders);
        Assertions.assertEquals(1,orders.size());

    }


}
