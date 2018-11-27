package com.future.netants.transport.message;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static com.future.netants.core.rpc.conf.ConfConstant.DEFAULT_MESSAGE_WAIT;

/**
 * Created by zhaofeng01 on 2018/11/27.
 */
public class MessageNotify {

    private static final Logger logger = LoggerFactory.getLogger(MessageNotify.class);

    /**
     * 消息id, 用于消息通知
     */
    private String messageId;

    /**
     * 返回的消息对象
     */
    private MessageResponse response;

    /**
     * 用来同步通知消息
     */
    private volatile CountDownLatch countDownLatch = new CountDownLatch(1);

    public MessageNotify(String messageId) {
        this.messageId = messageId;
    }

    /**
     * 获取远程服务结果，同步阻塞，等待远程服务端返回
     * @return  远程返回接口
     */
    public Object get() {
        String result = null;
        try {
            countDownLatch.await(DEFAULT_MESSAGE_WAIT, TimeUnit.MILLISECONDS);
            if (response != null) {
                result = response.getResult();
            }
        } catch (InterruptedException e) {
            logger.error("wait to get rpc response error. messageId is {}", messageId, e);
        }
        return result;
    }

    /**
     * 通知消息已经到达
     */
    public void messageNotify(MessageResponse response) {
        this.response = response;
        countDownLatch.countDown();
    }
}
