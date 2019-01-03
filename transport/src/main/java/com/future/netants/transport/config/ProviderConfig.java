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
     * RPC业务线程数量
     */
    private int threadNum = 10;

    /**
     * RPC请求等待队列长度
     */
    private int businessQueueSize = 100;

    /**
     * 预热所有线程池线程
     */
    private boolean prestartAllThread = false;

    public ProviderConfig setPrestartAllThread(boolean prestartAllThread) {
        this.prestartAllThread = prestartAllThread;
        return this;
    }

    public ProviderConfig setBusiNessQueueSize(int size) {
        this.businessQueueSize = size;
        return this;
    }

    public ProviderConfig setThreadNum(int threadNum) {
        this.threadNum = threadNum;
        return this;
    }

    public ProviderConfig setPort(int port) {
        this.port = port;
        return this;
    }

    public ProviderConfig setProtocol(String protocol) {
        this.protocol = protocol;
        return this;
    }


}
