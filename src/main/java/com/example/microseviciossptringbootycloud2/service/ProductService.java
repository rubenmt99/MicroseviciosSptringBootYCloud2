package com.example.microseviciossptringbootycloud2.service;

import com.example.microseviciossptringbootycloud2.entity.Category;
import com.example.microseviciossptringbootycloud2.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {

    public List<Product> listAllProductos();

    public Product getProduct(Integer id);

    public Product createProduct(Product product);
    public Product updateProduct(Product product);

    public Product deleteProduct(Integer id);

    public List<Product> findByCategory(Category category);

    public Product updateStock(Integer id, Double quantity);

}
