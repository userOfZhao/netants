package com.future.netants.helloword;

import com.future.netants.core.rpc.conf.ConfConstant;
import com.future.netants.transport.consumer.ClientConfig;
import com.future.netants.transport.consumer.RPCConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zhaofeng01 on 2018/11/26.
 */
public class Client {

    public static void main(String[] args) {

        ClientConfig config = new ClientConfig();
        config.set(ConfConstant.SERVER_PORT, 9997).set(ConfConstant.SERVER_HOST, "localhost");
        RPCConsumer rpcComsumer = new RPCConsumer();
        rpcComsumer.setConfig(config);
        Hello sayHello = rpcComsumer.refer(Hello.class, null);
        sayHello.sayHello("hello");

    }
}
