package com.store.sportswear.controller;

import com.store.sportswear.entity.*;
import com.store.sportswear.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
@SessionAttributes("loggedInUser")
public class CheckOutController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IProductService productService;
    @Autowired
    private ICartService cartService;
    @Autowired
    private ICategoryCartService categoryCartService;
    @Autowired
    private IOrderService orderService;
    @Autowired
    private IOrderDetailService orderDetailService;

    public CheckOutController(IUserService userService, IProductService productService, ICartService cartService, ICategoryCartService categoryCartService, IOrderService orderService, IOrderDetailService orderDetailService) {
        super();
        this.userService = userService;
        this.productService = productService;
        this.cartService = cartService;
        this.categoryCartService = categoryCartService;
        this.orderService = orderService;
        this.orderDetailService = orderDetailService;
    }

    @ModelAttribute("loggedInUser")
    public UserSystem loggedInUser(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        return userService.getUserByEmail(authentication.getName());
    }
    public UserSystem getSessionUser(HttpServletRequest request){
        return (UserSystem) request.getSession().getAttribute("loggedInUser");
    }
    @GetMapping("/checkout")
    public String checkoutPage(HttpServletRequest request, Model model){
        UserSystem currentUserSystem = getSessionUser(request);
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        Map<Integer,String>quanity=new HashMap<Integer,String>();
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
            if(cart!=null){
                List<Category_Cart>category_cartList=categoryCartService.getByCart(cart);
                for(Category_Cart c:category_cartList){
                    list.add(c.getProduct());
                    quanity.put(c.getProduct().getId(), Integer.toString(c.getNumber()));
                }
            }
        }
        model.addAttribute("cart",list);
        model.addAttribute("quanity", quanity);
        model.addAttribute("user", currentUserSystem);
        model.addAttribute("order",new Order());
        return "client/checkout";
    }
    public void cleanUpAfterCheckout(HttpServletRequest request, HttpServletResponse response){
        UserSystem currentUserSystem =getSessionUser(request);
        Authentication authentication=SecurityContextHolder.createEmptyContext().getAuthentication();
        if(authentication==null||authentication.getPrincipal()=="anonymousUser"){
            Cookie cookie[]= request.getCookies();
            for(int i=0;i<cookie.length;i++){
                cookie[i].setMaxAge(0);
                cookie[i].setPath("/sportswear");
                response.addCookie(cookie[i]);
            }
        }else{
            Cart cart=cartService.getCartByUser(currentUserSystem);
            List<Category_Cart> category_cartList=categoryCartService.getByCart(cart);
            categoryCartService.deleteAllCategoryCart(category_cartList);
        }
    }
    @PostMapping(value="/thankyou")
    public String thankyouPage(@ModelAttribute("order") Order order,HttpServletResponse response, HttpServletRequest request,Model model) {
        order.setOrder_at(new Date());
        order.setStatus("Đang chờ giao");
        UserSystem currentUserSystem = getSessionUser(request);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Map<Integer, String> quanity = new HashMap<Integer, String>();
        List<Product> list = new ArrayList<Product>();
        List<Order_Detail> order_detailList = new ArrayList<Order_Detail>();
        if (authentication == null || authentication.getPrincipal() == "anonymousUser") {
            Order o = orderService.saveOrder(order);
            Cookie cookie[] = request.getCookies();
            Set<Integer> idList = new HashSet<Integer>();
            for (int i = 0; i < cookie.length; i++) {
                if (cookie[i].getName().matches("[0-9]+")) {
                    idList.add(Integer.parseInt(cookie[i].getName()));
                    quanity.put(Integer.parseInt(cookie[i].getName()), cookie[i].getValue());
                }
            }
            list = productService.getAllProductByList(idList);
            for (Product product : list) {
                Order_Detail order_detail = new Order_Detail();
                order_detail.setProduct(product);
                order_detail.setNumber(Integer.parseInt(quanity.get(product.getId())));
                order_detail.setPrice(Integer.parseInt(quanity.get(product.getId())) * product.getSale_price());
                order_detail.setOrder(o);
                order_detailList.add(order_detail);
            }
        } else {
            order.setUser(currentUserSystem);
            Order od = orderService.saveOrder(order);
            Cart g = cartService.getCartByUser(currentUserSystem);
            List<Category_Cart> category_cartList = categoryCartService.getByCart(g);
            for (Category_Cart category_cart : category_cartList) {
                Order_Detail order_detail = new Order_Detail();
                order_detail.setProduct(category_cart.getProduct());
                order_detail.setPrice(category_cart.getNumber() * category_cart.getProduct().getSale_price());
                order_detail.setNumber(category_cart.getNumber());
                order_detail.setOrder(od);
                order_detailList.add(order_detail);

                list.add(category_cart.getProduct());
                quanity.put(category_cart.getProduct().getId(), Integer.toString(category_cart.getNumber()));
            }
        }
        orderDetailService.saveOrderDetail(order_detailList);
        cleanUpAfterCheckout(request, response);
        model.addAttribute("order", order);
        model.addAttribute("cart", list);
        model.addAttribute("quanity", quanity);
        return "client/thankYou";
    }
}
