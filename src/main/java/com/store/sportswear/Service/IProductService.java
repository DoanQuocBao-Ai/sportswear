package com.store.sportswear.Service;

import com.store.sportswear.Dto.ProductDto;
import com.store.sportswear.Dto.SearchProductDto;
import com.store.sportswear.Entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Set;

public interface IProductService {
    Product updateProduct(ProductDto dto);
    Product saveProduct(ProductDto dto);
    void deleteProduct(long id);
    Page<Product> getAllProductByFilter(SearchProductDto object, int page, int limit);
    Product getProductById(long id);
    List<Product> getProductLatest();
    Iterable<Product> getProductByNameWithPaginate(SearchProductDto dto);
    Page<Product> getProductByName(SearchProductDto dto, int page,int resultPerPage);
    List<Product> getAllProductByList(Set<Long> idList);
    Page<Product> getAllProductByNameForAdmin(String nameProduct,int page, int size);
    Iterable<Product> getProductByCategory(String category);
    public Page<Product> getProductByBrand(SearchProductDto dto, int page, int resultPerPage);

}
