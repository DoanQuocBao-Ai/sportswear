package com.store.sportswear.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Categories {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String category_name;
    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<Product> listProduct;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public List<Product> getListProduct() {
        return listProduct;
    }

    public void setListProduct(List<Product> listProduct) {
        this.listProduct = listProduct;
    }
}
