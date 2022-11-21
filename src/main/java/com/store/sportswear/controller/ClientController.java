package com.store.sportswear.controller;

import com.store.sportswear.entity.Categories;
import com.store.sportswear.entity.UserSystem;
import com.store.sportswear.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@SessionAttributes("loggedInUser")
@RequestMapping("/")
public class ClientController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IProductService productService;
    @Autowired
    private ICategoryService categoryService;

    public ClientController(IUserService userService, IProductService productService, ICategoryService categoryService) {
        super();
        this.userService = userService;
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @ModelAttribute("loggedInUser")
    public UserSystem loggedInUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userService.getUserByEmail(authentication.getName());
    }
    public UserSystem getSessionUser(HttpServletRequest request)
    {
        return (UserSystem) request.getSession().getAttribute("loggedInUser");
    }
    @ModelAttribute("category")
    public List<Categories> categoriesList(){
        return categoryService.getAllCategory();
    }
    @GetMapping("/home")
    public String managementHomePage(Model model)
    {
        return "client/home";
    }
    @GetMapping("/login")
    public String managementLoginPage(Model model){
        return "client/login";
    }

}
