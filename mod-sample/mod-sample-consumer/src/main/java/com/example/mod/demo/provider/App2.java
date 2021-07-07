package com.example.mod.demo.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wangyongxu
 */
@SpringBootApplication(scanBasePackages = "com.example.mod.demo.distribute")
public class App2 {
    private static final Logger logger = LoggerFactory.getLogger(App2.class);

    public static void main(String[] args) {
        SpringApplication.run(App2.class, args);
    }
}
