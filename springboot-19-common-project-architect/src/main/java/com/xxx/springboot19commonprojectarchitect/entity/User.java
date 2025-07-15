package com.xxx.springboot19commonprojectarchitect.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
@ApiModel(value = "用户实体", description = "用户实体描述")
public class User implements Serializable {
    private static final long serialVersionUID = 3472927823632154419L;
    @ApiModelProperty("用户ID")
    private int id;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("用户密码")
    private String password;
}
