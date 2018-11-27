package com.future.netants.transport.provider;

import com.future.netants.transport.config.ProviderConfig;

/**
 * Created by zhaofeng on 2018/11/27.
 */
public interface Provider {

    /**
     * 发布服务
     */
    void export();

    /**
     * 设置服务引用
     */
    Provider setRef(Object object);

    /**
     * 设置服务的接口名称
     */
    Provider setInterfaceId(String interfaceName);

    /**
     * 设置服务端配置
     */
    Provider setConfig(ProviderConfig config);
}
