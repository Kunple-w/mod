package com.example.mod.demo.monolith;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wangyongxu
 */
@SpringBootApplication
public class MonolithApp {
    private static final Logger logger = LoggerFactory.getLogger(MonolithApp.class);

    public static void main(String[] args) {
        SpringApplication.run(MonolithApp.class, args);
    }
}
