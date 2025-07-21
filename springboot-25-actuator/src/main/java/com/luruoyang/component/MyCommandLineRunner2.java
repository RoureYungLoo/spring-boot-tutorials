package com.luruoyang.component;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author luruoyang
 */
@Component
@Order(199)
public class MyCommandLineRunner2 implements CommandLineRunner {
  @Override
  public void run(String... args) throws Exception {
    System.out.println("调用MyCommandLineRunner2， 参数: " + Arrays.toString(args));
  }
}
