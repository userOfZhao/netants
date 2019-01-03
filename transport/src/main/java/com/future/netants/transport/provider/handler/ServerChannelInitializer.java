package com.future.netants.transport.provider.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * Created by zhaofeng01 on 2018/11/28.
 */
public class ServerChannelInitializer extends ChannelInitializer {

    private static final Logger logger = LoggerFactory.getLogger(ServerChannelInitializer.class);

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ch.pipeline().addLast(new StringDecoder());
        ch.pipeline().addLast(new RequestHandler());
        ch.pipeline().addLast(new StringEncoder());
    }
}
