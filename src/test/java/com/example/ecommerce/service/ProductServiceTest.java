package com.example.ecommerce.service;

import com.example.ecommerce.businessobject.Order;
import com.example.ecommerce.businessobject.Product;
import com.example.ecommerce.businessobject.ProductType;
import com.example.ecommerce.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import java.math.BigDecimal;
import java.util.List;

public class ProductServiceTest {
    private ProductRepository productRepository = new ProductRepository();

    private ProductService productService = new ProductService(productRepository);
    private Product product;

    private int PRODUCT_ID = 123;

    private String PRODUCT_NAME = "MysteryBook";

    @BeforeEach
    void setUp() {
        product = new Product(PRODUCT_ID, PRODUCT_NAME, new BigDecimal(15),75, ProductType.BOOK);
        productService.addProduct(product);
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void givenProductIdReturnProduct() {

        Product actualProduct= productService.getProduct(123);
        Assertions.assertNotNull(actualProduct);
        Assertions.assertEquals(123, actualProduct.getId());
        Assertions.assertEquals(PRODUCT_NAME, actualProduct.getName());

    }

    @Test
    public void addProductGivenProductIdReturnProduct() {

        Product product = new Product(456, "new", new BigDecimal(15),85, ProductType.BOOK);
        productService.addProduct(product);
        Product actualProduct= productService.getProduct(456);
        Assertions.assertNotNull(actualProduct);
        Assertions.assertEquals(product.getId(), actualProduct.getId());
        Assertions.assertEquals(product.getName(), actualProduct.getName());
    }

    @Test
    public void updateQuantityGivenProductIdReturnProduct_UnavailableProduct() {
        Product  product = new Product(4567, "no", new BigDecimal(15),15, ProductType.BOOK);
        Assertions.assertThrows(IllegalArgumentException.class,()->productService.updateQuantity( new Order(12, List.of(product))),
                "Product 4567 is not available");
    }

    @Test
    public void updateQuantityGivenProductIdReturnProduct() {
        Product product = new Product(PRODUCT_ID, PRODUCT_NAME, new BigDecimal(115),15, ProductType.BOOK);
        productService.updateQuantity( new Order(12, List.of(product)));
        Product actualProduct= productService.getProduct(PRODUCT_ID);
        Assertions.assertNotNull(actualProduct);
        Assertions.assertEquals(product.getId(), actualProduct.getId());
        Assertions.assertEquals(product.getName(), actualProduct.getName());
        Assertions.assertEquals(60, actualProduct.getQuantity());

    }

    @Test
    public void updateQuantityGivenProductIdReturnProduct_MaxQuantityProduct() {
        Product orderedProduct = new Product(PRODUCT_ID, PRODUCT_NAME, new BigDecimal(115),90, ProductType.BOOK);
        Assertions.assertThrows(IllegalArgumentException.class,()->productService.updateQuantity( new Order(75, List.of(orderedProduct))),
                "Ordered item Quantity are unavailable in stock");
    }
}
