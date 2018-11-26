package com.future.netants.transport.message;


import java.io.Serializable;

/**
 * Created by zhaofeng01 on 2018/11/26.
 */
public class MessageResponse extends Message implements Serializable{

    /**
     * 消息的唯一ID
     */
    private String messageId;

    /**
     * 请求结果
     */
    private String body;
}
