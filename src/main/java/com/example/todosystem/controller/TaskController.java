package com.example.todosystem.controller;

import com.example.todosystem.dto.TaskRequest;
import com.example.todosystem.pojo.PageBean;
import com.example.todosystem.pojo.Result;
import com.example.todosystem.pojo.Task;
import com.example.todosystem.service.TaskService;
import com.example.todosystem.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("addTask")
    public Result addTask(@RequestBody TaskRequest taskRequest, HttpServletRequest request) {

        Map<String, Object> claims = JwtUtil.parseJWT(request.getHeader("Authorization"));
        Integer userID = (Integer) claims.get("id");

        Task task = new Task(userID, taskRequest.getTitle(), taskRequest.getText(), taskRequest.getTasktime(), taskRequest.getCategoryid(), LocalDateTime.now());

        if (task.getTitle() == null) {
            return Result.error("信息错误");
        }
        if (task.getText() == null) {
            return Result.error("信息错误");
        }
        if (task.getTasktime() == null) {
            return Result.error("信息错误");
        }

        System.out.println(task);
        if (taskService.createTask(task)) {
            return Result.success( "添加成功");
        } else {
            return Result.error("添加失败");
        }
    }

    @PostMapping("deleteTask")
    public Result deleteTask(@RequestBody TaskRequest taskRequest) {
        if (taskService.deleteTask(taskRequest.getTaskid())) {
            return Result.success( "删除成功");
        } else {
            return Result.error("删除失败");
        }
    }

    @PostMapping("updateTask")
    public Result updateTask(@RequestBody TaskRequest taskRequest) {
        Task task = new Task(taskRequest.getTaskid(), taskRequest.getUserid(), taskRequest.getTitle(), taskRequest.getText(), taskRequest.getTasktime(), taskRequest.getCategoryid(), LocalDateTime.now());

        if (taskService.updateTask(task)) {
            return Result.success( "修改成功");
        } else {
            return Result.error("修改失败");
        }
    }

    @PostMapping("getTask")
    public Result getTask(@RequestBody TaskRequest taskRequest, HttpServletRequest request) {

        Map<String, Object> claims = JwtUtil.parseJWT(request.getHeader("Authorization"));
        Integer userID = (Integer) claims.get("id");

        Task task = taskService.getTask(taskRequest.getTaskid());

        if (!Objects.equals(task.getUserid(), userID)){
            return Result.error("权限不足");
        }

        return Result.success(task);
    }

    @PostMapping("getTaskListByUserId")
    public Result getTaskListByUserId(@RequestBody TaskRequest taskRequest) {
        Integer page = taskRequest.getPage();
        Integer pageSize = taskRequest.getPageSize();
        if (page == null) {
            page = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }

        PageBean result = taskService.getTaskListByUserId(taskRequest.getUserid(), page, pageSize);
        if(result != null)
            return Result.success(result);
        return Result.error("获取失败");
    }

    @PostMapping("getTaskListBySearch")
    public Result getTaskListBySearch(@RequestBody TaskRequest taskRequest, HttpServletRequest request) {
        Map<String, Object> claims = JwtUtil.parseJWT(request.getHeader("Authorization"));
        Integer userID = (Integer) claims.get("id");

        Integer page = taskRequest.getPage();
        Integer pageSize = taskRequest.getPageSize();
        if (page == null) {
            page = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        PageBean result = taskService.getTaskListBySearch(userID, taskRequest.getTitle(), page, pageSize);
        if(result != null)
            return Result.success(result);
        return Result.error("获取失败");
    }

}
