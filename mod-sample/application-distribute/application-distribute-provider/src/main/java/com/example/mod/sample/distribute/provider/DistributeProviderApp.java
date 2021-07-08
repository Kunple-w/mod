package com.example.mod.sample.distribute.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wangyongxu
 */
@SpringBootApplication(scanBasePackages = {"com.example.mod.sample.provider"})
public class DistributeProviderApp {
    private static final Logger logger = LoggerFactory.getLogger(DistributeProviderApp.class);

    public static void main(String[] args) {
        SpringApplication.run(DistributeProviderApp.class, args);
    }
}
