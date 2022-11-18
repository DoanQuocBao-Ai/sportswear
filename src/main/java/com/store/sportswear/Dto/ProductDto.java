package com.store.sportswear.Dto;

import org.springframework.web.multipart.MultipartFile;

public class ProductDto {
    private String id;
    private String name;
    private int price;
    private int sale_price;
    private String description;
    private int featured;
    private int best_seller;

    private int category_id;
    private int brand_id;
    private MultipartFile image;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSale_price() {
        return sale_price;
    }

    public void setSale_price(int sale_price) {
        this.sale_price = sale_price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFeatured() {
        return featured;
    }

    public void setFeatured(int featured) {
        this.featured = featured;
    }

    public int getBest_seller() {
        return best_seller;
    }

    public void setBest_seller(int best_seller) {
        this.best_seller = best_seller;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(int brand_id) {
        this.brand_id = brand_id;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "ProductDto [id = "+id+", name = "+name+", price= "+price+", sale_price = "+sale_price+", description = "+description+
                ", featured = "+featured+", best_seller = "+best_seller+", category_id = "+category_id+", brand_id = "+brand_id+", image = "+image+"]";
    }
}
