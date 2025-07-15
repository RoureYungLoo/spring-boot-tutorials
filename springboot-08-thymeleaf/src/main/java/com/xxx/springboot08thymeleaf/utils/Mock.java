package com.xxx.springboot08thymeleaf.utils;

import com.xxx.springboot08thymeleaf.entiry.User;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Mock {
    public static User getUser() {
        User user = new User();
        user.setAge(new Random().nextInt(100));
        user.setId((long) new Random().nextInt(1000000));
        user.setName(RandomStringUtils.randomAlphabetic(7));
        user.setEmail(RandomStringUtils.randomAlphabetic(7) + "@gmail.com");
        return user;
    }

    public static List<User> getUsers() {

        List<User> users = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            users.add(getUser());
        }
        return users;
    }
}
