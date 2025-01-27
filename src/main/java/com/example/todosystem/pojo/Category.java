package com.example.todosystem.pojo;

public class Category {
    private Integer categoryid;
    private Integer userid;
    private String classname;
    private String color;

    public Category() {
    }

    public Category(Integer userid, String classname, String color) {
        this.userid = userid;
        this.classname = classname;
        this.color = color;
    }

    public Category(Integer categoryid, Integer userid, String classname, String color) {
        this.categoryid = categoryid;
        this.userid = userid;
        this.classname = classname;
        this.color = color;
    }

    public Integer getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "category{" +
                "categoryid=" + categoryid +
                ", userid=" + userid +
                ", classname='" + classname + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
