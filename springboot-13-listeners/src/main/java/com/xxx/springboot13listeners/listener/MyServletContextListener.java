package com.xxx.springboot13listeners.listener;

import com.xxx.springboot13listeners.entity.User;
import com.xxx.springboot13listeners.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextListener;

/* 监听Servlet上下文对象 */
@Component
public class MyServletContextListener
        implements ApplicationListener<ContextRefreshedEvent /*加载或刷新应用上下文时*/> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        /* spring application 上下文 */
        ApplicationContext context = event.getApplicationContext();

        UserService userService = context.getBean(UserService.class);
        User userNewer = userService.getUSer();
        userNewer.setName("New User");

        /* application域对象 */
        ServletContext application = context.getBean(ServletContext.class);
        application.setAttribute("user", userNewer);

    }
}
