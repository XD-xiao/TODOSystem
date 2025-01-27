package com.example.todosystem.pojo;

import java.time.LocalDateTime;

public class User {

    private Integer id;
    private String name;
    private String email;
    private String password;
    private Integer picid;
    private LocalDateTime createdate;

    public User() {
    }

    public User(String name, String email, String password, Integer picid, LocalDateTime createdate) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.picid = picid;
        this.createdate = createdate;
    }

    public User(Integer id, String name, String email, String password, Integer picid, LocalDateTime createdate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.picid = picid;
        this.createdate = createdate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreatedate() {
        return createdate;
    }

    public void setCreatedate(LocalDateTime createdate) {
        this.createdate = createdate;
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



    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", picid=" + picid +
                ", createtime=" + createdate +
                '}';
    }
}
