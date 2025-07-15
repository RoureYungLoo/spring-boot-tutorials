package com.xxx.springboot05webmvc.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@Schema(description = "用户实体")
public class User implements Serializable {
    public static final Long serialVersionUID = 4147016262318227459L;
//    @Schema(description = "用户ID")
    private Long id;
//    @Schema(description = "用户名")
    private String username;
//    @Schema(description = "密码")
    private String password;
//    @Schema(description = "邮箱")
    private String email;
//    @Schema(description = "手机")
    private String phone;
//    @Schema(description = "昵称")
    private String nickname;
//    @Schema(description = "头像")
    private String avatar;
//    @Schema(description = "性别")
    private String gender;
//    @Schema(description = "生日")
    private LocalDate birthday;
//    @Schema(description = "启用/禁用")
    private Integer enabled;
//    @Schema(description = "锁定状态")
    private Integer locked;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
//    @Schema(description = "上次登录时间")
    private LocalDateTime lastLoginTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
//    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
//    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
//    @Schema(description = "创建者")
    private Long createBy;
//    @Schema(description = "更新者")
    private Long updateBy;
}
