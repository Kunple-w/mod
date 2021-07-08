package com.example.mod.monolith.annotation;

import com.example.mod.api.annotation.AbstractModInjectProcessor;

/**
 * @author wangyongxu
 */
public class MonolithModInjectProcessor extends AbstractModInjectProcessor {

    @Override
    public Object getProxy(Class<?> field, String beanName) {
        return applicationContext.getBean(field);
    }
}
