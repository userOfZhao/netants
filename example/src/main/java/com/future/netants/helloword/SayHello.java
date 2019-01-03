package com.future.netants.helloword;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zhaofeng01 on 2018/12/26.
 */
public class SayHello implements Hello {

    private static final Logger logger = LoggerFactory.getLogger(SayHello.class);

    @Override
    public String sayHello(String some) {
        return "Hello! This is netants. " + some;
    }
}
