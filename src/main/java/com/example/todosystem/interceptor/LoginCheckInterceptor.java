package com.example.todosystem.interceptor;


import com.example.todosystem.pojo.Result;
import com.example.todosystem.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Component
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String AuthToken = request.getHeader("Authorization");

        if(AuthToken==null || AuthToken.isEmpty() ){
            Result.error("未登录");
            return true;
        }

//        System.out.println(AuthToken);
        try{
            Map<String, Object> claims = JwtUtil.parseJWT(AuthToken);
        } catch (Exception e){
            // 返回错误
            response.getWriter().write("Authorization无效");
            Result.error("Authorization无效");
            return false;
        }

//        System.out.println("最后的True");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
