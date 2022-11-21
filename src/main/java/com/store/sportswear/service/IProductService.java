package com.store.sportswear.service;

import com.store.sportswear.dto.ProductDto;
import com.store.sportswear.dto.SearchProductDto;
import com.store.sportswear.entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Set;

public interface IProductService {
    Product updateProduct(ProductDto dto);
    Product saveProduct(ProductDto dto);
    void deleteProduct(int id);
    Page<Product> getAllProductByFilter(SearchProductDto object, int page, int limit);
    Product getProductById(int id);
    List<Product> getProductLatest();
    Iterable<Product> getProductByNameWithPaginate(SearchProductDto dto);
    Page<Product> getProductByName(SearchProductDto dto, int page,int resultPerPage);
    List<Product> getAllProductByList(Set<Integer> idList);
    Page<Product> getAllProductByNameForAdmin(String nameProduct, int page, int size);
    Iterable<Product> getProductByCategory(String category);
    public Page<Product> getProductByBrand(SearchProductDto dto, int page, int resultPerPage);

}
