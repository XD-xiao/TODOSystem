package com.example.todosystem.service;

import com.example.todosystem.pojo.Category;

import java.util.List;

public interface CategoryService {

    /**
     * 创建类别
     * @param category
     * @return
     */
    public boolean createCategory(Category category);

    /**
     * 删除类别
     * @param categoryid
     * @return
     */
    public boolean deleteCategory(int categoryid);

    /**
     * 更新类别
     * @param category
     * @return
     */
    public boolean updateCategory(Category category);

    /**
     * 获取类别
     * @param categoryid
     * @return
     */
    public Category getCategory(int categoryid);

    /**
     * 获取类别列表
     * @param userid
     * @return
     */
    public List<Category> getCategoryListByUserId(int userid);

}
