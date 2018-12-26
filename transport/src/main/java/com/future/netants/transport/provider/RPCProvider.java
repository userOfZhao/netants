package com.future.netants.transport.provider;

import com.future.netants.common.util.StringUtils;
import com.future.netants.transport.config.ProviderConfig;
import com.future.netants.transport.config.RPCConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zhaofeng on 2018/11/27.
 * RPCProvider class
 */
public class RPCProvider<T> implements Provider {

    private static final Logger logger = LoggerFactory.getLogger(RPCProvider.class);

    /**
     * 服务端配置
     */
    private volatile ProviderConfig config;

    /**
     * 发布服务名称
     */
    private String interfaceId;

    /**
     * 服务接口名称
     */
    private String interfaceName;

    /**
     * 服务提供者实例对象
     */
    private T instance;

    /**
     * 服务提供者索引
     * key : className      服务提供类名称
     * value : instance     服务提供者实际对象
     */
    private static ConcurrentHashMap<String, Object> InstanceMap = new ConcurrentHashMap<>();

    /**
     * 获取服务实现类
     */
    public static Object getServerRef(String className) {
        return InstanceMap.get(className);
    }

    @Override
    public void export() {
        if (StringUtils.isEmpty(interfaceId) || instance == null) {
            throw new RuntimeException("Do not set interfaceId or ref instance");
        }
        if (StringUtils.isEmpty(interfaceName)) {
            if (instance.getClass().getInterfaces().length > 1) {
                throw new RuntimeException("interfaceName not set and ref instance has implements more than two interface");
            }
            interfaceName = instance.getClass().getInterfaces()[0].getName();
        }
        InstanceMap.putIfAbsent(interfaceName, instance);
        RPCServerInit.getInstance().setConfig(config).start();

    }

    @Override
    public Provider setRef(Object object) {
        this.instance = (T) object;
        return this;
    }


    @Override
    public Provider setInterfaceId(String interfaceName) {
        this.interfaceId = interfaceName;
        return this;
    }

    @Override
    public Provider setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
        return this;
    }

    @Override
    public Provider setConfig(ProviderConfig config) {
        this.config = config;
        return this;
    }
}
