package com.example.mod.distribute.annotation;

/**
 * @author wangyongxu
 */
public class MonolithModInjectProcessor extends AbstractModInjectProcessor {

    @Override
    public Object getProxy(Class<?> field, String beanName) {
        return applicationContext.getBean(beanName, field);
    }
}
