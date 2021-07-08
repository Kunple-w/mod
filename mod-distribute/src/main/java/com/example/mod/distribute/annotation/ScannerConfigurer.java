package com.example.mod.distribute.annotation;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;

import java.util.Set;

/**
 * @author wangyongxu
 */
public class ScannerConfigurer implements BeanDefinitionRegistryPostProcessor {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {

    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }

    public static class ModServiceScanner extends ClassPathBeanDefinitionScanner {
        public ModServiceScanner(BeanDefinitionRegistry registry) {
            super(registry, false);
        }

        @Override
        protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
            Set<BeanDefinitionHolder> beanDefinitionHolders = super.doScan(basePackages);
            return super.doScan(basePackages);
        }

        private void processBeanDefinitions(Set<BeanDefinitionHolder> beanDefinitionHolders) {

        }

        protected void registerFilter() {

        }
    }

}
