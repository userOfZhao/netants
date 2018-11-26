package com.future.netants.transport.provider.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;

/**
 * Created by zhaofeng01 on 2018/9/6.
 */
public class FirstHandler extends SimpleChannelInboundHandler {

    private static final Logger logger = LoggerFactory.getLogger(FirstHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);

        String body = new String(req, Charset.forName("UTF-8"));
        logger.info("receive body from client {}", body);

        channelHandlerContext.writeAndFlush(Unpooled.copiedBuffer("hello you".getBytes()));
    }
}
