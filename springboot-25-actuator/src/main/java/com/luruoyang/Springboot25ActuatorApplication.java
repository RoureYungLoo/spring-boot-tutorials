package com.luruoyang;

import com.luruoyang.banner.MyBanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

/**
 * @author luruoyang
 */
@SpringBootApplication
public class Springboot25ActuatorApplication {
  public static void main(String[] args) {
    String[] tmp = {
        "param1",
        "param2",
        "param3"
    };
    // SpringApplication.run(Springboot25ActuatorApplication.class, tmp);
    MyApp myApp = new MyApp(Springboot25ActuatorApplication.class, new MyBanner());
    myApp.run(tmp);
  }

}
