package com.future.netants.core.seriliazer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.future.netants.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TimeZone;

/**
 * Created by zhaofeng01 on 2018/11/27.
 */
public class JSON {

    private static final Logger logger = LoggerFactory.getLogger(JSON.class);

    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.setTimeZone(TimeZone.getDefault());
        //objectMapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_EMPTY);
    }

    /**
     * 将实例序列化成json字符串
     */
    public static String json(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            logger.error("convert object to json failed, obj is {}", obj.toString(), e);
            throw new RuntimeException("json seriliazer failed.");
        }
    }

    /**
     * 将字符串反序列化成对象
     */
    public static <T> T parseJson(String json, Class<T> clazz) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        try {
            return objectMapper.readValue(json, clazz);
        } catch (Exception e) {
            logger.error("parse json to object failed. json is {}, class is {}", json, clazz.getName(), e);
            throw new RuntimeException("json deseriliazer failed");
        }
    }
}
