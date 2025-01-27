package com.example.todosystem.pojo;

import java.time.LocalDateTime;

public class Task {

    private Integer taskid;
    private Integer userid;
    private String title;
    private String text;
    private String tasktime;
    private Integer categoryid;
    private LocalDateTime createdate;

    public Task() {
    }

    public Task(Integer userid, String title, String text, String tasktime, Integer categoryid, LocalDateTime createdate) {
        this.userid = userid;
        this.title = title;
        this.text = text;
        this.tasktime = tasktime;
        this.categoryid = categoryid;
        this.createdate = createdate;
    }

    public Task(Integer taskid, Integer userid, String title, String text, String tasktime, Integer categoryid, LocalDateTime createdate) {
        this.taskid = taskid;
        this.userid = userid;
        this.title = title;
        this.text = text;
        this.tasktime = tasktime;
        this.categoryid = categoryid;
        this.createdate = createdate;
    }

    public Integer getTaskid() {
        return taskid;
    }

    public void setTaskid(Integer taskid) {
        this.taskid = taskid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTasktime() {
        return tasktime;
    }

    public void setTasktime(String tasktime) {
        this.tasktime = tasktime;
    }

    public Integer getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
    }

    public LocalDateTime getCreatedate() {
        return createdate;
    }

    public void setCreatedate(LocalDateTime createdate) {
        this.createdate = createdate;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskid=" + taskid +
                ", userid='" + userid + '\'' +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", tasktime='" + tasktime + '\'' +
                ", categoryid=" + categoryid +
                ", createdate=" + createdate +
                '}';
    }
}
