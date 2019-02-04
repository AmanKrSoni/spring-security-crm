package com.cvt.controller;

import com.cvt.dao.UsersDao;
import com.cvt.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users/rest")
public class UsersRestController {

    @Autowired
    UsersDao usersDao;

    //get user_list info if has manager to read_only privlieges or has admin authority
    @PreAuthorize("hasPermission(#users,'READ_ONLY') or hasAnyRole('ADMIN')")
    @PostFilter("filterObject.username!=authentication.name or hasAnyRole('ADMIN')")
    @GetMapping("/list")
    public List<Users> getAllUsers(Users users){
        return usersDao.getAll();
    }

    //get user LoggedIn Info
    @GetMapping("/get")
    @PostAuthorize("returnObject.name==authentication.name ")
    public Users getuserInfo(){
        Users users;
        String principal = SecurityContextHolder.getContext().getAuthentication().getName();

            users=usersDao.getById(principal);
            return users;
    }



}
