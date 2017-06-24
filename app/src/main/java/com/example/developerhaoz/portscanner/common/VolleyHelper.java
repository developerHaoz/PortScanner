package com.example.developerhaoz.portscanner.common;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by developerHaoz on 2017/6/14.
 */

public class VolleyHelper {

    private static String response = "";
    /**
     * 用于发送 Get 请求的封装方法
     *
     * @param context  Activity 的实例
     * @param url      请求的地址
     * @param callback 用于网络回调的接口
     */
    public static void sendHttpGet(Context context, String url, final VolleyResponseCallback callback) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(url
                , new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                callback.onSuccess(s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onError(error);
            }
        });
        requestQueue.add(stringRequest);
    }

    public static String sendHttpGet(Context context, String url) {
        String responseData = "";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        try {
            okhttp3.Response response = client.newCall(request).execute();
            responseData = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return responseData;
//
//        RequestQueue requestQueue = Volley.newRequestQueue(context);
//        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String s) {
//                response = s;
//                PortInfoBean portInfoBean = GsonHelper.getPortInfoBean(response);
//                PortInfoActivity.mPortInfoBeanList.add(portInfoBean);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Logger.d(error);
//            }
//        });
//        requestQueue.add(stringRequest);
//        return response;
//    }

    }

}
