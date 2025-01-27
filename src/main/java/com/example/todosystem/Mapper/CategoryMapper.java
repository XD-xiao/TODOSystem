package com.example.todosystem.Mapper;

import com.example.todosystem.pojo.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryMapper {

    @Insert("insert into category(userid,classname,color) values(#{userid},#{classname},#{color})")
    public void insertCategory(Category category);

    @Delete("delete from category where categoryid=#{categoryid}")
    public void deleteCategory(int categoryid);

    @Update("update category set classname=#{classname},color=#{color} where categoryid=#{categoryid}")
    public void updateCategory(Category category);

    @Select("select * from category where categoryid=#{categoryid}")
    public Category getCategory(int categoryid);

    @Select("select * from category where userid=#{userid}")
    public List<Category> getCategoryListByUserId(int userid);


}
