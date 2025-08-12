package com.luruoyang.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author luruoyang
 */
@Data
public class User {
  private Long id;
  private String username;
  private String password;
  private Integer enabled;
  private LocalDateTime createdAt;
}
