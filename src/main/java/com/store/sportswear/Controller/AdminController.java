package com.store.sportswear.Controller;

import com.store.sportswear.Dto.WorkListDto;
import com.store.sportswear.Entity.Categories;
import com.store.sportswear.Entity.Role;
import com.store.sportswear.Entity.User;
import com.store.sportswear.Service.*;
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
    @ModelAttribute("loggedInUser")
    public User loggedInUser()
    {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        return userService.getUserByEmail(authentication.getName());
    }
    public User getSessionUser(HttpServletRequest request){
        return (User) request.getSession().getAttribute("loggedInUser");
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
    public String updateUser(@ModelAttribute User user, HttpServletRequest request){
        User currentUser = getSessionUser(request);
        currentUser.setUser_address(user.getUser_address());
        currentUser.setUser_name(user.getUser_name());
        currentUser.setUser_phone(user.getUser_phone());
        userService.updateUser(currentUser);
        return "redirect:/admin/profile";
    }
    @GetMapping("/order")
    public  String managementOrderPage(Model model){
        Set<Role> roles=new HashSet<>();
        roles.add(roleService.getRoleByName("ROLE_SHIPPER"));
        List<User> shippers = userService.getUserByRule(roles);
        for (User shipper:shippers
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
