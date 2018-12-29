package com.future.netants.transport.provider.handler;

import com.future.netants.transport.message.MessageFactory;
import com.future.netants.transport.message.MessageRequest;
import com.future.netants.transport.message.MessageResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zhaofeng01 on 2018/12/28.
 */
public class ProviderLogicHandler extends SimpleChannelInboundHandler {

    private static final Logger logger = LoggerFactory.getLogger(ProviderLogicHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        MessageRequest request = (MessageRequest) msg;
        logger.info("msg is {}", msg);
        try {
            Thread.currentThread().sleep(5000);
        } catch (Exception e) {
            logger.error("error", e);
        }
        logger.info("finish");
        MessageResponse response = MessageFactory.getInstance().newResponse();
        response.setMessageId(request.getMessageId());
        response.setResult("hello boy. nice to meet you");
        ctx.writeAndFlush(response);
    }
}
