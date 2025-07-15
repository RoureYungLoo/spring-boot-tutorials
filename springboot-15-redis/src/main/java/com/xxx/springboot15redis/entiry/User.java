package com.xxx.springboot15redis.entiry;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

//@Data
//@Builder
public class User implements Serializable {
    private static final Long serialVersionUID = 1L;
    private int id;
    private String name;
    private int age;
    private String address;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", birthday=" + birthday +
                '}';
    }

    public User(int id, String name, int age, String address, Date birthday) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
        this.birthday = birthday;
    }

    public int getId() {
        return id;
    }

    public User setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public int getAge() {
        return age;
    }

    public User setAge(int age) {
        this.age = age;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public User setAddress(String address) {
        this.address = address;
        return this;
    }

    public Date getBirthday() {
        return birthday;
    }

    public User setBirthday(Date birthday) {
        this.birthday = birthday;
        return this;
    }
}
