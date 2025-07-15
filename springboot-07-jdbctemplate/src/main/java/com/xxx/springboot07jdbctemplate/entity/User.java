package com.xxx.springboot07jdbctemplate.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class User {
    private Long id;
    private String name;
    private Integer age;
    private String address;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss.sss")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss.ss")
    private Date updateTime;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public User() {
    }

    public User(Long id, String name, Integer age, String address, Date createTime, Date updateTime) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
}
