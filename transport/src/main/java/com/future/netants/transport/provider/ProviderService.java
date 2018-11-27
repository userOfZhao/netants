package com.future.netants.transport.provider;

import com.future.netants.core.seriliazer.JSON;
import com.future.netants.transport.message.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/**
 * Created by zhaofeng on 2018/11/28.
 */
public class ProviderService implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(ProviderService.class);

    private MessageRequest request;

    private Object object;

    private MessageNotify notify;

    public ProviderService(MessageRequest request, Object object, MessageNotify notify) {
        this.request = request;
        this.object = object;
        this.notify = notify;
    }

    @Override
    public void run() {
        String methodName = request.getMethod();
        try {
            Method method = object.getClass().getDeclaredMethod(methodName, request.getTypeParams());
            Object result = method.invoke(object, request.getParams());
            MessageResponse response = MessageFactory.getInstance().newResponse();
            response.setCode(0);
            response.setResult(JSON.json(result));
            response.setReturnType(method.getReturnType());
            notify.messageNotify(response);
        } catch (NoSuchMethodException e) {
            logger.error("Not found RPC Method. class is {}, method is {}, message request is {}", object.getClass().getName(), methodName, request);
            // TODO
        } catch (InvocationTargetException e) {
            logger.error("failed to invocation method. class is {}, method is {}, request is {}",object.getClass().getName(), methodName, request);
        } catch (IllegalAccessException e) {
            logger.error("not have ");
        }
    }
}
