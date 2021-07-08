package com.example.mod.autoconfiguration;

import com.example.mod.api.annotation.ModInject;
import com.example.mod.api.annotation.ModService;
import com.example.mod.distribute.annotation.DistributeModInjectBeanProcessor;
import com.example.mod.distribute.annotation.DistributeModServiceBeanProcessor;
import com.example.mod.monolith.annotation.MonolithModInjectProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangyongxu
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(value = {ModService.class, ModInject.class})
public class ModAutoConfiguration {


    @ConditionalOnClass(DistributeModServiceBeanProcessor.class)
    @Configuration(proxyBeanMethods = false)
    static class Distribute {
        @Bean
        public DistributeModServiceBeanProcessor distributeModServiceBeanProcessor() {
            return new DistributeModServiceBeanProcessor();
        }

        @Bean
        public DistributeModInjectBeanProcessor distributeModInjectProcessor() {
            return new DistributeModInjectBeanProcessor();
        }

    }

    @ConditionalOnClass(MonolithModInjectProcessor.class)
    @Configuration(proxyBeanMethods = false)
    static class Monolith {
        @Bean
        public MonolithModInjectProcessor monolithModInjectProcessor() {
            return new MonolithModInjectProcessor();
        }
    }

}