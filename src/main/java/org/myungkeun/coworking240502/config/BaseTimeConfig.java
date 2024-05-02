package org.myungkeun.coworking240502.config;

import jakarta.annotation.PostConstruct;

import java.util.TimeZone;

public class BaseTimeConfig {
    @PostConstruct
    public void setSeoulTimeZone() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
    }
}
