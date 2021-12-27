package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.entity.Role;
import web.entity.User;
import web.service.RoleService;
import web.service.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class MyController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping(value = "/login")
    public String getLoginPage() {
        return "login";
    }

    @ModelAttribute("newUser")
    public User getPerson(){
        return new User();
    }

    @RequestMapping("/admin")
    public String showAllUsers(Model model, @CurrentSecurityContext(expression = "authentication.principal") User principal){
        List<User> allUsers = userService.getAllUsers();
        model.addAttribute("allUsers", allUsers);
        model.addAttribute("roles", roleService.getAllRoles());
        model.addAttribute("aboutUserInfo", principal.getName() + " with roles: " + principal.getRoles());;
        model.addAttribute("thisUser", principal);
        return "users";

    }

    //@RequestMapping("/admin")
    //public String showAllUsersAdmin(Model model){
    //    List<User> allUsers = userService.getAllUsers();
    //    model.addAttribute("allUsers", allUsers);

    //    return "all-users";

    //}

    @GetMapping("/user")
    public String showUserInfo(@CurrentSecurityContext(expression = "authentication.principal") User principal,
                               Model model) {
        model.addAttribute("user", principal);
        model.addAttribute("aboutUserInfo", principal.getName() + " with roles: " + principal.getRoles());
        model.addAttribute("thisUser", principal);
        return "user";
    }

    @RequestMapping("/admin/addNewUser")
    public String addNewUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);

        return "edit-user-info";

    }

    @RequestMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user, @RequestParam("selectedRole") String rolesName) {
        Set<Role> setRoles= new HashSet<Role>();
        setRoles.add(roleService.getRoleByName(rolesName));
        user.setRoles(setRoles);
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @RequestMapping("/updateUser")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @PatchMapping("/{id}")
    public String updatePerson(@ModelAttribute("user") User updateuser){
        userService.saveUser(updateuser);
        return "redirect:/admin";
    }


    @GetMapping("/admin/{id}/edit")
    public String updateInfo(@ModelAttribute("id") int id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "edit-user-info";
    }

    @RequestMapping("/admin/deleteUser")
    public String deleteUser(@ModelAttribute("id") int id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
