package com.example.microseviciossptringbootycloud2.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "tbl_products")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "El nombre del campo no debe ser vacío")
    private String name;

    private String description;

    @Positive(message = "El stock no puede ser menor a 0")
    private Double stock;

    private Double price;

    private String status;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    //Eager te carga por defecto de manera proactiva todos los valores de
    //las categorias, lazy lo hará solo cuando se requieran
    @NotNull(message = "La categoría no puede ser vacía")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Category category;

/*    @PrePersist
    public void prePersist(){
        this.createAt = new Date();
    }*/

//    Con transient tenemos un atributo que podremos usar en nuestro entorno
//    pero que no aparecerá en nuestra BBDD ni en los Json.
//    @Transient
//    private Double subTotal;

}
