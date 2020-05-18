package com.yangsc.udp;

import lombok.extern.log4j.Log4j2;

import javax.xml.crypto.Data;
import java.net.*;

/**
 * <p>Description: UdpServer</p>
 *
 * <p>Copyright: © 2018-至今 All rights reserved.</p>
 *
 * @author yangsanchao
 * *
 * @date 2020/5/18 14:21
 **/
@Log4j2
public class UdpServer {
    public static void main(String[] args) throws Exception {

        DatagramSocket dSocket = new DatagramSocket(9090);
        //创建数据包（接收容器）
        byte[] bys = new byte[1024];
        DatagramPacket dPacket = new DatagramPacket(bys, bys.length);
        //调用接收方法
        dSocket.receive(dPacket);
        //数据包解析
        InetAddress address = dPacket.getAddress();
        String hostAddress = address.getHostAddress();
        byte[] data = dPacket.getData();
        String message = new String(data);
        log.info("hostAddress:{} message:{}", hostAddress, message);
        //资源释放
//        dSocket.close();

    }

}
