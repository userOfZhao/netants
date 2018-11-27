package com.future.netants.transport.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Proxy;

/**
 * Created by zhaofeng01 on 2018/11/26.
 */
public class RPCConsumer implements Consumer {

    private static final Logger logger = LoggerFactory.getLogger(RPCConsumer.class);

    private volatile ClientConfig config;

    @Override
    public <T>T refer(Class<T> clazz, String provider) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class<?>[]{clazz}, new ConsumerInvocationHandler());
    }

    @Override
    public void setConfig(ClientConfig config) {
        this.config = config;
    }

}
