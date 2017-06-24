package com.example.developerhaoz.portscanner.common;

/**
 * Created by developerHaoz on 2017/6/24.
 */

public class PortInfoEvent {

    private String portInfo;

    public PortInfoEvent(String portInfo) {
        this.portInfo = portInfo;
    }

    public String getPortInfo() {
        return portInfo;
    }
}
