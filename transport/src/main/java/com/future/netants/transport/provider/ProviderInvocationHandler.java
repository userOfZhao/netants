package com.future.netants.transport.provider;

import com.future.netants.core.seriliazer.JSON;
import com.future.netants.transport.message.*;

import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/**
 * Created by zhaofeng on 2018/11/28.
 */
public class ProviderInvocationHandler implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(ProviderInvocationHandler.class);

    private MessageRequest request;

    private Object object;

    private ChannelHandlerContext ctx;

    public ProviderInvocationHandler(MessageRequest request, ChannelHandlerContext channelHandlerContext) {
        this.request = request;
        this.ctx = channelHandlerContext;
        this.object = RPCProvider.getServerRef(request.getClassName());
    }

    @Override
    public void run() {
        String methodName = request.getMethod();
        try {
            Method method = object.getClass().getMethod(methodName, request.getParamTypes());
            Object result = method.invoke(object, request.getParams());
            MessageResponse response = MessageFactory.getInstance().newResponse();
            response.setMessageId(request.getMessageId());
            response.setCode(0);
            response.setResult(JSON.json(result));
            response.setReturnType(method.getReturnType());
            ctx.channel().writeAndFlush(JSON.json(response));
        } catch (NoSuchMethodException e) {
            logger.error("Not found RPC Method. provider class is {}, method is {}, message request is {}", object.getClass().getName(), methodName, request);
            // TODO
        } catch (InvocationTargetException e) {
            logger.error("failed to invocation method. class is {}, method is {}, request is {}",object.getClass().getName(), methodName, request);
        } catch (IllegalAccessException e) {
            logger.error("not have ");
        }
    }
}
