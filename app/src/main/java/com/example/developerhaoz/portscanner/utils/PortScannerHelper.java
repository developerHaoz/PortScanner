package com.example.developerhaoz.portscanner.utils;

import com.orhanobut.logger.Logger;

import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 端口扫描的帮助类
 *
 * Created by developerHaoz on 2017/6/14.
 */

public class PortScannerHelper {

    /**
     * 只扫描一个 ip
     *
     * @param ip  目标 ip 地址
     * @param startPort  开始的端口
     * @param endPort  结束的端口
     * @param threadNumber  线程开启的数量
     * @param timeout  连接超时时间
     */
    public static void scanOneIp(String ip, int startPort, int endPort, int threadNumber, int timeout){
        ExecutorService threadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < threadNumber; i++) {
            ScanMethod scanMethod = new ScanMethod(ip, startPort, endPort, threadNumber, i, timeout);
            threadPool.execute(scanMethod);
        }
        threadPool.shutdown();
        while(true){
            if(threadPool.isTerminated()){
                Logger.d("扫描结束");
                break;
            }
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    private static void scanMultiPorts(String ip, Set<Integer> portSet, int threadNumber, int timeout){
        ExecutorService threadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < threadNumber; i++) {
            ScanMultiIp scanMultiIp = new ScanMultiIp(ip, portSet, threadNumber, i, timeout);
            threadPool.execute(scanMultiIp);
        }
        threadPool.shutdown();
        while(true){
            if(threadPool.isTerminated()){
                Logger.d("扫描结束");
                break;
            }
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
















