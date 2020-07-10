package com.yangsc.netty;

import io.netty.channel.nio.NioEventLoopGroup;
import lombok.extern.log4j.Log4j2;

import java.time.temporal.ChronoUnit;
import java.util.Currency;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * <p>Description: NettyDemo</p>
 *
 * <p>Copyright: © 2018-至今 All rights reserved.</p>
 *
 * @author yangsanchao
 * *
 * @date  2020/6/9 17:47
 **/
@Log4j2
public class NettyDemo {
    public static void main(String[] args) {
        NioEventLoopGroup nioEventLoop = new NioEventLoopGroup();
        log.info(nioEventLoop);
    }
}
