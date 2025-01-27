package com.example.todosystem.service;

import com.example.todosystem.pojo.PageBean;
import com.example.todosystem.pojo.Task;

import java.util.List;

public interface TaskService {

    /**
     * 创建任务
     * @param task
     * @return
     */
    public boolean createTask(Task task);

    /**
     * 删除任务
     * @param taskid
     * @return
     */
    public boolean deleteTask(int taskid);

    /**
     * 更新任务
     * @param task
     * @return
     */
    public boolean updateTask(Task task);

    /**
     * 获取任务
     * @param taskid
     * @return
     */
    public Task getTask(int taskid);

    /**
     * 获取任务列表
     * @param userId
     * @param page
     * @param pageSize
     * @return
     */
    public PageBean getTaskListByUserId(Integer userId , Integer page, Integer pageSize);

    /**
     * 获取任务列表
     * @param userId
     * @param searchKey
     * @param page
     * @param pageSize
     * @return
     */
    public PageBean getTaskListBySearch(Integer userId , String searchKey, Integer page, Integer pageSize);

    /**
     * 获取所有任务列表
     * @return
     */
    public List<Task> getAllTaskList();
}
