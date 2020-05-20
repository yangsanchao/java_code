package com.yangsc.bio;

import lombok.extern.log4j.Log4j2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

/**
 * <p>Description: BioServer</p>
 *
 * <p>Copyright: © 2018-至今 All rights reserved.</p>
 *
 * @author yangsanchao
 * *
 * @date 2020/5/19 11:42
 **/
@Log4j2
public class BioServer {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(9090);
        while (true) {
            log.info("-- serverSocket before accept --");
            Socket socket = serverSocket.accept();
            log.info("-- serverSocket end accept --");
            new Thread(() -> {
                try {
                    // 读内容
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String readLine = bufferedReader.readLine();
                    log.info("thread:{} client :{}", Thread.currentThread().getName(), readLine);
                } catch (IOException e) {
                    log.error(e);
                }
            }).run();
        }
    }
}
