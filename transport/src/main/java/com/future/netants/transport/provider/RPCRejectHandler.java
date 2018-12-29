package com.future.netants.transport.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by zhaofeng01 on 2018/12/29.
 */
public class RPCRejectHandler implements RejectedExecutionHandler {

    private static final Logger logger = LoggerFactory.getLogger(RPCRejectHandler.class);

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        if (logger.isDebugEnabled()) {
            logger.debug("Reject request {}", r);
        }

    }
}
