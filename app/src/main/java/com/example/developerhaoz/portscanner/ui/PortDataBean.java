package com.example.developerhaoz.portscanner.ui;

/**
 * Created by developerHaoz on 2017/6/23.
 */

public class PortDataBean {


    /**
     * port : 88
     * service : Kerberos - 认证代理
     * type : TCP
     */

    private String service;
    private String type;

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
