package com.future.netants.helloword;

import com.future.netants.core.rpc.conf.ConfConstant;
import com.future.netants.transport.config.RPCConfig;
import com.future.netants.transport.consumer.RPCConsumer;

/**
 * Created by zhaofeng01 on 2018/11/26.
 */
public class Client {

    public static void main(String[] args) {

        RPCConfig config = new RPCConfig();
        config.set(ConfConstant.SERVER_PORT, 9997).set(ConfConstant.SERVER_HOST, "localhost");
        RPCConsumer rpcComsumer = new RPCConsumer();
        rpcComsumer.setConfig(config);
        Hello sayHello = rpcComsumer.refer(Hello.class, null);
        for (int i =0; i< 10; i++) {
            System.out.println(sayHello.sayHello("hello"));
        }


    }
}
