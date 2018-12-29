package com.future.netants.transport.provider;

import com.future.netants.core.annotation.Singleton;
import com.future.netants.transport.config.ProviderConfig;
import com.future.netants.transport.provider.handler.ServerChannelInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zhaofeng01 on 2018/11/28.
 */
@Singleton
class RPCServerInit {

    private static final Logger logger = LoggerFactory.getLogger(RPCServerInit.class);

    private static RPCServerInit instance;

    /**
     * RPC服务端配置
     */
    private ProviderConfig config;

    /**
     * 负责channel接入的nio线程
     */
    private EventLoopGroup bossGroup;

    /**
     * 负责处理业务消息nio线程
     */
    private EventLoopGroup workGroup;

    private RPCServerInit() {}

    static RPCServerInit getInstance() {
        if (instance == null) {
            synchronized (RPCServerInit.class) {
                if (instance == null) {
                    instance = new RPCServerInit();
                }
            }
        }
        return instance;
    }

    void start() {
        startBusinessThreadPool();
        startEventLoop();
    }

    /**
     * start business thread pool
     */
    private void startBusinessThreadPool() {
        BusinessServiceExecutor.getInstance().init();
    }

    /**
     * start nio thread pool
     */
    private void startEventLoop() {
        bossGroup = new NioEventLoopGroup();
        workGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childHandler(new ServerChannelInitializer());
            ChannelFuture future = bootstrap.bind(config.getPort());
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            logger.error("Start rpc server failed", e);
            throw new RuntimeException("RPC start fail.", e.getCause());
        }
    }

    /**
     * 设置服务端配置
     */
    public RPCServerInit setConfig(ProviderConfig config) {
        this.config = config;
        return this;
    }
}