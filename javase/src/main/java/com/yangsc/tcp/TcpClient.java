package com.yangsc.tcp;

import java.io.OutputStream;
import java.net.Socket;

/**
 * <p>Description: TcpClient</p>
 *
 * <p>Copyright: © 2018-至今 All rights reserved.</p>
 *
 * @author yangsanchao
 * *
 * @date  2020/5/18 14:56
 **/
public class TcpClient {
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("127.0.0.1",9020);

        String data = "tcp data";
        OutputStream outputStream  = socket.getOutputStream();
        outputStream.write(data.getBytes(),0,data.length());

        socket.close();
    }
}
