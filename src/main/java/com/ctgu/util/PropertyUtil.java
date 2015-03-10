// Source File Name:   PropertyUtil.java

package com.ctgu.util;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyResourceConfigurer;

public class PropertyUtil extends PropertyResourceConfigurer
{

    public PropertyUtil()
    {
    }

    public static String getProperty(String key)
    {
        return properties.getProperty(key);
    }

    public static String getProperty(String key, String defaultValue)
    {
        return properties.getProperty(key, defaultValue);
    }

    public static Iterator iterator()
    {
        return properties.entrySet().iterator();
    }

    protected void processProperties(ConfigurableListableBeanFactory paramConfigurableListableBeanFactory, Properties paramProperties)
        throws BeansException
    {
        if(properties == null)
        {
            properties = paramProperties;
        } else
        {
            String key;
            for(Enumeration enums = paramProperties.propertyNames(); enums.hasMoreElements(); properties.setProperty(key, paramProperties.getProperty(key)))
                key = (String)enums.nextElement();

        }
    }

    private static Properties properties;
}
