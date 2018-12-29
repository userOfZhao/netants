package com.future.netants.transport.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * Created by zhaofeng01 on 2018/12/29.
 */
public class BusinessServiceExecutor implements BusinessService {

    private static final Logger logger = LoggerFactory.getLogger(BusinessServiceExecutor.class);

    private static BusinessServiceExecutor instance = new BusinessServiceExecutor();

    private ThreadPoolExecutor executor;

    private static int Default_Pool_Size = Math.max(2 * Runtime.getRuntime().availableProcessors(), 4);

    private static int Default_Queue_Size = 500;

    private BusinessServiceExecutor() {}

    public static BusinessServiceExecutor getInstance() {
        return instance;
    }

    public void init() {
        init(Default_Pool_Size, Default_Queue_Size, false);
    }

    public void init(int corePoolSize, int queueSize, boolean prestart) {
        this.executor = new ThreadPoolExecutor(corePoolSize, corePoolSize, 0, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(queueSize), new RPCRejectHandler());
        if (prestart) {
            executor.prestartAllCoreThreads();
        }
    }

    @Override
    public void shutDownGracefully() {
        if (executor == null) {
            return;
        }
        executor.shutdown();
        do {
            try {
                executor.awaitTermination(1000, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                logger.error("Interrupted from shutdown business service pool", e);
                break;
            }
        } while (!executor.isShutdown());
    }

    @Override
    public Executor getExecutor() {
        return executor;
    }

    @Override
    public void execute(Runnable runnable) {
        executor.execute(runnable);
    }
}
