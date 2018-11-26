package com.future.netants.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

/**
 * Created by zhaofeng01 on 2018/11/26.
 */
public class Utils {

    private static final Logger logger = LoggerFactory.getLogger(Utils.class);

    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
