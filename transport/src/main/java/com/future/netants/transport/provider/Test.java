package com.future.netants.transport.provider;

import com.future.netants.common.PropConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zhaofeng01 on 2018/11/26.
 */
public class Test {

    private static final Logger logger = LoggerFactory.getLogger(Test.class);

    public static void main(String[] args) {
        try {
            logger.info("start service now");

            logger.info("load properties now");
            PropConfig.loadProperties(ClassLoader.getSystemResource("sys.properties").getPath());

            System.out.println("properties is " + System.getProperties().toString());
            logger.info("start eventloop now");
            int port = PropConfig.getInt("port", 9999);
            ServerStart.start(port);

            logger.info("config shutdown hook");
            addShutdownHook();

            logger.info("server has been stated");
        } catch (Exception e) {
            logger.error("start service failed", e);
            System.exit(1);
        }
    }

    public static void addShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }));
    }
}
