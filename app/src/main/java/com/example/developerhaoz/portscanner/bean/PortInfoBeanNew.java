package com.example.developerhaoz.portscanner.bean;

/**
 * Created by developerHaoz on 2017/6/14.
 */

public class PortInfoBeanNew {

    /**
     * port : 0
     * service : 保留端口；不使用（若发送过程不准备接受回复消息，则可以作为源端口）
     * type : ALL
     */

    private String port;
    private String service;
    private String type;

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
