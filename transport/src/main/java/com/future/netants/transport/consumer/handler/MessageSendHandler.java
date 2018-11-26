package com.future.netants.transport.consumer.handler;

import com.future.netants.transport.message.Message;
import com.future.netants.transport.message.MessageResponse;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;

/**
 * Created by zhaofeng01 on 2018/11/26.
 */
public class MessageSendHandler extends SimpleChannelInboundHandler {

    private static final Logger logger = LoggerFactory.getLogger(MessageSendHandler.class);

    private ChannelHandlerContext ctx;

    public void channelRegistered(ChannelHandlerContext context) {
        this.ctx = context;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);

        String body = new String(req, Charset.forName("UTF-8"));
        logger.info("receive body from server {}", body);
    }

    public Message sendMsg(Message msg) {
        ctx.channel().writeAndFlush(Unpooled.copiedBuffer(msg.toString().getBytes()));
        return new Message();
    }
}
