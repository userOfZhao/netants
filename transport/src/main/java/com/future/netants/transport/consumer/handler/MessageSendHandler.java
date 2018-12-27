package com.future.netants.transport.consumer.handler;

import com.future.netants.core.seriliazer.JSON;
import com.future.netants.transport.message.Message;
import com.future.netants.transport.message.MessageNotify;
import com.future.netants.transport.message.MessageRequest;
import com.future.netants.transport.message.MessageResponse;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zhaofeng01 on 2018/11/26.
 */
public class MessageSendHandler extends SimpleChannelInboundHandler {

    private static final Logger logger = LoggerFactory.getLogger(MessageSendHandler.class);

    private ChannelHandlerContext ctx;

    private volatile ConcurrentHashMap<String, MessageNotify> currentMsg = new ConcurrentHashMap<>();

    public void channelRegistered(ChannelHandlerContext context) {
        this.ctx = context;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        String body = (String) msg;
        if (logger.isDebugEnabled()) {
            logger.info("receive response from remote server {}", body);
        }
        MessageResponse response = JSON.parseJson(body, MessageResponse.class);
        MessageNotify notify = currentMsg.get(response.getMessageId());
        currentMsg.remove(response.getMessageId());
        if (notify != null) {
            notify.messageNotify(response);
        }
    }

    public MessageNotify sendMsg(MessageRequest msg) {
        MessageNotify notify = new MessageNotify(msg.getMessageId());
        currentMsg.put(msg.getMessageId(), notify);
        ctx.channel().writeAndFlush(JSON.json(msg));
        return notify;
    }
}
