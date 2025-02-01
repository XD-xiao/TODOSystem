package com.example.todosystem.service.Impl;

import com.example.todosystem.Mapper.TaskMapper;
import com.example.todosystem.pojo.PageBean;
import com.example.todosystem.pojo.Task;
import com.example.todosystem.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskMapper taskMapper;


    @Override
    public boolean createTask(Task task) {
        taskMapper.insertTask(task);
        return true;
    }

    @Override
    public boolean deleteTask(int taskid) {

        taskMapper.deleteTask(taskid);
        return true;

    }

    @Override
    public boolean updateTask(Task task) {

        taskMapper.updateTask(task);
        return true;

    }

    @Override
    public Task getTask(int taskid) {
        return taskMapper.getTask(taskid);
    }

    @Override
    public PageBean getTaskListByUserId(Integer userId, Integer page, Integer pageSize) {
        Long count = (long) taskMapper.getTaskCount(userId);
        List<Task> taskList = taskMapper.getTaskList(userId, (page - 1) * pageSize, pageSize);
        return new PageBean(count, taskList);
    }

    @Override
    public PageBean getTaskListBySearch(Integer userId, String searchKey, Integer page, Integer pageSize) {
        Long count = (long) taskMapper.getTaskCountBySearch(userId, searchKey);
        List<Task> taskList = taskMapper.getTaskListBySearch(userId, searchKey, (page - 1) * pageSize, pageSize);
        return new PageBean(count, taskList);
    }
    @Override
    public List<Task> getAllTaskList() {
        return taskMapper.getAllTaskList();
    }
}
