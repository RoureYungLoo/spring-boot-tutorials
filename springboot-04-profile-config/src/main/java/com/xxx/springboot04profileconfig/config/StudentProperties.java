package com.xxx.springboot04profileconfig.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "student")
public class StudentProperties {
    /**
     * 学生姓名
     */
    @Value("${student.name}")
    private String name;
    /**
     * 学生年龄
     */
    @Value("${student.age}")
    private int age;
    /**
     * 学生性别
     */
    @Value("${student.gender}")
    private String gender;

    /**
     * 学生密码
     */
    @Value("${student.password}")
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
