package com.luruoyang.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author luruoyang
 */
@RestController
@RequestMapping("/hello")
public class HelloController {
  @GetMapping
  public String hello() {
    return "hello" + LocalDateTime.now();
  }

  @RestController
  @RequestMapping("/hello")
  public static class HelloController {

    @GetMapping
    public String hello() {
      StackWalker instance = StackWalker.getInstance();
      List<StackWalker.StackFrame> stackFrameList = instance.walk(stackFrameStream -> stackFrameStream.collect(Collectors.toList()));

      return "hello from stack frame: " + stackFrameList.get(0);
    }
  }
}
