package com.levy.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author LevyXie
 * @create 2022-01-14 11:29
 * @description servlet相关的工具类，封装方法 + 整数转换方法
 */
public class WebUtils {
    public static <T> T copyParamToBean(Map value, T bean){
        try {
            BeanUtils.populate(bean,value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return bean;
    }
    public static int parseInt(String num,int defaultValue){
        try{
            if(num != null){
                return Integer.parseInt(num);
            };
        }catch (Exception e){
            e.printStackTrace();
        }
        return defaultValue;
    }
}
