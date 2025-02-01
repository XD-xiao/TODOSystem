package com.example.todosystem.service.Impl;

import com.example.todosystem.Mapper.UserMapper;
import com.example.todosystem.pojo.User;
import com.example.todosystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean register(User user) {

        userMapper.insertUser(user);
        return true;

    }

    @Override
    public boolean delete(Integer id) {

        userMapper.deleteUser(id);
        return true;

    }

    @Override
    public boolean update(User user) {
        userMapper.updateUser(user);
        return true;
    }

    @Override
    public User getUserById(Integer id) {
        return userMapper.getUserById(id);
    }

    @Override
    public User login(User user) {

        User resUser = userMapper.getUserByEmailAndPassword(user);
        if (resUser != null) {
            return resUser;
        } else {
            return null;
        }

    }
}
