package com.xxx.springboot03logging.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/logging")
//@Slf4j //lombok注解
public class LoggingController {

    private final Logger logger = LoggerFactory.getLogger(LoggingController.class);

    @GetMapping("/info")
    public String info() {
        logger.info("info test success");
        return "info test success";
    }

    @GetMapping("/warn")
    public String warn() {
        logger.warn("warn test success");
        return "warn test success";
    }

    @GetMapping("/debug")
    public String debug() {
        logger.debug("debug test success");
        return "debug test success";
    }
}
