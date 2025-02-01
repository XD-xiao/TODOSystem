package com.example.todosystem.service.Impl;

import com.example.todosystem.Mapper.CategoryMapper;
import com.example.todosystem.pojo.Category;
import com.example.todosystem.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;


    @Override
    public boolean createCategory(Category category) {

        categoryMapper.insertCategory(category);
        return true;

    }

    @Override
    public boolean deleteCategory(int categoryid) {

        categoryMapper.deleteCategory(categoryid);
        return true;

    }

    @Override
    public boolean updateCategory(Category category) {

        categoryMapper.updateCategory(category);
        return true;

    }

    @Override
    public Category getCategory(int categoryid) {
        return categoryMapper.getCategory(categoryid);
    }

    @Override
    public List<Category> getCategoryListByUserId(int userid) {
        return categoryMapper.getCategoryListByUserId(userid);
    }
}
