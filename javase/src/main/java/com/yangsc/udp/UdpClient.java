package com.yangsc.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * <p>Description: UdpClient</p>
 *
 * <p>Copyright: © 2018-至今 All rights reserved.</p>
 *
 * @author yangsanchao
 * *
 * @date 2020/5/18 14:22
 **/
public class UdpClient {

    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress address = InetAddress.getByName("127.0.0.1");
        int port = 9090;
        String data = "udt data";
        DatagramPacket datagramPacket = new DatagramPacket(data.getBytes(), data.length(), address, port);
        socket.send(datagramPacket);
        socket.close();
    }
}
