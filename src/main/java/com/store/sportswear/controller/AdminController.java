package com.store.sportswear.controller;

import com.store.sportswear.dto.WorkListDto;
import com.store.sportswear.entity.Role;
import com.store.sportswear.entity.UserSystem;
import com.store.sportswear.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
@SessionAttributes("loggedInUser")
public class AdminController {
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private IBrandService brandService;
    @Autowired
    private IOrderService orderService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;

    public AdminController(ICategoryService categoryService, IBrandService brandService, IOrderService orderService, IUserService userService, IRoleService roleService) {
        super();
        this.categoryService = categoryService;
        this.brandService = brandService;
        this.orderService = orderService;
        this.userService = userService;
        this.roleService = roleService;
    }

    @ModelAttribute("loggedInUser")
    public UserSystem loggedInUser()
    {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        return userService.getUserByEmail(authentication.getName());
    }
    public UserSystem getSessionUser(HttpServletRequest request){
        return (UserSystem) request.getSession().getAttribute("loggedInUser");
    }
    @GetMapping
    public String adminPage(Model model)
    {
        WorkListDto workListDto = new WorkListDto();
        workListDto.setNewContact(orderService.countOrderByStatus("Đang chờ giao"));
        workListDto.setConfirmOrder(orderService.countOrderByStatus("Chờ duyệt"));
        
        model.addAttribute("workList",workListDto);
        return "admin/PageAdmin";
    }
    @GetMapping("/category")
    public String managementCategoryPage()
    {
        return "admin/ManagementCategory";
    }
    @GetMapping("/brand")
    public String managementBrandPage(){
        return "admin/ManagementBrand";
    }
    @GetMapping("/product")
    public String managementProductPage(Model model){
        model.addAttribute("listCategory", categoryService.getAllCategory());
        model.addAttribute("listBrand",brandService.getAllBrand());
        return "admin/ManagementProduct";
    }
    @GetMapping("/profile")
    public String profilePage(Model model, HttpServletRequest request){
        model.addAttribute("user",getSessionUser(request));
        return "admin/profile";
    }
    @PostMapping("/profile/update")
    public String updateUser(@ModelAttribute UserSystem userSystem, HttpServletRequest request){
        UserSystem currentUserSystem = getSessionUser(request);
        currentUserSystem.setUser_address(userSystem.getUser_address());
        currentUserSystem.setUser_name(userSystem.getUser_name());
        currentUserSystem.setUser_phone(userSystem.getUser_phone());
        userService.updateUser(currentUserSystem);
        return "redirect:/admin/profile";
    }
    @GetMapping("/order")
    public  String managementOrderPage(Model model){
        Set<Role> roles=new HashSet<>();
        roles.add(roleService.getRoleByName("ROLE_SHIPPER"));
        List<UserSystem> shippers = userService.getUserByRole(roles);
        for (UserSystem shipper:shippers
             ) {
            shipper.setListOrder(orderService.getOrderByStatusAndShipper("Đang giao",shipper));
        }
        model.addAttribute("allShipper", shippers);
        return "admin/ManagementOrder";
    }
    @GetMapping("/account")
    public String managementAccountPage(Model model){
        model.addAttribute("listRole", roleService.getAllRole());
        return "admin/account";
    }
    @GetMapping("/statistical")
    public String statistical(Model model){
        return "admin/statistical";
    }
}
