package com.yangsc.bio;

import lombok.extern.log4j.Log4j2;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * <p>Description: BioClient</p>
 *
 * <p>Copyright: © 2018-至今 All rights reserved.</p>
 *
 * @author yangsanchao
 * *
 * @date 2020/5/19 14:35
 **/
@Log4j2
public class BioClient {
    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 1000; i++) {
            Integer tmp = i;
            new Thread(() -> {
                try {
                    Socket socket = new Socket(InetAddress.getByName("127.0.0.1"), 9090);
                    OutputStream outputStream = socket.getOutputStream();
                    PrintWriter printWriter = new PrintWriter(outputStream);
                    printWriter.write("client message index: " + tmp);
                    printWriter.flush();
                    log.info("index:{}", tmp);
                } catch (Exception e) {
                    log.error(e);
                }
            }).run();
        }

    }
}
