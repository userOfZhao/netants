package com.future.netants.transport.provider.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executor;

/**
 * Created by zhaofeng01 on 2018/12/29.
 */
public interface BusinessService {

//    private static final Logger logger = LoggerFactory.getLogger(BusinessService.class);

    /**
     * shutdown business logic thread pool gracefully
     */
    void shutDownGracefully();

    /**
     * get Executor instance from thread pool
     * @return  Executor
     */
    Executor getExecutor();

    /**
     *  execute a task
     */
    void execute(Runnable runnable);
}
