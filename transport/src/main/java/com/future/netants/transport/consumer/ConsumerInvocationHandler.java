package com.future.netants.transport.consumer;

import com.future.netants.common.util.Utils;
import com.future.netants.transport.message.MessageFactory;
import com.future.netants.transport.message.MessageRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by zhaofeng01 on 2018/11/26.
 */
public class ConsumerInvocationHandler implements InvocationHandler {

    private static final Logger logger = LoggerFactory.getLogger(ConsumerInvocationHandler.class);

    private String interfaceName;

    public ConsumerInvocationHandler(String name) {
        this.interfaceName = name;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MessageRequest requestMessage = (MessageRequest) MessageFactory.getInstance().createObject("request");
        requestMessage.setMessageId(Utils.getUUID());
        requestMessage.setClassName(method.getDeclaringClass().getName());
        requestMessage.setMethod(method.getName());
        requestMessage.setParamTypes(method.getParameterTypes());
        requestMessage.setParams(args);
        return RPCServiceLoad.getInstance().sendMessage(interfaceName, requestMessage).get();
    }
}
