package com.future.netants.transport.consumer;

/**
 * Created by zhaofeng01 on 2018/11/26.
 */
public interface Consumer {
    Object refer(Class clazz, String provider);
}
