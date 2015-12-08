package com.keji50.k5.common.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationContextHolder implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextHolder.applicationContext = applicationContext;
    }

    public static ApplicationContext getContext() {
        return applicationContext;
    }

    @SuppressWarnings("unchecked")
    public static final <T> T getBean(String beanName) {
        return (T) applicationContext.getBean(beanName);
    }

    @SuppressWarnings("unchecked")
    public static final <T> T getBean(String beanName, Class<?> requiredType) {
        return (T) applicationContext.getBean(beanName, requiredType);
    }
}
