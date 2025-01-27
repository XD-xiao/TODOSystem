package com.example.todosystem.Mapper;

import com.example.todosystem.pojo.Task;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TaskMapper {

    @Insert("INSERT INTO task (userid, title, text, tasktime, categoryid,createdate) value (#{userid}, #{title},#{text},#{tasktime},#{categoryid},#{createdate})")
    public void insertTask(Task task);

    @Delete("DELETE FROM task WHERE taskid = #{taskid}")
    public void deleteTask(int taskid);

    @Update("UPDATE task SET title = #{title}, text = #{text}, tasktime = #{tasktime}, categoryid = #{categoryid} WHERE taskid = #{taskid}")
    public void updateTask(Task task);

    @Select("SELECT * FROM task WHERE taskid = #{taskid}")
    public Task getTask(int taskid);

    @Select("SELECT * FROM task WHERE userid = #{userid} LIMIT #{start},#{pageSize}")
    public List<Task> getTaskList(int userid , int start, int pageSize);

    @Select("SELECT COUNT(*) FROM task WHERE userid = #{userid}")
    public int getTaskCount(int userid);

    @Select("SELECT * FROM task WHERE userid = #{userid} AND title like CONCAT('%', #{title}, '%') LIMIT #{start},#{pageSize}")
    public List<Task> getTaskListBySearch(int userid, String title, int start, int pageSize);

    @Select("SELECT COUNT(*) FROM task WHERE userid = #{userid} AND title like CONCAT('%', #{title}, '%')")
    public int getTaskCountBySearch(int userid, String title);

    @Select("SELECT * FROM task")
    public List<Task> getAllTaskList();

}
