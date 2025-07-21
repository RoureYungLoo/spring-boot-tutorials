package com.luruoyang.component;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author luruoyang
 */
@Component
@Order(200)
public class MyCommandLineRunner1 implements CommandLineRunner {
  @Override
  public void run(String... args) throws Exception {
    System.out.println("调用MyCommandLineRunner1， 参数: " + Arrays.toString(args));
  }
}
