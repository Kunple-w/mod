package com.example.mod.distribute.annotation;

import com.example.mod.api.annotation.ModService;
import org.apache.dubbo.config.ServiceConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;
import org.apache.dubbo.config.bootstrap.builders.ServiceBuilder;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.AnnotationUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangyongxu
 */
public class DistributeModServiceBeanProcessor implements BeanPostProcessor, ApplicationContextAware, ApplicationListener<ContextRefreshedEvent> {

    protected ApplicationContext applicationContext;
    /**
     * beanName, className
     */
    private Map<String, Class<?>> exposedService = new HashMap<>();

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        // TODO: 2021-07-07 02:00:43 父子容器问题 by wangyongxu
        Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(ModService.class);
        List<ServiceConfig> serviceConfigs = new ArrayList<>();
        for (Map.Entry<String, Object> entry : beansWithAnnotation.entrySet()) {
            Class<?> clazz = entry.getValue().getClass();
            ModService modService = AnnotationUtils.findAnnotation(clazz, ModService.class);
            // TODO: 2021-07-07 02:03:21 远程暴露服务 by wangyongxu
            exposedService.put(entry.getKey(), clazz);
            ServiceConfig<Object> serviceConfig = ServiceBuilder.newBuilder()
                    .interfaceClass(clazz)
                    .ref(entry.getValue())
                    .build();
            serviceConfigs.add(serviceConfig);
        }

        DubboBootstrap dubboBootstrap = DubboBootstrap.getInstance();
        dubboBootstrap.services(serviceConfigs);
        dubboBootstrap.start();

    }
}
