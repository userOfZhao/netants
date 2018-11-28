package com.future.netants.core.load;

import com.future.netants.core.factory.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zhaofeng01 on 2018/11/28.
 */
public class ElementFactory implements Factory {

    private static final Logger logger = LoggerFactory.getLogger(ElementFactory.class);

    @Override
    public <T> T createObject() {
        return null;
    }

    @Override
    public <T> T createObject(String name) {
        return null;
    }

    @Override
    public <T> T createObject(Class<T> clazz) {
        return null;
    }

    @Override
    public <T> T newInstance(Class<T> clazz) {
        return null;
    }
}
