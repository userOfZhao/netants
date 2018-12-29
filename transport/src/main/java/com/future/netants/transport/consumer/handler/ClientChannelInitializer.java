package com.future.netants.transport.consumer.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zhaofeng01 on 2018/11/27.
 */
public class ClientChannelInitializer extends ChannelInitializer {

    private static final Logger logger = LoggerFactory.getLogger(ClientChannelInitializer.class);

    @Override
    protected void initChannel(Channel ch) throws Exception {
        //ch.pipeline().addLast(new LineBasedFrameDecoder(1024));
        ch.pipeline().addLast(new StringDecoder());
        ch.pipeline().addLast(new MessageSendHandler());
        ch.pipeline().addLast(new StringEncoder());
    }
}
