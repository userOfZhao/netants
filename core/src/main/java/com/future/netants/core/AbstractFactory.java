package com.future.netants.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zhaofeng01 on 2018/11/26.
 */
public abstract class AbstractFactory implements Factory {

    private static final Logger logger = LoggerFactory.getLogger(AbstractFactory.class);

    @Override
    public <T> T createObject() {
        return null;
    }

    public <T> T  createObject(String name) {
        return null;
    }

    public abstract Factory createFactory();
}
