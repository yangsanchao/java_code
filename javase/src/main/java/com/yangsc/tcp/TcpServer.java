package com.yangsc.tcp;

import lombok.extern.log4j.Log4j2;

import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * <p>Description: TcpServer</p>
 *
 * <p>Copyright: © 2018-至今 All rights reserved.</p>
 *
 * @author yangsanchao
 * *
 * @date 2020/5/18 14:49
 **/
@Log4j2
public class TcpServer {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(9020);
        Socket socket = serverSocket.accept();
        InputStream socketInputStream = socket.getInputStream();
        byte bytes[] = new byte[1024];
        socketInputStream.read(bytes, 0, bytes.length);
        String message = new String(bytes);
        log.info("message:{}", message);
//        serverSocket.close();
    }
}
