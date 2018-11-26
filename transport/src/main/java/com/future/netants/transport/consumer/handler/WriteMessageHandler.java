package com.future.netants.transport.consumer.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zhaofeng01 on 2018/11/23.
 */
public class WriteMessageHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(WriteMessageHandler.class);

    private ChannelHandlerContext context;

    public void channelActive(ChannelHandlerContext context) {
        this.context = context;
    }

    public Object call(Object[] msg) throws Exception {
        context.writeAndFlush(msg);
        return null;
    }
}
