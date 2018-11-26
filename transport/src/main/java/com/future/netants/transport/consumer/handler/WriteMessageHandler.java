package com.future.netants.transport.consumer.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;

/**
 * Created by zhaofeng01 on 2018/11/23.
 */
public class WriteMessageHandler extends SimpleChannelInboundHandler {

    private static final Logger logger = LoggerFactory.getLogger(WriteMessageHandler.class);

    private ChannelHandlerContext context;

    public void channelRegistered(ChannelHandlerContext context) {
        this.context = context;
    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);

        String body = new String(req, Charset.forName("UTF-8"));
        logger.info("receive body from server {}", body);
    }

    public String call(Object[] msg) throws Exception {
        byte[] bytes = ((String) msg[0]).getBytes();
        context.channel().writeAndFlush(Unpooled.copiedBuffer(bytes));
        return "ok";
    }
}
