package com.example.developerhaoz.portscanner.utils;

import com.example.developerhaoz.portscanner.bean.PortInfoBean;
import com.example.developerhaoz.portscanner.common.VolleyHelper;
import com.example.developerhaoz.portscanner.ui.PortInfoActivity;
import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;

/**
 * 只扫描一个 ip 地址
 * <p>
 * Created by developerHaoz on 2017/6/14.
 */

public class ScanMethod implements Runnable {

    /**
     * 目标 ip
     */
    private String ip;
    private int startPort;
    private int endPort;
    private int threadNumber;
    private int serial;
    private int timeout;

    public ScanMethod(String ip, int startPort, int endPort, int threadNumber, int serial, int timeout) {
        this.ip = ip;
        this.startPort = startPort;
        this.endPort = endPort;
        this.threadNumber = threadNumber;
        this.serial = serial;
        this.timeout = timeout;
    }

    @Override
    public void run() {
        int port = 0;
        try {
            InetAddress address = InetAddress.getByName(ip);
            Socket socket = null;
            SocketAddress socketAddress;

            for (port = startPort + serial; port <= endPort; port += threadNumber) {
                socket = new Socket();
                socketAddress = new InetSocketAddress(address, port);
                try {
                    socket.connect(socketAddress, timeout);
                    socket.close();
                    String response = VolleyHelper.sendHttpGet(PortInfoActivity.mContext, AddressDecoder.getAddress(String.valueOf(port)));
                    PortInfoBean portInfoBean = GsonHelper.getPortInfoBean(response);
                    PortInfoActivity.mPortList.add(portInfoBean);
                    Logger.d("端口" + port + "开放");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}














