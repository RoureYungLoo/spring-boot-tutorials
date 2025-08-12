package com.luruoyang.entity;

import com.alibaba.fastjson2.annotation.JSONField;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * 管理员实体类
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Tag(name = "Emp", description = "员工实体")
public class Emp implements Serializable {

  @Serial
  private static final long serialVersionUID = 4317337818874663187L;

  @Schema(description = "员工ID")
  private Long id;

  @Schema(description = "部门ID")
  private Long deptId;

  @Schema(description = "用户名")
  private String username;

  @JSONField(serialize = false)
  @Schema(description = "密码")
  private String password;

  @Schema(description = "姓名")
  private String name;

  @Schema(description = "状态: 0-正常, 1-禁用")
  private Integer status;
}

