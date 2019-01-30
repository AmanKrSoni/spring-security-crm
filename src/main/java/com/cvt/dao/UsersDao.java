package com.cvt.dao;

import com.cvt.model.Users;

import java.util.List;

public interface UsersDao {

    List<Users> getAll();

    Users getById(String username);

    int updateUsers(Users users);

    int deleteUsers(String username);

    int createUsers(Users users);


}
