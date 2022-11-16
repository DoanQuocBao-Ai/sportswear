package com.store.sportswear.Controller;

import com.store.sportswear.Entity.User;
import com.store.sportswear.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@SessionAttributes("loggedInUser")
@RequestMapping("/shipper")
public class ShipperController {
    @Autowired
    private IUserService userService;
    @ModelAttribute("loggedInUser")
    public User loggedInUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userService.getUserByEmail(authentication.getName());
    }
    @GetMapping(value = {"","/order"})
    public String shipperPage(Model model){
        return "shipper/order";
    }
    @GetMapping("/profile")
    public String profilePage(Model model, HttpServletRequest request){
        model.addAttribute("user",getSessionUser(request));
        return "shipper/profile";
    }
    @PostMapping("/updateProfile")
    public String updateProfile(@ModelAttribute User user, HttpServletRequest request)
    {
        User currentUser = new User();
        currentUser.setUser_name(user.getUser_name());
        currentUser.setUser_phone(user.getUser_phone());
        currentUser.setUser_address(user.getUser_address());
        userService.updateUser(currentUser);
        return "redirect:/shipper/profile";
    }
    public User getSessionUser(HttpServletRequest request){
        return (User) request.getSession().getAttribute("loggedInUser");
    }
}
