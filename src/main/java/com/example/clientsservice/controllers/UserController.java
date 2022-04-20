package com.example.clientsservice.controllers;

import com.example.clientsservice.controllers.tools.BootstrapManager;
import com.example.clientsservice.helper.Option;
import com.example.clientsservice.models.Client;
import com.example.clientsservice.models.Phone;
import com.example.clientsservice.models.User;
import com.example.clientsservice.security.UserDetailsServiceImplement;
import com.example.clientsservice.services.data.PhoneService;
import com.example.clientsservice.services.data.qualifiers.PhoneServiceQualifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class UserController {

    @Autowired
    private UserDetailsServiceImplement userService;


    @GetMapping("logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        System.err.println("LOGOUT: ");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth != null)
            new SecurityContextLogoutHandler().logout(request, response, auth);
        return "redirect:";
    }

    @GetMapping("/login")
    public String login(Model model) {
        BootstrapManager.setBootstrapHead(model);
        return "login";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }



    @PostMapping("/register")
    public String addClient(Model model, @RequestParam("login") String login, @RequestParam("password") String password,
                            @RequestParam("email") String email) {
        Set<User.Role> roles = new HashSet<>();
        roles.add(User.Role.USER);
//        roles.add(User.Role.ADMIN);
        password = new BCryptPasswordEncoder().encode(password);
        User user = new User(0L, login, password, email, User.Status.ACTIVE, roles);
        userService.saveUser(user);
        return "redirect:login";
    }

// old
//    @GetMapping("/users")
//    public String userRoles(Model model){
//        BootstrapManager.setBootstrapHead(model);
//        List<User> users = userService.findAll();
//        model.addAttribute("users", users);
//        if(users.isEmpty()) model.addAttribute("no-users", "");
//        return "users";
//    }

    @GetMapping("/users")
    public String userRoles(Model model, @AuthenticationPrincipal Object ap){
        BootstrapManager.setBootstrapHead(model);
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        System.err.println("Users: ");
        System.err.println(ap);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if(users.isEmpty()) model.addAttribute("no-users", "");
        return "users";
    }





    @GetMapping("/edit_user")
    public String editUser(Model model, Long id){
        BootstrapManager.setBootstrapHead(model);
        User user = userService.findById(id);
        model.addAttribute("user", user);
        List<Option> roles = new ArrayList<>();
        for (User.Role role:  User.Role.values() ) {
            roles.add(new Option(role.toString(), user.getRole().contains(role)));
        }
        model.addAttribute("roles", roles);
        return "edit_user";
    }




    @PostMapping("/update_user")
    public String addClient(Model model,@RequestParam("userId") long userId, @RequestParam("roleIds") List<String> roleIds) {
        User user = userService.findById(userId);
        Set<User.Role> roles = new HashSet<>();
        for (String roleId: roleIds ) {
            roles.add(User.Role.valueOf(roleId));
        }
        user.setRole(roles);
        userService.saveUser(user);
        return "redirect:";
    }




}
