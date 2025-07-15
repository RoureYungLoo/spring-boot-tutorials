package com.xxx.springboot04profileconfig.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class NameConfig {

    @Value("${name}")
    private String name;


    @Value("${db}")
    private String db;
    @Value("${mq}")
    private String mq;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public String getMq() {
        return mq;
    }

    public void setMq(String mq) {
        this.mq = mq;
    }
}
