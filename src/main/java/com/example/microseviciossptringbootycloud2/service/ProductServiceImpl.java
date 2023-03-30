package com.example.microseviciossptringbootycloud2.service;

import com.example.microseviciossptringbootycloud2.entity.Category;
import com.example.microseviciossptringbootycloud2.entity.Product;
import com.example.microseviciossptringbootycloud2.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductRepository productRepository;
    @Override
    public List<Product> listAllProductos() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(Integer id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product createProduct(Product product) {

        //Product product1 = ProductRepository.findByCodigoProducto
                //validamos que si existe. if product1 != null return product1


        product.setStatus("Created");
        product.setCreateAt(new Date());
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        Product productDB = getProduct(product.getId());
        if (productDB == null){
            return null;
        }
        productDB.setName(product.getName());
        productDB.setDescription(product.getDescription());
        productDB.setCategory(product.getCategory());
        productDB.setPrice(product.getPrice());
        return productRepository.save(productDB);
    }

    @Override
    public Product deleteProduct(Integer id) {
        Product productDB = getProduct(id);
        if (productDB == null){
            return null;
        }
        productDB.setStatus("Deleted");
        return productRepository.save(productDB);
    }

    @Override
    public List<Product> findByCategory(Category category) {
        return productRepository.findByCategory(category);
    }

    @Override
    public Product updateStock(Integer id, Double quantity) {
        Product productDB = getProduct(id);
        if (productDB == null){
            return null;
        }
        Double stock = productDB.getStock() + quantity;
        productDB.setStock(stock);

        return productRepository.save(productDB);
    }

}
