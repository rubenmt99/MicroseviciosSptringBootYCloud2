package com.example.microseviciossptringbootycloud2.controller;

import com.example.microseviciossptringbootycloud2.entity.Category;
import com.example.microseviciossptringbootycloud2.entity.Product;
import com.example.microseviciossptringbootycloud2.service.ProductService;
import com.example.microseviciossptringbootycloud2.service.ProductServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//con Slf4j podemos meter los logs. Que podremos usar en Spring Cloud.
@Slf4j
@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/list")
    public ResponseEntity<List<Product>> listProduct(@RequestParam(name = "categoryId", required = false) Integer categoryId){
        List<Product> products = new ArrayList<>();

        if(categoryId==null){
            products = productService.listAllProductos();
            if(products.isEmpty()){
                return ResponseEntity.noContent().build();
            }
        }else {
            products = productService.findByCategory(Category.builder().id(categoryId).build());
            if(products.isEmpty()){
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.ok(products);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Integer id){
        log.info("Fetching Product with id {}", id);
        Product product = productService.getProduct(id);
        if(product == null){
            log.info("Product with id {} not found", id);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product, BindingResult result){
        if (result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        Product productCreate = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productCreate);
    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable("id") Integer id){
        product.setId(id);
        Product productDB = productService.updateProduct(product);
        if (productDB == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productDB);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") Integer id){
        Product productDeleted = productService.deleteProduct(id);
        if (productDeleted == null){
            return ResponseEntity.notFound().build();
        }

        return  ResponseEntity.ok(productDeleted);
    }

    @GetMapping(value = "/{id}/stock")
    public ResponseEntity<Product> updateStockProduct(@PathVariable Integer id, @RequestParam(name = "quantity", required = true) Double quantity){
        Product product = productService.updateStock(id,quantity);
        if (product == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(product);
    }


    private String formatMessage(BindingResult result){
        List<Map<String,String>> errors = result.getFieldErrors().stream()
                .map(err -> {
                    Map<String,String> error = new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;
                }).collect(Collectors.toList());

        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .messages(errors).build();

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "";
        try {
            jsonString = mapper.writeValueAsString(errorMessage);
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }
        return jsonString;
    }

}
