package com.future.netants.transport.provider.handler;

import com.future.netants.core.seriliazer.JSON;
import com.future.netants.transport.message.MessageRequest;
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
        if (logger.isDebugEnabled()) {
            logger.debug("receive body from client {}", request);
        }
        MessageRequest messageRequest = JSON.parseJson(request, MessageRequest.class);
        BusinessServiceExecutor.getInstance().execute(new ProviderInvocation(messageRequest, channelHandlerContext));
    }
}
