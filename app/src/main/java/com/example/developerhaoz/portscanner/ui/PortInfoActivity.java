package com.example.developerhaoz.portscanner.ui;

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

    @Bind(R.id.port_info_rv)
    RecyclerView mPortInfoRv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_port_info);
        ButterKnife.bind(this);
        initView();
        PortScannerHelper.scanOneIp("192.168.199.209", 1, 500, 5, 2000);
    }

    private void initView() {

        initPortBeanList();
        mPortInfoRv.setLayoutManager(new LinearLayoutManager(this));
        PortInfoAdapter adapter = new PortInfoAdapter(mPortInfoBeanList);
        mPortInfoRv.setAdapter(adapter);
    }

    private void initPortBeanList() {
        if(mPortList.size() > 0){
            mPortList.clear();
        }
        PortScannerHelper.scanOneIp("192.168.199.209", 1, 500, 5, 2000);
        for (Integer integer : mPortList) {
            mPortInfoBeanList.add(new PortInfoBean("192.168.199.209", String.valueOf(integer), "", ""));
        }

    }
}
