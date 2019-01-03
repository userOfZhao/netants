package com.future.netants.transport.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by zhaofeng01 on 2018/11/26.
 */
@Data
public class MessageRequest extends Message implements Serializable {

    /**
     * 消息的唯一ID
     */
    @JsonProperty("m_id")
    private String messageId;

    /**
     * 服务提供方接口的类名称
     */
    @JsonProperty("c_n")
    private String className;

    /**
     * 服务提供方接口的方法名称
     */
    private String method;

    /**
     * 方法的参数类型
     */
    @JsonProperty("p_type")
    private Class<?>[] paramTypes;

    /**
     * 方法的参数
     */
    private Object[] params;
}
