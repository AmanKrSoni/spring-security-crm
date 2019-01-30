package com.cvt.controller;

import com.cvt.dao.UsersDao;
import com.cvt.model.Users;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users/rest")
public class UsersRestController {

    @Autowired
    UsersDao usersDao;

    @GetMapping("/list")
    public List<Users> getAllUsers(){

        return usersDao.getAll();
    }
}
