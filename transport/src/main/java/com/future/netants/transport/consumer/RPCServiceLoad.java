package com.future.netants.transport.consumer;

import com.future.netants.core.balance.LoadBalance;
import com.future.netants.core.balance.LoadBalanceFactory;
import com.future.netants.core.rpc.conf.ConfConstant;
import com.future.netants.transport.config.RPCConfig;
import com.future.netants.transport.consumer.handler.ClientChannelInitializer;
import com.future.netants.transport.consumer.handler.MessageSendHandler;
import com.future.netants.transport.message.MessageNotify;
import com.future.netants.transport.message.MessageRequest;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.future.netants.core.rpc.conf.ConfConstant.DEFAULT_SERVER_HOST;
import static com.future.netants.core.rpc.conf.ConfConstant.DEFAULT_SERVER_PORT;

/**
 * Created by zhaofeng on 2018/11/26.
 */
public class RPCServiceLoad {

    private static final Logger logger = LoggerFactory.getLogger(RPCServiceLoad.class);

    /**
     * RPCServiceLoad 实例
     */
    private static RPCServiceLoad instance;

    /**
     * 客户端RPC配置
     */
    private RPCConfig config;

    /**
     * 所有远程连接
     * 数据结构 : Map<interfaceName, Map<host:port, ChannelFuture>>
     */
    private volatile ConcurrentHashMap<String, ConcurrentHashMap<String, ChannelFuture>> connections = new ConcurrentHashMap<>();

    private RPCServiceLoad() {}

    /**
     * 获取实例对象
     * @return  RPCServiceLoad 实例对象
     */
    public static RPCServiceLoad getInstance() {
        if (instance == null) {
            synchronized (RPCServiceLoad.class) {
                if (instance == null) {
                    instance = new RPCServiceLoad();
                }
            }
        }
        return instance;
    }

    /**
     * 向远程服务发送信息
     * @param interfaceName     接口名称
     * @param request           请求对象
     * @return                  待回复的结果
     */
    public MessageNotify sendMessage(String interfaceName, MessageRequest request) {
        LoadBalance loadBalance = LoadBalanceFactory.getInstance().createObject(config.getString("loadBalance", "random"));
        ChannelFuture future = loadBalance.getFuture(connections.get(interfaceName));
        return future.channel().pipeline().get(MessageSendHandler.class).sendMsg(request);
    }

    /**
     * 加载客户端配置
     * @param clientConfig 客户端配置
     * @return RPCServiceLoad 实例，用于构建
     */
    public RPCServiceLoad loadConfig(RPCConfig clientConfig) {
        if (clientConfig == null) {
            throw new IllegalArgumentException("RPC Client config can not be null");
        }
        this.config = clientConfig;
        return this;
    }

    /**
     * 客户端加载RPC远程服务
     */
    void loadRPCService(String interfaceName) {
        if (logger.isDebugEnabled()) {
            logger.debug("load rpc service now...");
        }
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        try {
            String host = config.getString(ConfConstant.SERVER_HOST, DEFAULT_SERVER_HOST);
            int port = config.getInt(ConfConstant.SERVER_PORT, DEFAULT_SERVER_PORT);
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(bossGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ClientChannelInitializer());
            ChannelFuture future = bootstrap.connect(host, port).sync();
            setChannel(interfaceName, host, port, future);
        } catch (Exception e) {
            logger.error("Interrupted occurs", e);
        }
    }

    /**
     * 移除一个链接
     * @param provider  服务提供者名称
     * @param host      host地址
     * @param port      端口号
     */
    public void removeChannel(String provider, String host, int port) {
        //TODO
    }

    /**
     * 设置链接信息
     * @param provider  服务名称
     * @param host      rpc服务器地址
     * @param port      rpc服务端口
     * @param future    rpc服务链接
     */
    private synchronized void setChannel(String provider, String host, int port, ChannelFuture future) {
        Map<String, ChannelFuture> map = connections.get(provider);
        if (map == null || map.isEmpty()) {
            ConcurrentHashMap<String, ChannelFuture> channels = new ConcurrentHashMap<>();
            channels.put(host.concat(String.valueOf(port)), future);
            connections.putIfAbsent(provider, channels);
        } else {
            map.putIfAbsent(host.concat(String.valueOf(port)), future);
        }
    }
}
