package com.store.sportswear.controller;

import com.store.sportswear.entity.UserSystem;
import com.store.sportswear.service.ISecurityService;
import com.store.sportswear.service.IUserService;
import com.store.sportswear.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegisterController {
    @Autowired
    private IUserService userService;
    @Autowired
    private ISecurityService securityService;
    @Autowired
    private UserValidator userValidator;

    public RegisterController(IUserService userService, ISecurityService securityService, UserValidator userValidator) {
        super();
        this.userService = userService;
        this.securityService = securityService;
        this.userValidator = userValidator;
    }

    @GetMapping("/register")
    public String registerPage(Model model){
        model.addAttribute("newUser",new UserSystem());
        return "client/register";
    }
    @PostMapping("/register")
    public String registerProcess(@ModelAttribute("newUser") @Valid UserSystem userSystem, BindingResult bindingResult, Model model){
        userValidator.validate(userSystem,bindingResult);
        if(bindingResult.hasErrors()){
            return "client/register";
        }
        userService.saveUserForMember(userSystem);
        securityService.autoLogin(userSystem.getUser_email(), userSystem.getConfirmPassword());
        return "redirect:/";
    }
}
