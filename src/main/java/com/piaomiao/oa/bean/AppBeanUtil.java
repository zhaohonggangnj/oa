package com.piaomiao.oa.bean;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;

@Component
public class AppBeanUtil implements ApplicationContextAware {
    private static ApplicationContext appContext = null;
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException{
       appContext=applicationContext;
    }

    public static Object getBean(String id) {
        return appContext.getBean(id);
    }

    public static <T> T getBean(Class<T> beanClass) {
        return appContext.getBean(beanClass);
    }

    public static <T> Collection<T> getBeanList(Class<T> beanClass){
        Map<String, T> map = appContext.getBeansOfType(beanClass);
        return map.values();
    }
}
