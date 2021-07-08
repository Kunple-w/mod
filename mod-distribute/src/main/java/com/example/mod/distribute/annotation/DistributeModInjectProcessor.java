//package com.example.mod.distribute.annotation;
//
//import com.example.mod.api.annotation.AbstractModInjectProcessor;
//import org.apache.dubbo.config.bootstrap.DubboBootstrap;
//import org.apache.dubbo.config.bootstrap.builders.ConsumerBuilder;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.lang.reflect.InvocationHandler;
//import java.lang.reflect.Method;
//import java.lang.reflect.Proxy;
//
///**
// * @author wangyongxu
// */
//public class DistributeModInjectProcessor extends AbstractModInjectProcessor {
//
//
//    private static final Logger logger = LoggerFactory.getLogger(DistributeModInjectProcessor.class);
//
//
//    @Override
//    public Object getProxy(Class<?> field, String beanName) {
//
//        // TODO: 2021-07-07 08:56:42 依赖注册中心及rpc by wangyongxu
//        DubboBootstrap.getInstance().getCache().getProxies()
//        logger.info("expect bean: {}", field);
//        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{field}, new CachedLazyRemoteProxy(field));
//    }
//
//
//    static class CachedLazyRemoteProxy implements InvocationHandler {
//
//        private Class<?> clazz;
//
//        private Object remoteObj;
//
//        public CachedLazyRemoteProxy(Class<?> clazz) {
//            this.clazz = clazz;
//        }
//
//        @Override
//        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//            getRemoteObj();
//            return method.invoke(remoteObj, args);
//        }
//
//        private synchronized Object getRemoteObj() {
//            if (remoteObj == null) {
//                logger.info("获得代理对象");
//                remoteObj = DubboBootstrap.getInstance().getCache().get(clazz);
//            }
//            return remoteObj;
//        }
//    }
//
//}
