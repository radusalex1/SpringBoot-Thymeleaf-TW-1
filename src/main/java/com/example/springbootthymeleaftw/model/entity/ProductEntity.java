package com.example.springbootthymeleaftw.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.*;

@Entity
@Data
@Table(name = "products",schema = "public", catalog = "college")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "price")
    private double price;

    @Basic
    @Column(name = "category")
    private String category;

    @Basic
    @Column(name = "unitatemasura")
    private String unitate_masura;

    @Transient
    private Integer quantity;


    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                '}';
    }

    @OneToMany(mappedBy = "user")
    private List<UserProductEntity> userProductEntityList = new ArrayList<UserProductEntity>();

}
