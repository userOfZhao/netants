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
     * 设置发布服务名称
     */
    Provider setInterfaceId(String interfaceName);

    /**
     * 设置服务接口
     */
    Provider setInterfaceName(String interfaceName);

    /**
     * 设置服务端配置
     */
    Provider setConfig(ProviderConfig config);
}
