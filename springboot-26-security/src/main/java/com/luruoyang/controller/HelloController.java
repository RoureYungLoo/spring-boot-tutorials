package com.luruoyang.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Function;
import java.util.stream.Stream;

@RestController

public class HelloController {

  @GetMapping("/hello")
  public String hello() {
    StackWalker walker = StackWalker.getInstance();
    String walk = walker.walk(new Function<Stream<StackWalker.StackFrame>, String>() {
      @Override
      public String apply(Stream<StackWalker.StackFrame> stackFrameStream) {
        return stackFrameStream.findFirst().toString();
      }
    });
    return walk;
  }
}
