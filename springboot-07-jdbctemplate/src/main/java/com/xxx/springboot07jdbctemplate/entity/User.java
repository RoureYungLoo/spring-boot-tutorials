package com.xxx.springboot07jdbctemplate.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class User implements Serializable {
    private Long id;
    private String name;
    private Integer age;
    private String address;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss.ss")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss.ss")
    private LocalDateTime updateTime;

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
}
