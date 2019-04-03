package com.bihang.seaya;

import com.bihang.seaya.environment.Environment;
import com.bihang.seaya.handler.SeayaHandler;
import com.bihang.seaya.log.PrintSeayaLogo;
import com.bihang.seaya.text.TextHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;
import lombok.extern.slf4j.Slf4j;

/**
 * Created By bihang
 * 2018/12/21 17:03
 */
@Slf4j
public class SeayaServer {

    public static Environment environment = Environment.getInstance();

    public static EventLoopGroup group = new NioEventLoopGroup();

    private static Channel channel ;
    /**
     * 以默認端口8007开启服务器
     * @throws Exception
     */
    public static void start() throws Exception {
        int port = environment.getPort();
        PrintSeayaLogo.print();
        long start = System.currentTimeMillis();
        ServerBootstrap b = new ServerBootstrap();
        b.group(group)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new HttpServerCodec())
                                .addLast(new SeayaHandler())
                        ;
                    }
                });
        channel = b.bind(port).sync().channel();
        long end = System.currentTimeMillis();
        log.info("Seaya initialize successfully !");
        log.info("Seaya started on port: {}", port);
        log.info("Seaya started cost: {}ms", end - start);
        log.info("Open browser access: http://127.0.0.1:{}",port);
    }

    /**
     * 以指定端口开启服务器
     * @param port
     * @throws Exception
     */
    public static void start(int port) throws Exception {
        environment.setPort(port);
        start();
    }

    public static void start(TextHandler textHandler) throws Exception {
        textHandler.text();
        start();
    }

    public static void start(Class<?> startClass) throws Exception {

        String rootPackageName = startClass.getPackage().getName();
        environment.setRootPackageName(rootPackageName);
        start();
    }

    public static void join() throws InterruptedException {
        channel.closeFuture().sync();
    }


    public static void stop() {
        log.info("Seaya shutdown...");
        if (group != null)
            group.shutdownGracefully();
        log.info("Seaya shutdown successful");
    }

    private static void shutdownHook() {
        Thread shutdownThread = new Thread(()->{
            SeayaServer.stop();
        });
        shutdownThread.setName("shutdown@thread");
        Runtime.getRuntime().addShutdownHook(shutdownThread);
    }
}
