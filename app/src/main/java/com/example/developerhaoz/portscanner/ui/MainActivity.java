package com.example.developerhaoz.portscanner.ui;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;

import com.example.developerhaoz.portscanner.R;
import com.example.developerhaoz.portscanner.bean.PortInfoBean;
import com.example.developerhaoz.portscanner.common.VolleyHelper;
import com.example.developerhaoz.portscanner.utils.AddressDecoder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.main_iv_computer)
    ImageView mMainm;
    @Bind(R.id.main_tid_ip)
    TextInputEditText mTidIp;
    @Bind(R.id.main_tie_startPort)
    TextInputEditText mTieStartPort;
    @Bind(R.id.main_tie_endPort)
    TextInputEditText mTieEndPort;
    @Bind(R.id.main_btn_scanner)
    Button mBtnScanner;

    private static List<Integer> mOpenPortLists = new ArrayList<>();

    /**
     * 线程池中线程的数量
     */
    private static final int THREAN_NUMBER = 5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        final String url = "http://118.89.39.129:8888/scanner/port.json";

        Gson gson = new Gson();
        Type type = new TypeToken<List<PortInfoBean>>(){}.getType();
//        List<PortInfoBean> portInfoBeanList = gson.fromJson(VolleyHelper.PORT_INFO, type);
//        Logger.d(portInfoBeanList.size());

        Logger.d(AddressDecoder.getAddress("22"));
        VolleyHelper.sendHttpGet(this, AddressDecoder.getAddress("22"));


//        VolleyHelper.sendHttpGet(this, url, new VolleyResponseCallback() {
//            @Override
//            public void onSuccess(String response) {
////                Logger.json(response);
//                Logger.d(response);
//                Gson gson = new Gson();
//                Type type = new TypeToken<List<PortInfoBeanNew>>() {}.getType();
//                List<PortInfoBeanNew> portInfoBeanNew = gson.fromJson(response, type);
////                Logger.d(portInfoBeanNew.size());
//            }
//
//            @Override
//            public void onError(VolleyError error) {
//
//            }
//        });
    }

    /**
     * 开始扫描
     */
    private void startScan() {
        String ip = mTidIp.getText().toString();
        String startPort = mTieStartPort.getText().toString();
        String endPort = mTieEndPort.getText().toString();
        PortInfoActivity.startActivity(this, ip, startPort, endPort);
    }

    @OnClick(R.id.main_btn_scanner)
    public void onViewClicked() {
        startScan();
    }
}
