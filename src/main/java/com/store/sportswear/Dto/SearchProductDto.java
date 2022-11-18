package com.store.sportswear.Dto;

public class SearchProductDto {
    private String categoryId;
    private String brandId;
    private String price;
    private String sale_price;

    private String sortSalePrice;
    private String[] keyword;
    private String sort;

    private String category;
    private String brand;
    public SearchProductDto()
    {
        categoryId= "";
        brandId= "";
        sale_price ="";
        sortSalePrice= "ASC";
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSale_price() {
        return sale_price;
    }

    public void setSale_price(String sale_price) {
        this.sale_price = sale_price;
    }

    public String getSortSalePrice() {
        return sortSalePrice;
    }

    public void setSortSalePrice(String sortSalePrice) {
        this.sortSalePrice = sortSalePrice;
    }

    public String[] getKeyword() {
        return keyword;
    }

    public void setKeyword(String[] keyword) {
        this.keyword = keyword;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String toString()
    {
        return "SearchProductDto[categoryId = "+categoryId+", brandId = "+brandId+", sale_price = "+sale_price+"]";
    }
}
