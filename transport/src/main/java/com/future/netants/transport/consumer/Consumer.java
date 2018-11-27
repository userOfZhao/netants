package com.future.netants.transport.consumer;

/**
 * Created by zhaofeng01 on 2018/11/26.
 */
public interface Consumer {
    <T>T refer(Class<T> clazz, String provider);

    void setConfig(ClientConfig config);
}
