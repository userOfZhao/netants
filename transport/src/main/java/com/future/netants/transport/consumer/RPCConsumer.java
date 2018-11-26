package com.future.netants.transport.consumer;

import com.future.netants.transport.consumer.handler.WriteMessageHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by zhaofeng01 on 2018/11/26.
 */
public class RPCConsumer implements Consumer {

    private static final Logger logger = LoggerFactory.getLogger(RPCConsumer.class);

    private static WriteMessageHandler writeMessageHandler;

    @Override
    public Object refer(Class clazz, String provider) {
        return Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, new ConsumerInvocationHandler());
    }

    // 构造一个虚假的实现类，实际上是由远程服务来提供的
    private class ConsumerInvocationHandler implements InvocationHandler {

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return writeMessageHandler.call(args);
        }
    }

    private void initClient() {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(bossGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .handler(new ChildChannelHandler());

            ChannelFuture future = bootstrap.connect("localhost", 9999);
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            logger.error("Interrupted occurs", e);
            throw new RuntimeException("Interrupted occurs");
        }
    }

    private static class ChildChannelHandler extends ChannelInitializer {
        @Override
        protected void initChannel(Channel channel) throws Exception {
            writeMessageHandler = new WriteMessageHandler();
            channel.pipeline().addLast(writeMessageHandler);
        }
    }
}
