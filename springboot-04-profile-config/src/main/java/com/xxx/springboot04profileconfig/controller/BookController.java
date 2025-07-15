package com.xxx.springboot04profileconfig.controller;

import com.xxx.springboot04profileconfig.config.BookConfig;
import com.xxx.springboot04profileconfig.config.NameConfig;
import com.xxx.springboot04profileconfig.config.StudentProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookConfig bookConfig;

    @Autowired
    private NameConfig nameConfig;

    @Autowired
    private StudentProperties studentProperties;

    @GetMapping
    public BookConfig getBookInfo() {
        return bookConfig;
    }

    @GetMapping("/name")
    public NameConfig getNameInfo() {
        return nameConfig;
    }

    @GetMapping("/stu")
    public StudentProperties getStuInfo() {
        LOGGER.info("stu password: {}", studentProperties.getPassword());
        return studentProperties;
    }
}
