package com.example.mod.sample.distribute.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wangyongxu
 */
@SpringBootApplication(scanBasePackages = {"com.example.mod.sample.consumer"})
public class DistributeConsumerApp {
    private static final Logger logger = LoggerFactory.getLogger(DistributeConsumerApp.class);

    public static void main(String[] args) {
        SpringApplication.run(DistributeConsumerApp.class, args);
    }
}
