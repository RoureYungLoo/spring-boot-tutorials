package com.xxx.springboot13listeners.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private Long id;
    private String name;
    private String password;
}
