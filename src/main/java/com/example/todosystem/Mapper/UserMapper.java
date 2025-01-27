package com.example.todosystem.Mapper;

import com.example.todosystem.pojo.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Insert("INSERT INTO user(name, email , password , picid , createdate) VALUES(#{name}, #{email}, #{password}, #{picid},#{createdate})")
    public void insertUser(User user);

    @Delete("DELETE FROM user WHERE id = #{id}")
    public void deleteUser(Integer id);

    @Update("UPDATE user SET name = #{name},email = #{email}, password = #{password} ,picid = #{picid} WHERE id = #{id}")
    public void updateUser(User user);

    @Select("SELECT * FROM user WHERE id = #{id}")
    public User getUserById(Integer id);

    @Select("SELECT * FROM user WHERE email = #{email} AND password = #{password}")
    public User getUserByEmailAndPassword(User user);

}
