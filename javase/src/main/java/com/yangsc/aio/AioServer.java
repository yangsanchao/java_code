package com.yangsc.aio;

import lombok.extern.log4j.Log4j2;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.concurrent.CountDownLatch;

/**
 * <p>Description: AioServer</p>
 *
 * <p>Copyright: © 2018-至今 All rights reserved.</p>
 *
 * @author yangsanchao
 * *
 * @date 2020/5/19 19:14
 **/
@Log4j2
public class AioServer {

    public static void main(String[] args) throws Exception {

        AsynchronousServerSocketChannel channel = AsynchronousServerSocketChannel
                .open();
        channel.bind(new InetSocketAddress(9090));

        channel.accept(null, new CompletionHandler<AsynchronousSocketChannel, Void>() {
            @Override
            public void completed(final AsynchronousSocketChannel asynchronousSocketChannel, Void attachment) {
                channel.accept(null, this);

                ByteBuffer buffer = ByteBuffer.allocate(1024);
                asynchronousSocketChannel.read(buffer, buffer, new CompletionHandler<Integer, ByteBuffer>() {
                    @Override
                    public void completed(Integer result_num, ByteBuffer attachment) {
                        attachment.flip();
                        CharBuffer charBuffer = CharBuffer.allocate(1024);
                        CharsetDecoder decoder = Charset.defaultCharset().newDecoder();
                        decoder.decode(attachment, charBuffer, false);
                        charBuffer.flip();
                        String data = new String(charBuffer.array(), 0, charBuffer.limit());
                        log.info("data:{}", data);
                        try {
                            asynchronousSocketChannel.close();
                        } catch (Exception e) {
                            log.info(e);
                        }
                    }

                    @Override
                    public void failed(Throwable exc, ByteBuffer attachment) {
                        log.info("read error");
                    }
                });
            }

            @Override
            public void failed(Throwable exc, Void attachment) {
                System.out.println("accept error");
            }
        });
        while (true){
            Thread.sleep(1000);
        }
    }
}

