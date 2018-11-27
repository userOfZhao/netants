package com.future.netants.core.balance;

import com.future.netants.core.factory.AbstractFactory;
import com.future.netants.core.factory.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zhaofeng01 on 2018/11/27.
 */
public class LoadBalanceFactory extends AbstractFactory {

    private static final Logger logger = LoggerFactory.getLogger(LoadBalanceFactory.class);

    private static volatile ConcurrentHashMap<String, LoadBalance> loadBalances = new ConcurrentHashMap<>();

    private static LoadBalanceFactory instance;

    static {
        loadBalances.putIfAbsent("random", new RandomLoadBalance());
    }

    private LoadBalanceFactory() {}

    public static LoadBalanceFactory getInstance() {
        if (instance == null) {
            synchronized (LoadBalanceFactory.class) {
                if (instance == null) {
                    instance = new LoadBalanceFactory();
                }
            }
        }
        return instance;
    }

    @Override
    public Factory createFactory() {
        return null;
    }

    @Override
    public LoadBalance createObject() {
        return loadBalances.get("random");

    }

    @Override
    public LoadBalance createObject(String name) {
        return loadBalances.get(name);
    }
}
