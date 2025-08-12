package com.luruoyang.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.luruoyang.config.JwtUtils;
import com.luruoyang.dto.LoginDTO;
import com.luruoyang.entity.User;
import com.luruoyang.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author luruoyang
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtUtils jwtUtils;

  @Autowired
  private UserMapper userMapper;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
    Authentication authToken = new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());
    Authentication authentication = authenticationManager.authenticate(authToken);
    org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
    List<String> roles = principal.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
    String token = jwtUtils.genToken(principal.getUsername(), roles);
    return ResponseEntity.ok(Map.of("token", token));
  }

  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody LoginDTO loginDTO) {
    if (userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, loginDTO.getUsername())) != null) {
      return ResponseEntity.status(HttpStatus.CONFLICT).body("exists");
    }
    User user = BeanUtil.toBean(loginDTO, User.class);
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userMapper.insert(user);
    return ResponseEntity.ok("ok");
  }

}
