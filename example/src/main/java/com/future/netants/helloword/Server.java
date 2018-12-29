package com.future.netants.helloword;

import com.future.netants.transport.config.ProviderConfig;
import com.future.netants.transport.provider.RPCProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zhaofeng01 on 2018/11/26.
 */
public class Server {

    private static final Logger logger = LoggerFactory.getLogger(Server.class);

    public static void main(String[] args) {
        logger.info("start server");
        ProviderConfig config = new ProviderConfig();
        config.setPort(9997).setProtocol("test");
        RPCProvider provider = new RPCProvider();
        provider.setInterfaceId("helloService").setInterfaceName(Hello.class.getName()).setConfig(config).setRef(new SayHello()).export();
    }
}
