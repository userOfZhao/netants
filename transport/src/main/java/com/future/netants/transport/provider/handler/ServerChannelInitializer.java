package com.future.netants.transport.provider.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.EventExecutorGroup;
import io.netty.util.concurrent.RejectedExecutionHandler;
import io.netty.util.concurrent.SingleThreadEventExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;


/**
 * Created by zhaofeng01 on 2018/11/28.
 */
public class ServerChannelInitializer extends ChannelInitializer {

    private static final Logger logger = LoggerFactory.getLogger(ServerChannelInitializer.class);

    private EventExecutorGroup executor;

    private static int defaultPoolSize = Runtime.getRuntime().availableProcessors();

    private static int defaultQueueSize = 1000;

    public ServerChannelInitializer() {
        this(defaultPoolSize, defaultQueueSize);
    }

    public ServerChannelInitializer(int corePoolSize, int queueSize) {
        executor = new DefaultEventExecutorGroup(corePoolSize, Executors.defaultThreadFactory(), queueSize, new RejectStrategy());
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {
        //ch.pipeline().addLast(new LineBasedFrameDecoder(1024));
        ch.pipeline().addLast(new StringDecoder());
        ch.pipeline().addLast(new RequestHandler());
        ch.pipeline().addLast(executor, "business", new ProviderLogicHandler());
        ch.pipeline().addLast(new StringEncoder());
    }

    private class RejectStrategy implements RejectedExecutionHandler {

        @Override
        public void rejected(Runnable task, SingleThreadEventExecutor executor) {

        }
    }
}
