package com.store.sportswear.Controller;

import com.store.sportswear.Entity.Cart;
import com.store.sportswear.Entity.Category_Cart;
import com.store.sportswear.Entity.Product;
import com.store.sportswear.Entity.UserSystem;
import com.store.sportswear.Service.ICartService;
import com.store.sportswear.Service.ICategoryCartService;
import com.store.sportswear.Service.IProductService;
import com.store.sportswear.Service.IUserService;
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
        Map<Long,String> quanity=new HashMap<Long,String>();
        List<Product> list=new ArrayList<Product>();
        if(authentication==null||authentication.getPrincipal()=="anonymousUser"){
            Cookie cookie[]=request.getCookies();
            Set<Long> idList=new HashSet<Long>();
            for(int i=0; i<cookie.length;i++){
                if(cookie[i].getName().matches("[0-9]+")){
                    idList.add(Long.parseLong(cookie[i].getName()));
                    quanity.put(Long.parseLong(cookie[i].getName()),cookie[i].getValue());
                }
            }
            list=productService.getAllProductByList(idList);
        }else{
            Cart cart=cartService.getCartByUser(currentUserSystem);
            if(cart !=null){
                List<Category_Cart> category_cartList=categoryCartService.getByCart(cart);
                for(Category_Cart c: category_cartList){
                    list.add(c.getProduct());
                    quanity.put((c.getProduct().getId()),Integer.toString(c.getNumber()));
                }
            }
        }
        model.addAttribute("checkEmpty",list.size());
        model.addAttribute("cart",list);
        model.addAttribute("quanity",quanity);
        return "client/cart";
    }
}
