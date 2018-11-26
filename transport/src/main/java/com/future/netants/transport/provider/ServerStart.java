package com.future.netants.transport.provider;

import com.future.netants.transport.provider.handler.FirstHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zhaofeng01 on 2018/11/26.
 */
public class ServerStart {

    private static final Logger logger = LoggerFactory.getLogger(ServerStart.class);

    private static EventLoopGroup bossGroup;
    private static EventLoopGroup workGroup;

    private ServerStart() {}

    static void start(int port) {
        bossGroup = new NioEventLoopGroup();
        workGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childHandler(new ChildChannelHandler());

            ChannelFuture future = bootstrap.bind(port);
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            logger.error("Interrupted occurs", e);
            throw new RuntimeException("Interrupted occurs");
        }
    }

    private static class ChildChannelHandler extends ChannelInitializer {
        @Override
        protected void initChannel(Channel channel) throws Exception {
            channel.pipeline().addLast(new FirstHandler());
        }
    }
}
