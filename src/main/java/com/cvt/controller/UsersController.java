package com.cvt.controller;

import com.cvt.model.Users;
import com.cvt.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UsersController {

    @Autowired
    UsersService usersService;


    @RequestMapping("/list")
    public String showUsersList(Model model){

        //get users from service
        List<Users> usersList=usersService.getAllUsers();

        //add users to model
        model.addAttribute("users",usersList);
        return "users-list";
    }

    @GetMapping("/showFormForAdd")
    @PreAuthorize("hasAnyRole('ADMIN') or #username==authentication.principal.username ")
    public String showFormForAdd(Model model,String username){
        username=getUserLogInInfo();
        model.addAttribute("users",new Users());
        return "users-add";
    }

    @GetMapping("/save")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public String saveUsers(@ModelAttribute("users") Users users){
        usersService.createUsers(users);
        return "redirect:/users/list";
    }

    @GetMapping("/update")
    @PreAuthorize("hasAnyRole('ADMIN') or #users.username==authentication.principal.username")
    public String updateUsers(@ModelAttribute("users") Users users){
        usersService.updateUsers(users);
        return "redirect:/users/list";
    }

    @GetMapping("showFormForUpdate")
    @PreAuthorize("hasAnyRole('ADMIN') or #username==authentication.principal.username ")
    public String showFormForUpdate(@RequestParam("username") String username, Model model){

        //get users from service
       Users users=usersService.getById(username);

        //set users as model attribute
        model.addAttribute("users",users);

        //send over to form
        return "users-update";
    }

    @GetMapping("/delete")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public String deleteUsers(@RequestParam("username") String username){
        usersService.deleteUsers(username);
        return "redirect:/users/list";
    }

    public String getUserLogInInfo(){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String user=securityContext.getAuthentication().getName();
        return user;
    }
}
