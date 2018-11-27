package com.future.netants.transport.consumer;

import com.future.netants.transport.consumer.handler.MessageSendHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zhaofeng on 2018/11/26.
 */
public class RPCServiceLoad {

    private static final Logger logger = LoggerFactory.getLogger(RPCServiceLoad.class);

    private static RPCServiceLoad instance;

    private RPCServiceLoad() {}

    public static RPCServiceLoad getInstance() {
        if (instance == null) {
            synchronized (RPCServiceLoad.class) {
                if (instance == null) {
                    instance = new RPCServiceLoad();
                }
            }
        }
        return instance;
    }

    public void loadRPCService() {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(bossGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChildChannelHandler());
            bootstrap.connect("localhost", 9997).sync();
        } catch (Exception e) {
            logger.error("Interrupted occurs", e);
        } finally {
            bossGroup.shutdownGracefully();
        }
    }

    private static class ChildChannelHandler extends ChannelInitializer {
        @Override
        protected void initChannel(Channel channel) throws Exception {
            channel.pipeline().addLast(new MessageSendHandler());
        }
    }
}
