package com.store.sportswear.Controller;

import com.store.sportswear.Entity.UserSystem;
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
    public UserSystem loggedInUser(){
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
    public String updateProfile(@ModelAttribute UserSystem userSystem, HttpServletRequest request)
    {
        UserSystem currentUserSystem = new UserSystem();
        currentUserSystem.setUser_name(userSystem.getUser_name());
        currentUserSystem.setUser_phone(userSystem.getUser_phone());
        currentUserSystem.setUser_address(userSystem.getUser_address());
        userService.updateUser(currentUserSystem);
        return "redirect:/shipper/profile";
    }
    public UserSystem getSessionUser(HttpServletRequest request){
        return (UserSystem) request.getSession().getAttribute("loggedInUser");
    }
}
