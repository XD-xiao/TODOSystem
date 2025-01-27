package com.example.todosystem.controller;

import com.example.todosystem.dto.UserRequest;
import com.example.todosystem.pojo.Result;
import com.example.todosystem.pojo.User;
import com.example.todosystem.service.UserService;
import com.example.todosystem.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/register")
    public Result register(@RequestBody UserRequest userRequest) {

        User user = new User(userRequest.getUsername(), userRequest.getEmail(), userRequest.getPassword(), userRequest.getPicid(), LocalDateTime.now());

        if (userService.register(user)) {
            return Result.success( "注册成功");
        } else {
            return  Result.error("注册失败");
        }
    }

    @PostMapping("/logoff")
    public Result logoff(@RequestBody UserRequest userRequest, HttpServletRequest request) {

        Map<String, Object> claims = JwtUtil.parseJWT(request.getHeader("Authorization"));
        Integer userID = (Integer) claims.get("id");

        if (!Objects.equals(userID, userRequest.getId())) {
            return Result.error("权限不足");
        }

        if (userService.delete(userRequest.getId())) {
            return Result.success("注销成功");
        } else {
            return Result.error("注销失败");
        }
    }

    @PostMapping("/update")
    public Result update(@RequestBody UserRequest userRequest, HttpServletRequest request) {

        Map<String, Object> claims = JwtUtil.parseJWT(request.getHeader("Authorization"));
        Integer userID = (Integer) claims.get("id");
//        System.out.println(userID);

        if (!Objects.equals(userID, userRequest.getId())) {
            return Result.error("权限不足");
        }

        User user = new User(userRequest.getId(), userRequest.getUsername(), userRequest.getEmail(), userRequest.getPassword(), userRequest.getPicid(), userRequest.getCreatetime());

        if (userService.update(user)) {
            return Result.success( "修改成功");
        } else {
            return Result.error("修改失败");
        }
    }

    @PostMapping("/getUserById")
    public Result getUserById(@RequestBody UserRequest userRequest, HttpServletRequest request) {

        Map<String, Object> claims = JwtUtil.parseJWT(request.getHeader("Authorization"));
        Integer userID = (Integer) claims.get("id");

        User user = userService.getUserById(userID);

        if (user != null) {
            System.out.println(user);
            return Result.success(user);
        } else {
            return Result.error("获取失败");
        }
    }

    @PostMapping("/login")
    public Result login(@RequestBody UserRequest userRequest) {

        User user = new User(userRequest.getUsername(), userRequest.getEmail(), userRequest.getPassword(), userRequest.getPicid(), LocalDateTime.now());

        User resUser = userService.login(user);

        if (resUser != null) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", resUser.getId());

            String AuthToken = JwtUtil.getJWT(claims);

            return Result.success(AuthToken);
        } else {
            return Result.error("登录失败");
        }
    }

}
