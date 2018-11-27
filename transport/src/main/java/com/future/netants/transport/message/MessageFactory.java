package com.future.netants.transport.message;

import com.future.netants.core.factory.AbstractFactory;
import com.future.netants.core.factory.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zhaofeng01 on 2018/11/26.
 */
public class MessageFactory<T extends Message> extends AbstractFactory {

    private static final Logger logger = LoggerFactory.getLogger(MessageFactory.class);

    public static MessageFactory instance;

    private MessageFactory() {}

    public static MessageFactory getInstance() {
        if (instance == null) {
            synchronized (MessageFactory.class) {
                if (instance == null) {
                    instance = new MessageFactory();
                }
            }
        }
        return instance;
    }

    @Override
    public Message createObject(String name) {
        if ("request".equals(name)) {
            return new MessageRequest();
        } else if ("response".equals(name)) {
            return new MessageResponse();
        }
        return null;
    }

    public MessageResponse newResponse() {
        return new MessageResponse();
    }

    @Override
    public Factory createFactory() {
        return null;
    }
}
