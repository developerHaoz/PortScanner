package com.example.developerhaoz.portscanner.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.developerhaoz.portscanner.R;
import com.example.developerhaoz.portscanner.bean.PortInfoBean;
import com.example.developerhaoz.portscanner.utils.PortScannerHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by developerHaoz on 2017/6/14.
 */

public class PortInfoActivity extends AppCompatActivity {

    public static List<Integer> mPortList = new ArrayList<>();
    public static List<PortInfoBean> mPortInfoBeanList = new ArrayList<>();
    private static final String IP = "ip";
    private static final String STARTPORT = "startPort";
    private static final String ENDPORT = "endPort";
    private String ip;
    private String startPort;
    private String endPort;

    @Bind(R.id.port_info_rv)
    RecyclerView mPortInfoRv;

    public static void startActivity(Context context, String ip, String startPort, String endPort){
        Intent intent = new Intent(context, PortInfoActivity.class);
        intent.putExtra(IP, ip);
        intent.putExtra(STARTPORT, startPort);
        intent.putExtra(ENDPORT, endPort);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_port_info);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        ip = intent.getStringExtra(IP);
        startPort = intent.getStringExtra(STARTPORT);
        endPort = intent.getStringExtra(ENDPORT);
        initView();
    }

    private void initView() {
        initPortBeanList();
        mPortInfoRv.setLayoutManager(new LinearLayoutManager(this));
        PortInfoAdapter adapter = new PortInfoAdapter(mPortInfoBeanList);
        mPortInfoRv.setAdapter(adapter);
    }

    private void initPortBeanList() {
        if(mPortList.size() > 0){
            mPortList = new ArrayList<>();
            mPortInfoBeanList.clear();
        }
        if(startPort.length() > 0 && endPort.length() > 0){
            PortScannerHelper.scanOneIp(String.valueOf(ip), Integer.valueOf(startPort), Integer.valueOf(endPort), 100, 1000);

        }
        for (Integer integer : mPortList) {
            mPortInfoBeanList.add(new PortInfoBean("192.168.199.209", String.valueOf(integer), "sdf", "sdf"));
        }

    }
}
