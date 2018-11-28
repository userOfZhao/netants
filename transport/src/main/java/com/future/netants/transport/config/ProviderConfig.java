package com.future.netants.transport.config;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zhaofeng on 2018/11/27.
 */
@Getter
public class ProviderConfig extends RPCConfig {

    private static final Logger logger = LoggerFactory.getLogger(ProviderConfig.class);

    /**
     * rpc协议
     */
    private String protocol;

    /**
     * 服务端口号
     */
    private int port;

    /**
     * 设置nio的bossGroup的数量
     */
    private int bossGroupCount;

    /**
     * 设置nio的workGroup的数量
     */
    private int workGroupCount;

    /**
     * 设置端口号
     */
    public ProviderConfig setPort(int port) {
        this.port = port;
        return this;
    }

    public ProviderConfig setProtocol(String protocol) {
        this.protocol = protocol;
        return this;
    }


}
