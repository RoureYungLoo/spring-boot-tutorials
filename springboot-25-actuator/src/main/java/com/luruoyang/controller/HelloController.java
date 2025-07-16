package com.luruoyang.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/hello")
public class HelloController {
  @GetMapping
  public String hello() {
    return "hello" + LocalDateTime.now();
  }
}
