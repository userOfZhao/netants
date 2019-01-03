package com.future.netants.core.balance;

import io.netty.channel.ChannelFuture;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zhaofeng01 on 2018/11/27.
 */
public interface LoadBalance {

//    /**
//     * 设置远程连接映射关系
//     */
//    void setFutureMap(Map<String, ChannelFuture> futureMap);

    /**
     * 获取远程连接
     * @return  远程连接
     */
    ChannelFuture getFuture();

    /**
     * 从futureMap中选择一个远程连接
     * @param futureMap 选取的远程连接的集合
     * @return  一条远程连接
     */
    ChannelFuture getFuture(Map<String, ChannelFuture> futureMap);
}
