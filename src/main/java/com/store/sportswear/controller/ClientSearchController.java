package com.store.sportswear.controller;

import com.store.sportswear.dto.SearchProductDto;
import com.store.sportswear.entity.Product;
import com.store.sportswear.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class ClientSearchController {
    @Autowired
    private IProductService productService;

    public ClientSearchController(IProductService productService) {
        super();
        this.productService = productService;
    }

    @GetMapping("search")
    public String searchProduct(@RequestParam(defaultValue = "1")int page, @RequestParam String name,
                                @RequestParam(defaultValue = "") String sort, @RequestParam(defaultValue = "") String range,
                                @RequestParam(defaultValue = "") String brand, @RequestParam(defaultValue = "") String category, Model model){
        SearchProductDto searchProductDto=new SearchProductDto();
        searchProductDto.setKeyword(name.split(" "));
        searchProductDto.setSort(sort);
        searchProductDto.setSale_price(range);
        searchProductDto.setBrand(brand);
        searchProductDto.setCategory(category);
        Page<Product> list= productService.getProductByName(searchProductDto,page,12);
        int totalPage = list.getTotalPages();
        model.addAttribute("totalPage",totalPage);
        model.addAttribute("list",list.getContent());
        model.addAttribute("currentPage",page);
        model.addAttribute("name",name);
        model.addAttribute("sort",sort);
        model.addAttribute("range",range);
        model.addAttribute("brand",brand);
        model.addAttribute("category",category);
        List<Integer> pagelist = new ArrayList<Integer>();

        if(page==1 || page ==2 || page == 3 || page == 4)
        {
            for(int i = 2; i <=5 && i<=totalPage; i++)
            {
                pagelist.add(i);
            }
        }else if(page == totalPage)
        {
            for(int i = totalPage; i >= totalPage - 3 && i> 1; i--)
            {
                pagelist.add(i);
            }
            Collections.sort(pagelist);
        }else
        {
            for(int i = page; i <= page + 2 && i<= totalPage; i++)
            {
                pagelist.add(i);
            }
            for(int i = page-1; i >= page - 2 && i> 1; i--)
            {
                pagelist.add(i);
            }
            Collections.sort(pagelist);
        }
        model.addAttribute("pageList",pagelist);

        Set<String> categorySet = new HashSet<String>();
        Set<String> brandSet = new HashSet<String>();
        Iterable<Product> dum = productService.getProductByNameWithPaginate(searchProductDto);
        for(Product sp: dum)
        {
            categorySet.add(sp.getCategory().getCategory_name());
            brandSet.add(sp.getBrand().getBrand_name());
        }
        model.addAttribute("category",categorySet);
        model.addAttribute("brand",brandSet);

        return "client/searchResult";
    }
}
