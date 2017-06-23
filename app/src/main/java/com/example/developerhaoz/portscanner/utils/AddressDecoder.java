package com.example.developerhaoz.portscanner.utils;

/**
 * Created by developerHaoz on 2017/6/23.
 */

public class AddressDecoder {

    String address = "http://118.89.39.129:8888/scanner/88/detail";

    /**
     * 根据端口地址获取相应的 Url
     *
     * @param port
     * @return
     */
    public static String getAddress(String port){
        String address = "";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("http://118.89.39.129:8888/scanner/");
        stringBuilder.append(port);
        stringBuilder.append("/detail");
        return String.valueOf(stringBuilder);
    }
}
