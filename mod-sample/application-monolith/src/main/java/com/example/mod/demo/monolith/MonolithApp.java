package com.example.mod.demo.monolith;

import com.example.mod.api.annotation.ModService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

/**
 * @author wangyongxu
 */
@SpringBootApplication(scanBasePackages = {"com.example.mod.sample.consumer","com.example.mod.sample.provider"})
//@ComponentScan(includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = ModService.class),useDefaultFilters = true)
public class MonolithApp {
    private static final Logger logger = LoggerFactory.getLogger(MonolithApp.class);

    public static void main(String[] args) {
        SpringApplication.run(MonolithApp.class, args);
    }
}
