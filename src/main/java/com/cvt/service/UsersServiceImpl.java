package com.cvt.service;

import com.cvt.dao.UsersDao;
import com.cvt.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersDao usersDao;

    @Override
    public List<Users> getAllUsers() {
        return usersDao.getAll();
    }

    @Override
    public Users getById(String username) {
        return usersDao.getById(username);
    }

    @Override
    public int updateUsers(Users users) {
        return usersDao.updateUsers(users);
    }

    @Override
    public int deleteUsers(String username) {
        return usersDao.deleteUsers(username);
    }

    @Override
    public int createUsers(Users users) {

        return usersDao.createUsers(users);
    }
}
