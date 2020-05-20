package com.yangsc.nio;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * <p>Description: NioServer</p>
 *
 * <p>Copyright: © 2018-至今 All rights reserved.</p>
 *
 * @author yangsanchao
 * *
 * @date 2020/5/19 17:29
 **/
@Log4j2
public class NioServer {

    public static void main(String[] args) throws Exception {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(9090));
        serverSocketChannel.configureBlocking(false);

        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        new Thread(() -> {
            try {
                while (true) {
                    if (selector.select(1) > 0) {
                        Set<SelectionKey> selectedKeys = selector.selectedKeys();
                        Iterator<SelectionKey> it = selectedKeys.iterator();
                        SelectionKey key = null;
                        while (it.hasNext()) {
                            key = it.next();
                            try {
                                handle(key, selector);
                            } catch (Exception e) {
                                if (key != null) {
                                    key.cancel();
                                    if (key.channel() != null) {
                                        key.channel().close();
                                    }
                                }
                            } finally {
                                it.remove();
                            }
                        }
                    }
                }
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }).start();
    }

    private static void handle(SelectionKey key, Selector selector) throws IOException {

        if (key.isValid()) {
            // 处理新接入的请求消息
            if (key.isAcceptable()) {
                log.info("new channel ...");
                // Accept the new connection
                ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                SocketChannel sc = ssc.accept();
                sc.configureBlocking(false);
                // Add the new connection to the selector
                sc.register(selector, SelectionKey.OP_READ);
            }
            if (key.isReadable()) {
                // Read the data
                SocketChannel sc = (SocketChannel) key.channel();
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                int readBytes = sc.read(readBuffer);
                if (readBytes > 0) {
                    readBuffer.flip();
                    byte[] bytes = new byte[readBuffer.remaining()];
                    readBuffer.get(bytes);
                    String data = new String(bytes, "UTF-8");
                    log.info("data:{}", data);
                } else if (readBytes < 0) {
                    // 对端链路关闭
                    key.cancel();
                    sc.close();
                }
            }
        }
    }
}
