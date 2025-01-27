package com.example.todosystem.dto;

import java.time.LocalDateTime;

public class UserRequest {

    private Integer id;
    private String username;
    private String email;
    private String password;
    private Integer picid;
    private LocalDateTime createtime;

    public UserRequest() {
    }

    public UserRequest(String username, String email, String password, Integer picid, LocalDateTime createtime) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.picid = picid;
        this.createtime = createtime;
    }

    public UserRequest(Integer id, String username, String email, String password, Integer picid, LocalDateTime createtime) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.picid = picid;
        this.createtime = createtime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPicid() {
        return picid;
    }

    public void setPicid(Integer picid) {
        this.picid = picid;
    }

    public LocalDateTime getCreatetime() {
        return createtime;
    }

    public void setCreatetime(LocalDateTime createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        return "UserRequset{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", picid=" + picid +
                ", createtime=" + createtime +
                '}';
    }
}
