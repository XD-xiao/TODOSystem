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
        try{
            userMapper.insertUser(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Integer id) {
        try{
            userMapper.deleteUser(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean update(User user) {
        try{
            userMapper.updateUser(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public User getUserById(Integer id) {
        return userMapper.getUserById(id);
    }

    @Override
    public User login(User user) {
        try{
            User resUser = userMapper.getUserByEmailAndPassword(user);
            if (resUser != null) {
                return resUser;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
}
