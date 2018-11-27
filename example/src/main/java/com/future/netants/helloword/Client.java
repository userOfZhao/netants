package com.future.netants.helloword;

import com.future.netants.transport.consumer.RPCConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zhaofeng01 on 2018/11/26.
 */
public class Client {

    public static void main(String[] args) {
        RPCConsumer rpcComsumer = new RPCConsumer();
        Hello sayHello = rpcComsumer.refer(Hello.class, null);
        sayHello.sayHello("hello");


    }
}
