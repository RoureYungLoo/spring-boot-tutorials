package com.xxx.springboot12transaction.entity;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Long id;
    private String userName;
    private Integer age;
    private Date createTime;
    private Date updateTime;
}
