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
    @Override
    public String toString()
    {
        return "SearchProductDto[categoryId = "+categoryId+", brandId = "+brandId+", sale_price = "+sale_price+"]";
    }
}
