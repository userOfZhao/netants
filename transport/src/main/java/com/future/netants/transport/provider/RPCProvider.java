package com.future.netants.transport.provider;

import com.future.netants.transport.config.ProviderConfig;
import com.future.netants.transport.config.RPCConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zhaofeng on 2018/11/27.
 */
public class RPCProvider<T> implements Provider {

    private static final Logger logger = LoggerFactory.getLogger(RPCProvider.class);

    /**
     * 服务端配置
     */
    private volatile ProviderConfig config;

    /**
     * 服务接口名称
     */
    private String interfaceId;

    /**
     * 服务提供者实例对象
     */
    private T instance;

    @Override
    public void export() {

    }

    @Override
    public Provider setRef(Object object) {
        this.instance = (T) object;
        return this;
    }


    @Override
    public Provider setInterfaceId(String interfaceName) {
        this.interfaceId = interfaceName;
        return this;
    }

    @Override
    public Provider setConfig(ProviderConfig config) {
        this.config = config;
        return this;
    }
}
