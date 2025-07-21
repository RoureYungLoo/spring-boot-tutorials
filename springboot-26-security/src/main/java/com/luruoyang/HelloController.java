package com.luruoyang;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.Filter;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/hello")
public class HelloController {

  @GetMapping
  public String hello() {
    StackWalker instance = StackWalker.getInstance();
    List<StackWalker.StackFrame> stackFrameList = instance.walk(stackFrameStream -> stackFrameStream.collect(Collectors.toList()));

    return "hello from stack frame: " + stackFrameList.get(0);
  }
}
