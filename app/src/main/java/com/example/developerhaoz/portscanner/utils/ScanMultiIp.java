package com.example.developerhaoz.portscanner.utils;

import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.Set;

/**
 * 扫描多个 Ip
 *
 * Created by developerHaoz on 2017/6/14.
 */

public class ScanMultiIp implements Runnable{

    private String ip;
    private Set<Integer> portSet;
    private int threadNumber;
    /**
     * 记录当前是第几个线程
     */
    private int serial;
    private int timeout;

    public ScanMultiIp(String ip, Set<Integer> portSet, int threadNumber, int serial, int timeout) {
        this.ip = ip;
        this.portSet = portSet;
        this.threadNumber = threadNumber;
        this.serial = serial;
        this.timeout = timeout;
    }

    @Override
    public void run() {
        int port = 0;
        Integer[] ports = portSet.toArray(new Integer[portSet.size()]);
        try{
            InetAddress address = InetAddress.getByName(ip);
            Socket socket;
            SocketAddress socketAddrss;
            if (ports.length < 1){
                return;
            }
            for (port = 0 + serial;port <= ports.length - 1;port += threadNumber){
                socket = new Socket();
                socketAddrss = new InetSocketAddress(address, ports[port]);
                try {
                    socket.connect(socketAddrss, timeout);
                    socket.close();
                    Logger.d("端口" + ports[port] +"开放");
                }catch (IOException e){
                   e.printStackTrace();
                }
            }

        }catch (UnknownHostException e){
            e.printStackTrace();
        }
    }
}

















