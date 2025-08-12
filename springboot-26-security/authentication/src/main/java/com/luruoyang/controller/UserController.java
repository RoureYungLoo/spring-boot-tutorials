package com.luruoyang.controller;

import com.luruoyang.entity.User;
import com.luruoyang.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author luruoyang
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

  @Autowired
  private UserMapper userMapper;

  @GetMapping("/{id}")
  @PreAuthorize("hasAnyAuthority('query')")
  public ResponseEntity<User> getUser(@PathVariable Long id) {
    return ResponseEntity.ok(userMapper.selectById(id));
  }
}

