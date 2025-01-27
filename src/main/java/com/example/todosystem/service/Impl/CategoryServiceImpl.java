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
        try {
            categoryMapper.insertCategory(category);
            return true;
        } catch (Exception e) {
//            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteCategory(int categoryid) {
        try {
            categoryMapper.deleteCategory(categoryid);
            return true;
        } catch (Exception e) {
//            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateCategory(Category category) {
        try {
            categoryMapper.updateCategory(category);
            return true;
        } catch (Exception e) {
//            e.printStackTrace();
            return false;
        }
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
