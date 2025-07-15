package com.xxx.springboot08thymeleaf.entiry;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
