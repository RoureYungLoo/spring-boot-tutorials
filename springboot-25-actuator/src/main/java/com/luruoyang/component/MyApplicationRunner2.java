package com.luruoyang.component;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author luruoyang
 */
@Component
@Order(99)
public class MyApplicationRunner2 implements ApplicationRunner {
  @Override
  public void run(ApplicationArguments args) throws Exception {
    System.out.println("调用MyApplicationRunner2， 参数: " + args.toString());

  }
}
