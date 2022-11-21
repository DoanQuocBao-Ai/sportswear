package com.store.sportswear.controller;

import com.store.sportswear.entity.Cart;
import com.store.sportswear.entity.Category_Cart;
import com.store.sportswear.entity.Product;
import com.store.sportswear.entity.UserSystem;
import com.store.sportswear.service.ICartService;
import com.store.sportswear.service.ICategoryCartService;
import com.store.sportswear.service.IProductService;
import com.store.sportswear.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@SessionAttributes("loggedInUser")
public class CartController {
    @Autowired
    private IProductService productService;
    @Autowired
    private IUserService userService;
    @Autowired
    private ICartService cartService;
    @Autowired
    private ICategoryCartService categoryCartService;

    public CartController(IProductService productService, IUserService userService, ICartService cartService, ICategoryCartService categoryCartService) {
        super();
        this.productService = productService;
        this.userService = userService;
        this.cartService = cartService;
        this.categoryCartService = categoryCartService;
    }

    @ModelAttribute("loggedInUser")
    public UserSystem loggedInUser(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        return userService.getUserByEmail(authentication.getName());
    }
    public UserSystem getSessionUser(HttpServletRequest request){
        return (UserSystem) request.getSession().getAttribute("loggedInUser");
    }
    @GetMapping("/cart")
    public  String cartPage(HttpServletRequest request, Model model){
        UserSystem currentUserSystem =getSessionUser(request);
        Authentication authentication=SecurityContextHolder.createEmptyContext().getAuthentication();
        Map<Integer,String> quanity=new HashMap<Integer,String>();
        List<Product> list=new ArrayList<Product>();
        if(authentication==null||authentication.getPrincipal()=="anonymousUser"){
            Cookie cookie[]=request.getCookies();
            Set<Integer> idList=new HashSet<Integer>();
            for(int i=0; i<cookie.length;i++){
                if(cookie[i].getName().matches("[0-9]+")){
                    idList.add(Integer.parseInt(cookie[i].getName()));
                    quanity.put(Integer.parseInt(cookie[i].getName()),cookie[i].getValue());
                }
            }
            list=productService.getAllProductByList(idList);
        }else{
            Cart cart=cartService.getCartByUser(currentUserSystem);
            if(cart !=null){
                List<Category_Cart> category_cartList=categoryCartService.getByCart(cart);
                for(Category_Cart c: category_cartList){
                    list.add(c.getProduct());
                    quanity.put(c.getProduct().getId(),Integer.toString(c.getNumber()));
                }
            }
        }
        model.addAttribute("checkEmpty",list.size());
        model.addAttribute("cart",list);
        model.addAttribute("quanity",quanity);
        return "client/cart";
    }
}
