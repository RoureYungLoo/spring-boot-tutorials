package com.xxx.springboot17shiro.entity;

import lombok.Data;

@Data
public class User {
    private int id;
    private String userName;
    private String password;
    private int roleId;
}
