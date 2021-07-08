package com.example.mod.distribute.annotation;

import com.example.mod.api.annotation.ModService;
import org.apache.dubbo.config.ServiceConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;
import org.apache.dubbo.config.bootstrap.builders.ServiceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotationUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangyongxu
 */
public class DistributeModServiceBeanProcessor implements ApplicationListener<ContextRefreshedEvent>, Ordered {
    private static final Logger logger = LoggerFactory.getLogger(DistributeModServiceBeanProcessor.class);

    /**
     * beanName, className
     */
    private Map<String, Class<?>> exposedService = new HashMap<>();


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ApplicationContext applicationContext = event.getApplicationContext();
        logger.info("ContextRefreshedEvent, cxt: {}", applicationContext);
        // TODO: 2021-07-07 02:00:43 父子容器问题 by wangyongxu
        Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(ModService.class);
        List<ServiceConfig> serviceConfigs = new ArrayList<>();
        for (Map.Entry<String, Object> entry : beansWithAnnotation.entrySet()) {
            Class<?> clazz = entry.getValue().getClass();
            ModService modService = AnnotationUtils.findAnnotation(clazz, ModService.class);
            // TODO: 2021-07-07 02:03:21 远程暴露服务 by wangyongxu
            exposedService.put(entry.getKey(), clazz);
            ServiceConfig<Object> serviceConfig = ServiceBuilder.newBuilder()
                    .interfaceClass(clazz.getInterfaces()[0])
                    .ref(entry.getValue())
                    .build();
            serviceConfigs.add(serviceConfig);
        }
        exposedService.forEach((k, v) -> {
            logger.info("expose service: {}, {}", k, v);
        });

        DubboBootstrap dubboBootstrap = DubboBootstrap.getInstance();
        dubboBootstrap.services(serviceConfigs);
//        dubboBootstrap.start();

    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE - 100;
    }
}
