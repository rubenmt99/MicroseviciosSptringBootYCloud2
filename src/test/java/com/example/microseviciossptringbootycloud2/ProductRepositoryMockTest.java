package com.example.microseviciossptringbootycloud2;

import com.example.microseviciossptringbootycloud2.entity.Category;
import com.example.microseviciossptringbootycloud2.entity.Product;
import com.example.microseviciossptringbootycloud2.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
/*
@TestPropertySource(locations = "classpath:application-test.properties")
*/
public class ProductRepositoryMockTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void whenFindByCategory_thenReturnListProduct(){
        Product product01 = Product.builder()
                .name("computer")
                .category(Category.builder().id(1).name("shoes").build())
                .description("")
                .stock(Double.parseDouble("10"))
                .price(Double.parseDouble("1240.99"))
                .status("Created")
                .createAt(new Date()).build();



        System.out.println(product01.getId());
        System.out.println(product01.getCategory());
        System.out.println(product01.getName());
        productRepository.save(product01);

        List<Product> founds = productRepository.findByCategory(product01.getCategory());

        Assertions.assertThat(founds.size()).isEqualTo(2);
    }

}
