package com.luruoyang.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view")
public class ViewController {
  @GetMapping("/hello")
  public String returnView() {
    return "index";
  }
}
