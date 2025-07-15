package com.xxx.springboot04profileconfig.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "urls")
public class MicroServiceUrl {
    private String orderUrl;
    private String userUrl;
    private String shoppingUrl;

    public String getOrderUrl() {
        return orderUrl;
    }

    public void setOrderUrl(String orderUrl) {
        this.orderUrl = orderUrl;
    }

    public String getUserUrl() {
        return userUrl;
    }

    public void setUserUrl(String userUrl) {
        this.userUrl = userUrl;
    }

    public String getShoppingUrl() {
        return shoppingUrl;
    }

    public void setShoppingUrl(String shoppingUrl) {
        this.shoppingUrl = shoppingUrl;
    }

    @Override
    public String toString() {
        return "MicroServiceUrl{" +
                "orderUrl='" + orderUrl + '\'' +
                ", userUrl='" + userUrl + '\'' +
                ", shoppingUrl='" + shoppingUrl + '\'' +
                '}';
    }
}

