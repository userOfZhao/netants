package com.future.netants.transport.provider.handler;

import com.future.netants.core.seriliazer.JSON;
import com.future.netants.transport.message.MessageFactory;
import com.future.netants.transport.message.MessageRequest;
import com.future.netants.transport.message.MessageResponse;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zhaofeng01 on 2018/9/6.
 */
public class RequestHandler extends SimpleChannelInboundHandler {

    private static final Logger logger = LoggerFactory.getLogger(RequestHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object msg) throws Exception {
        String request = (String) msg;
        logger.info("receive body from client {}", request);
        MessageResponse response = MessageFactory.getInstance().newResponse();
        MessageRequest messageRequest = JSON.parseJson(request, MessageRequest.class);
        response.setMessageId(messageRequest.getMessageId());
        response.setResult("hello boy. nice to meet you");
        channelHandlerContext.writeAndFlush(Unpooled.copiedBuffer(JSON.json(response).getBytes()));
    }
}
