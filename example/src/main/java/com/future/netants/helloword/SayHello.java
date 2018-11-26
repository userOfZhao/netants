package com.future.netants.helloword;

import com.future.netants.transport.consumer.RPCConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zhaofeng01 on 2018/11/26.
 */
public class SayHello {

    private static final Logger logger = LoggerFactory.getLogger(SayHello.class);

    public static void main(String[] args) {
        RPCConsumer rpcComsumer = new RPCConsumer();
        Hello sayHello = (Hello) rpcComsumer.refer(Hello.class, "testProvider");
        sayHello.sayHello();
    }
}
