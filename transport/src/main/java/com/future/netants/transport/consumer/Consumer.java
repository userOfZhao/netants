package com.future.netants.transport.consumer;

import com.future.netants.transport.config.RPCConfig;

/**
 * Created by zhaofeng01 on 2018/11/26.
 */
public interface Consumer {

    /**
     * refer remote service
     * @param clazz     引用的接口类
     * @param provider  提供者名称
     * @param <T>       接口类型
     * @return          远程接口对象
     */
    <T>T refer(Class<T> clazz, String provider);

    /**
     * rpc客户端配置
     * @param config    客户端配置对象
     */
    void setConfig(RPCConfig config);
}
