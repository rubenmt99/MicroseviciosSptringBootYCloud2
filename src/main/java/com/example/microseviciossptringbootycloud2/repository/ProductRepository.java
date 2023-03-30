package com.example.microseviciossptringbootycloud2.repository;

import com.example.microseviciossptringbootycloud2.entity.Category;
import com.example.microseviciossptringbootycloud2.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    //si queremos buscar por atributos podemos poner el prefijo findBy y luego la propiedad del entity
    public List<Product> findByCategory(Category category);
}
