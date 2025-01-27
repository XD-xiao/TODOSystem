package com.example.todosystem.service;

import com.example.todosystem.pojo.User;

public interface UserService {

    /**
     * 注册
     * @param user
     * @return
     */
    public boolean register(User user);

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean delete(Integer id);

    /**
     * 修改
     * @param user
     * @return
     */
    public boolean update(User user);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    public User getUserById(Integer id);

    /**
     * 登录
     * @param user
     * @return
     */
    public User login(User user);

}
