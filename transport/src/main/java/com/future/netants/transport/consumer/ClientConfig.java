package com.future.netants.transport.consumer;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zhaofeng on 2018/11/26.
 */
public class ClientConfig {

    private volatile ConcurrentHashMap<String, String> configs = new ConcurrentHashMap();

    public ClientConfig config(String key, String value) {
        configs.putIfAbsent(key, value);
        return this;
    }

    public ClientConfig config(String key, int value) {
        return config(key, String.valueOf(value));
    }
}
