package com.store.sportswear.controller;

import com.store.sportswear.dto.ChangePasswordDto;
import com.store.sportswear.entity.ResponseObject;
import com.store.sportswear.entity.UserSystem;
import com.store.sportswear.service.IOrderService;
import com.store.sportswear.service.IProductService;
import com.store.sportswear.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@SessionAttributes("loggedInUser")
@RequestMapping("/")
public class AccountClientController {
    @Autowired
    private IProductService productService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IOrderService orderService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public AccountClientController(IProductService productService, IUserService userService, IOrderService orderService, BCryptPasswordEncoder passwordEncoder) {
        super();
        this.productService = productService;
        this.userService = userService;
        this.orderService = orderService;
        this.passwordEncoder = passwordEncoder;
    }

    @ModelAttribute("loggedInUser")
    public UserSystem loggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userService.getUserByEmail(authentication.getName());
    }

    public UserSystem getSessionUser(HttpServletRequest request) {
        return (UserSystem) request.getSession().getAttribute("loggedInUser");
    }

    @GetMapping("/account")
    public String accountPage(Model model, HttpServletRequest request) {
        UserSystem currentUserSystem = getSessionUser(request);
        model.addAttribute("user", currentUserSystem);
        return "client/account";
    }

    @GetMapping("/changeInformation")
    public String clientChangeInformationPage(HttpServletRequest request, Model model) {
        UserSystem currentUserSystem = getSessionUser(request);
        model.addAttribute("user", currentUserSystem);
        return "client/information";
    }

    @PostMapping("/updateInformation")
    @ResponseBody
    public ResponseObject commitChange(HttpServletRequest request, @RequestBody UserSystem userSystem) {
        UserSystem currentUserSystem = getSessionUser(request);
        currentUserSystem.setUser_name(userSystem.getUser_name());
        currentUserSystem.setUser_phone(userSystem.getUser_phone());
        currentUserSystem.setUser_address(userSystem.getUser_address());
        userService.updateUser(currentUserSystem);
        return new ResponseObject();
    }

    @GetMapping("/changePassword")
    public String clientChangePassword() {

        return "client/passwordChange";
    }

    @PostMapping("/updatePassword")
    @ResponseBody
    public ResponseObject passwordChange(HttpServletRequest request, @RequestBody ChangePasswordDto dto) {
        UserSystem currentUSer = getSessionUser(request);
        if(!passwordEncoder.matches(dto.getOldPassword(), currentUSer.getUser_password())){
            ResponseObject responseObject=new ResponseObject();
            responseObject.setStatus("old");
            return responseObject;
        }
        userService.changePassword(dto.getNewPassword(), currentUSer);
        return new ResponseObject();
    }

}
