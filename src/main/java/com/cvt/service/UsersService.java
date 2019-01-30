package com.cvt.service;

import com.cvt.model.Users;

import java.util.List;

public interface UsersService {
    List<Users> getAllUsers();

    Users getById(String username);

    int updateUsers(Users users);

    int deleteUsers(String username);

    int createUsers(Users users);
}
