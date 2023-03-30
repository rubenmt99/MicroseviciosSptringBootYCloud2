package com.example.microseviciossptringbootycloud2;

import com.example.microseviciossptringbootycloud2.entity.Category;
import com.example.microseviciossptringbootycloud2.entity.Product;
import com.example.microseviciossptringbootycloud2.repository.ProductRepository;
import com.example.microseviciossptringbootycloud2.service.ProductService;
import com.example.microseviciossptringbootycloud2.service.ProductServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Optional;

@SpringBootTest
public class ProductServiceMockTest {

    Product producto;

    @Mock
    private ProductRepository productRepository;

    @Mock
    ProductService productService;

    @InjectMocks
    private ProductServiceImpl productServiceImpl;

    @BeforeEach
    void  before(){
        producto = Product.builder()
                .id(1)
                .name("computer")
                .category(Category.builder().id(1).build())
                .description("")
                .stock(Double.parseDouble("10"))
                .price(Double.parseDouble("1240.99"))
                .status("Created")
                .createAt(new Date()).build();

        Mockito.when(productRepository.findById(1))
                .thenReturn(Optional.of(producto));

        Mockito.when(productRepository.save(producto)).thenReturn(producto);
    }


    @Test
    public void whenValidGetID_ThenReturnProduct(){
        Product found = productServiceImpl.getProduct(1);
        Assertions.assertEquals("computer", found.getName());
    }


    @Test
    public void whenValidUpdateStock_ThenReturnNewStock(){
        Product newStock = productServiceImpl.updateStock(1,Double.parseDouble(("5")));
        Assertions.assertEquals(newStock.getStock(),15);
    }



}
