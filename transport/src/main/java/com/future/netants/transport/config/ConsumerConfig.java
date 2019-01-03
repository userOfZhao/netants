package com.future.netants.transport.config;

import lombok.Data;

/**
 * Created by zhaofeng01 on 2019/1/3.
 */
@Data
public class ConsumerConfig extends RPCConfig {

    /**
     * 设置负载均衡
     */
    private String loadBalance;

    private ConsumerConfig setLoadBalance(String loadBalance) {
        this.loadBalance = loadBalance;
        return this;
    }
}
