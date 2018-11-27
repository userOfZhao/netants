package com.future.netants.transport.consumer;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zhaofeng on 2018/11/26.
 */
public class ClientConfig {

    /**
     * 存放所有用户客户端自己配置的信息
     */
    private volatile ConcurrentHashMap<String, String> configs = new ConcurrentHashMap();

//    public ConcurrentHashMap getConfigs() {
//        return configs;
//    }

    /**
     * 获取字符串配置
     * @param key           配置的key
     * @param defaultValue  默认值，没有配置这个key,返回该值
     * @return              key对应的value
     */
    public String getString(String key, String defaultValue) {
        String value = configs.get(key);
        return value == null ? defaultValue : value;
    }

    /**
     * 获取整形配置
     * @param key           配置的key
     * @param defaultValue  默认值，没有配置这个key,返回该值
     * @return              key对应的value
     */
    public int getInt(String key, int defaultValue) {
        String value = configs.get(key);
        return value == null ? defaultValue : Integer.valueOf(value);
    }

    /**
     * 配置
     * @param key key
     * @param value value
     * @return ClientConfig 对象
     */
    public ClientConfig set(String key, String value) {
        configs.putIfAbsent(key, value);
        return this;
    }

    public ClientConfig set(String key, int value) {
        return set(key, String.valueOf(value));
    }

    public boolean initConfig() {
        if (configs.isEmpty()) {
            return false;
        }
        return true;
    }
}
