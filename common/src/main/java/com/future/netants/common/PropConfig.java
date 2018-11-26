package com.future.netants.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zhaofeng01 on 2018/11/26.
 */
public class PropConfig extends AbstractPropConfig {

    private static final Logger logger = LoggerFactory.getLogger(PropConfig.class);

    /**
     * 获取Int类型配置
     * @param key
     * @param defaultValue
     * @return
     */
    public static int getInt(String key, int defaultValue) {
        String value = properties.getProperty(key);
        return value == null ? defaultValue : Integer.parseInt(value);
    }

    /**
     * 获取int类型配置, 如果没有找到配置, 返回0
     * @param key
     * @return
     */
    public static int getInt(String key) {
        return getInt(key, 0);
    }

    /**
     * 获取String类型配置
     * @param key
     * @param defaultValue
     * @return
     */
    public static String getString(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    /**
     * 获取String类型配置, 如果没有找到配置, 返回null
     * @param key
     * @return
     */
    public static String getString(String key) {
        return getString(key, null);
    }
}
