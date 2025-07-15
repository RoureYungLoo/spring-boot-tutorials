package com.xxx.springboot04profileconfig.controller;

import com.xxx.springboot04profileconfig.config.MicroServiceUrl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
public class ConfigController {

    private final Logger logger = LoggerFactory.getLogger(ConfigController.class);


    @Value("${url.orderUrl}")
    private String orderUrl;

    @Autowired
    private MicroServiceUrl microServiceUrl;

    @GetMapping("/order")
    public String getOrderUrl() {

        logger.info("getOrderUrl: {}", orderUrl);

        return "orderUrl: " + orderUrl;
    }

    @GetMapping("/micro-urls")
    public MicroServiceUrl getMicroServiceUrl() {

        logger.info("getMicroServiceUrl: {}", microServiceUrl);

        return microServiceUrl;
    }
}
