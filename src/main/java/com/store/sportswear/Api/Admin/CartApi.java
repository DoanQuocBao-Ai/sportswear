package com.store.sportswear.Api.Admin;

import com.store.sportswear.Entity.*;
import com.store.sportswear.Service.ICartService;
import com.store.sportswear.Service.ICategoryCartService;
import com.store.sportswear.Service.IProductService;
import com.store.sportswear.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api/cart")
@SessionAttributes("loggedInUser")
public class CartApi {

    @Autowired
    private IUserService userService;
    @Autowired
    private ICartService cartService;
    @Autowired
    private IProductService productService;
    @Autowired
    private ICategoryCartService categoryCartService;

    @ModelAttribute("loggedInUser")
    public UserSystem loggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userService.getUserByEmail(authentication.getName());
    }

    public UserSystem getSessionUser(HttpServletRequest request) {
        return (UserSystem) request.getSession().getAttribute("loggedInUser");
    }

    @GetMapping("/addProduct")
    public ResponseObject addToCart(@RequestParam String id, HttpServletRequest request, HttpServletResponse response) {
        ResponseObject responseObject = new ResponseObject();
        Product product = productService.getProductById(Long.parseLong(id));
        if(product.getProduct_price() == 0)
        {
            responseObject.setStatus("false");
            return responseObject;
        }
        UserSystem currentUserSystem = getSessionUser(request);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || authentication.getPrincipal() == "anonymousUser" )    //Su dung cookie de luu
        {
            Cookie clientCookies[] = request.getCookies();
            boolean found = false;
            for(int i=0;i<clientCookies.length;i++)
            {
                if(clientCookies[i].getName().equals(id))
                {
                    clientCookies[i].setValue(Integer.toString(Integer.parseInt(clientCookies[i].getValue())+1));
                    clientCookies[i].setPath("/sportswear");
                    clientCookies[i].setMaxAge(60*60*24*7);
                    response.addCookie(clientCookies[i]);
                    found = true;
                    break;
                }
            }
            if(!found)
            {
                Cookie c = new Cookie(id,"1");
                c.setPath("/sportswear");
                c.setMaxAge(60*60*24*7);
                response.addCookie(c);
            }
        }else {     //Su dung database de luu
            Cart cart = cartService.getCartByUser(currentUserSystem);
            if(cart==null)
            {
                cart = new Cart();
                cart.setUser(currentUserSystem);
                cart = cartService.saveCart(cart);
            }

            Category_Cart category_cart = categoryCartService.getByProductAndCart(product,cart);
            if(category_cart== null)
            {
                System.out.println(cart.getUser().getUser_email());
                System.out.println(cart.getId());
                category_cart = new Category_Cart();
                category_cart.setCart(cart);
                category_cart.setProduct(product);
                category_cart.setNumber(1);
            }else
            {
                category_cart.setNumber(category_cart.getNumber()+1);
            }
            category_cart = categoryCartService.saveCategory_Cart(category_cart);
        }
        responseObject.setStatus("success");
        return responseObject;
    }

    @GetMapping("/changProductQuanity")
    public ResponseObject changeQuanity(@RequestParam String id,@RequestParam String value,HttpServletRequest request,HttpServletResponse response) {
        UserSystem currentUserSystem = getSessionUser(request);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ResponseObject responseObject = new ResponseObject();
        if(authentication == null || authentication.getPrincipal() == "anonymousUser" )
        {
            Cookie clientCookies[] = request.getCookies();
            for(int i=0;i<clientCookies.length;i++)
            {
                if(clientCookies[i].getName().equals(id))
                {
                    clientCookies[i].setValue(value);
                    clientCookies[i].setPath("/sportswear");
                    clientCookies[i].setMaxAge(60*60*24*7);
                    response.addCookie(clientCookies[i]);
                    break;
                }
            }
        }else
        {
            Cart cart = cartService.getCartByUser(currentUserSystem);
            Product product = productService.getProductById(Long.parseLong(id));
            Category_Cart category_cart = categoryCartService.getByProductAndCart(product,cart);
            category_cart.setNumber(Integer.parseInt(value));
            category_cart = categoryCartService.saveCategory_Cart(category_cart);
        }
        responseObject.setStatus("success");
        return responseObject;
    }

    @GetMapping("/deleteFromCart")
    public ResponseObject deleteProduct(@RequestParam String id,HttpServletRequest request,HttpServletResponse response) {
        UserSystem currentUserSystem = getSessionUser(request);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ResponseObject responseObject = new ResponseObject();
        if(authentication == null || authentication.getPrincipal() == "anonymousUser")    //Su dung cookie de luu
        {
            Cookie clientCookies[] = request.getCookies();
            for(int i=0;i<clientCookies.length;i++)
            {
                if(clientCookies[i].getName().equals(id))
                {
                    clientCookies[i].setMaxAge(0);
                    clientCookies[i].setPath("/sportswear");
                    System.out.println(clientCookies[i].getMaxAge());
                    response.addCookie(clientCookies[i]);
                    break;
                }
            }
        }else
        {
            Cart cart = cartService.getCartByUser(currentUserSystem);
            Product product = productService.getProductById(Long.parseLong(id));
            Category_Cart category_cart = categoryCartService.getByProductAndCart(product,cart);
            categoryCartService.deleteCategoryCart(category_cart);
        }

        responseObject.setStatus("success");
        return responseObject;
    }
}
