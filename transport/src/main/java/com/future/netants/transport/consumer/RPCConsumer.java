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

    /**
     * 目前只做点对点的链接，后续接入zk之后，会进行多连接
     * @param clazz     引用的接口类
     * @param provider  提供者名称
     * @return          远程服务对象
     */
    @Override
    public <T> T refer(Class<T> clazz, String provider) {
        String interfaceName = clazz.getName();
        if (logger.isDebugEnabled()) {
            logger.debug("refer remote service. Class is {}, provider is {}", interfaceName, provider);
        }
        RPCServiceLoad.getInstance().loadConfig(config).loadRPCService(interfaceName);
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class<?>[]{clazz}, new ConsumerInvocationHandler(interfaceName));
    }

    @Override
    public void setConfig(ClientConfig config) {
        this.config = config;
    }

}
