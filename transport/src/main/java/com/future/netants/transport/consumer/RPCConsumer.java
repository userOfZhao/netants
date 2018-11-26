package com.future.netants.transport.consumer;

import com.future.netants.transport.consumer.handler.WriteMessageHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Proxy;

/**
 * Created by zhaofeng01 on 2018/11/26.
 */
public class RPCConsumer implements Consumer {

    private static final Logger logger = LoggerFactory.getLogger(RPCConsumer.class);

    private static WriteMessageHandler writeMessageHandler;

    @Override
    public <T>T refer(Class<T> clazz, String provider) {
        initClient();
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class<?>[]{clazz}, new ConsumerInvocationHandler());
    }

    private void initClient() {
        writeMessageHandler = new WriteMessageHandler();
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(bossGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChildChannelHandler());

            ChannelFuture future = bootstrap.connect("localhost", 9997).sync();
        } catch (Exception e) {
            logger.error("Interrupted occurs", e);
        }
    }

    private static class ChildChannelHandler extends ChannelInitializer {
        @Override
        protected void initChannel(Channel channel) throws Exception {
            channel.pipeline().addLast(writeMessageHandler);
        }
    }
}
