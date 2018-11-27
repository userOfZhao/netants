package com.future.netants.transport.provider.handler;

import com.future.netants.core.seriliazer.JSON;
import com.future.netants.transport.message.MessageFactory;
import com.future.netants.transport.message.MessageRequest;
import com.future.netants.transport.message.MessageResponse;
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
public class TestHandler extends SimpleChannelInboundHandler {

    private static final Logger logger = LoggerFactory.getLogger(TestHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);

        String body = new String(req, Charset.forName("UTF-8"));
        logger.info("receive body from client {}", body);
        MessageResponse response = MessageFactory.getInstance().newResponse();
        MessageRequest request = JSON.parseJson(body, MessageRequest.class);
        response.setMessageId(request.getMessageId());
        response.setResult("hello boy. nice to meet you");
        channelHandlerContext.writeAndFlush(Unpooled.copiedBuffer(JSON.json(response).getBytes()));
    }
}
