package com.example.mod.api.annotation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.BridgeMethodResolver;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;

/**
 * @author wangyongxu
 */
public abstract class AbstractModInjectProcessor implements BeanPostProcessor, ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(AbstractModInjectProcessor.class);

    protected ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        ReflectionUtils.doWithLocalFields(bean.getClass(), field -> {
            ModInject fieldAnnotation = field.getAnnotation(ModInject.class);
            if (fieldAnnotation != null) {
                Object value = getProxy(field.getType(), beanName);
                ReflectionUtils.makeAccessible(field);
                ReflectionUtils.setField(field, bean, value);
            }
        });
        ReflectionUtils.doWithLocalMethods(bean.getClass(), method -> {
            Method bridgedMethod = BridgeMethodResolver.findBridgedMethod(method);
            if (!BridgeMethodResolver.isVisibilityBridgeMethodPair(method, bridgedMethod)) {
                return;
            }
            ModInject methodAnnotation = findAnnotation(method);
            if (methodAnnotation != null && method.equals(ClassUtils.getMostSpecificMethod(method, bean.getClass()))) {
                if (Modifier.isStatic(method.getModifiers())) {
                    logger.info("ModInject annotation is not supported on static methods: {}", method);
                }
                if (method.getParameterCount() == 0) {
                    logger.info("ModInject annotation should only be used on methods : {} with parameters", method);
                }
                Parameter[] parameters = method.getParameters();
                Object[] arguments = new Object[parameters.length];
                for (int i = 0; i < parameters.length; i++) {
                    arguments[i] = getProxy(parameters[i].getType(), beanName);
                }
                ReflectionUtils.makeAccessible(method);
                try {
                    method.invoke(bean, arguments);
                } catch (InvocationTargetException e) {
                    logger.error("invoke ModInject method: {} failed", method, e);
                }
            }

        });
        return bean;
    }

    public abstract Object getProxy(Class<?> field, String beanName);

    private ModInject findAnnotation(Method method) {
        ModInject annotation = method.getAnnotation(ModInject.class);
        if (annotation == null) {
            for (Parameter parameter : method.getParameters()) {
                if ((annotation = parameter.getAnnotation(ModInject.class)) != null) {
                    return annotation;
                }
            }
        }
        return null;
    }


}
