package com.xxx.springboot13listeners.service;

import com.xxx.springboot13listeners.entity.User;
import com.xxx.springboot13listeners.event.MyEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UserService {

    @Autowired
    private ApplicationContext applicationContext;

    public User getUSer() {
        return User.builder()
                .id((long) new Random().nextInt())
                .name("testName" + new Random().nextInt())
                .password("123456").build();
    }

    public User getUserAndPublishEvent() {
        User user = User.builder()
                .id((long) new Random().nextInt())
                .name("testName" + new Random().nextInt())
                .password("123456").build();

        /* 发布一个事件 */
        MyEvent myEvent = new MyEvent(this, user);
        applicationContext.publishEvent(myEvent);

        return user;
    }
}
