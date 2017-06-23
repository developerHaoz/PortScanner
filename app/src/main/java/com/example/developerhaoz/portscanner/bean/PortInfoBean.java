package com.example.developerhaoz.portscanner.bean;

/**
 * Created by developerHaoz on 2017/6/14.
 */

public class PortInfoBean {

    private String port;
    private String service;
    private String type;

    public PortInfoBean( String port, String service, String type) {
        this.port = port;
        this.service = service;
        this.type = type;
    }

    public PortInfoBean(){

    }

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
