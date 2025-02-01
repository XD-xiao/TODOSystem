package com.example.todosystem.controller;

import com.example.todosystem.dto.CategoryRequest;
import com.example.todosystem.pojo.Category;
import com.example.todosystem.pojo.Result;
import com.example.todosystem.service.CategoryService;
import com.example.todosystem.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Objects;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("addCategory")
    public Result addCategory(@RequestBody CategoryRequest categoryRequest, HttpServletRequest request)
    {
        Map<String, Object> claims = JwtUtil.parseJWT(request.getHeader("Authorization"));
        Integer userID = (Integer) claims.get("id");
        Category category = new Category(userID,categoryRequest.getClassname(),categoryRequest.getColor());

        categoryService.createCategory(category);

        return Result.success("添加成功");

    }

    @PostMapping("deleteCategory")
    public Result deleteCategory(@RequestBody CategoryRequest categoryRequest, HttpServletRequest request)
    {

        Map<String, Object> claims = JwtUtil.parseJWT(request.getHeader("Authorization"));
        Integer userID = (Integer) claims.get("id");

        Category category = categoryService.getCategory(categoryRequest.getCategoryid());

        if( category == null )
            return Result.error("没有该类别");
        else if(!Objects.equals(category.getUserid(), userID))
            return Result.error("没有权限");

        categoryService.deleteCategory(categoryRequest.getCategoryid());

        return Result.success();
    }

    @PostMapping("updateCategory")
    public Result updateCategory(@RequestBody CategoryRequest categoryRequest, HttpServletRequest request)
    {

        Map<String, Object> claims = JwtUtil.parseJWT(request.getHeader("Authorization"));
        Integer userID = (Integer) claims.get("id");

        Category category = categoryService.getCategory(categoryRequest.getCategoryid());
        category.setClassname(categoryRequest.getClassname());
        category.setColor(categoryRequest.getColor());

        if(!Objects.equals(categoryRequest.getUserid(), userID))
            return Result.error("没有权限");

        categoryService.updateCategory(category);
        return Result.success();

    }

    @PostMapping("getCategory")
    public Result getCategory(@RequestBody CategoryRequest categoryRequest)
    {
        Category category = categoryService.getCategory(categoryRequest.getCategoryid());
        if(category != null)
        {
            return Result.success(category);
        }
        else
        {
            return Result.error("获取失败");
        }
    }

    @PostMapping("getCategoryListByUserId")
    public Result getCategoryListByUserId(@RequestBody CategoryRequest categoryRequest, HttpServletRequest request)
    {
        Map<String, Object> claims = JwtUtil.parseJWT(request.getHeader("Authorization"));
        Integer userID = (Integer) claims.get("id");

        return Result.success(categoryService.getCategoryListByUserId(userID));
    }

}
