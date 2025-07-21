package com.luruoyang;

import com.luruoyang.banner.MyBanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @author luruoyang 自定义App
 */
public class MyApp {
  private final Banner myBanner;
  private final Class<?> source;

  public MyApp(Class<?> source, Banner banner) {
    this.myBanner = new MyBanner();
    this.source = source;
  }

  public void run(String... args) {
    SpringApplication application = new SpringApplication(source);
    application.setBanner(myBanner);
    application.run(args);
  }
}
