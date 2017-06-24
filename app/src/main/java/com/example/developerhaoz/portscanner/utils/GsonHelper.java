package com.example.developerhaoz.portscanner.utils;

import com.example.developerhaoz.portscanner.bean.PortInfoBean;
import com.example.developerhaoz.portscanner.ui.PortDataBean;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 *
 * Created by developerHaoz on 2017/6/23.
 */

public class GsonHelper {

    public static PortDataBean getPortDataBean(String response){

        PortDataBean portDataBean = new PortDataBean();
        try {
            JSONObject responseJson = new JSONObject(response);
            String data = responseJson.getString("data");
            Gson gson = new Gson();
            portDataBean = gson.fromJson(data, PortDataBean.class);
            return portDataBean;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return portDataBean;
    }

    public static PortInfoBean getPortInfoBean(String response){

        PortInfoBean portInfoBean = new PortInfoBean();
        try {
            JSONObject responseJson = new JSONObject(response);
            String data = responseJson.getString("data");
            JSONArray jsonArray = new JSONArray(data);
            String dataJson = jsonArray.get(0).toString();
            Logger.d(dataJson);
            Gson gson = new Gson();
            portInfoBean = gson.fromJson(dataJson, PortInfoBean.class);
            return portInfoBean;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return portInfoBean;
    }
}
