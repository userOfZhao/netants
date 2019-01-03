package com.future.netants.core.balance;

import io.netty.channel.ChannelFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by zhaofeng01 on 2018/11/27.
 */
public class RandomLoadBalance implements LoadBalance {

    private static final Logger logger = LoggerFactory.getLogger(RandomLoadBalance.class);

    @Override
    public ChannelFuture getFuture() {
        return null;
    }

    @Override
    public ChannelFuture getFuture(Map<String, ChannelFuture> futureMap) {
        int size = futureMap.size();
        Set<String> keys = futureMap.keySet();
        return futureMap.get(keys.toArray()[ThreadLocalRandom.current().nextInt(size)]);
    }
}
