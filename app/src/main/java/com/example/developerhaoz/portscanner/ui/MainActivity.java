package com.example.developerhaoz.portscanner.ui;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.developerhaoz.portscanner.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.main_iv_computer)
    ImageView mMainm;
    @Bind(R.id.main_tid_ip)
    TextInputEditText mTidIp;
    @Bind(R.id.main_tie_startPort)
    TextInputEditText mTieStartPort;
    @Bind(R.id.main_tie_endPort)
    TextInputEditText mTieEndPort;

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
        startScan();
//        if(mOpenPortLists.size() > 0){
//            mOpenPortLists.clear();
//        }
//        PortScannerHelper.scanOneIp("192.168.199.209", 1, 500, 5, 2000);
//        for (Integer openPortList : mOpenPortLists) {
//            Logger.d(openPortList);
//        }
    }

    private void startScan() {
        String ip = mTidIp.getText().toString();
        String startPort = mTieStartPort.getText().toString();
        String endPort = mTieEndPort.getText().toString();
    }
}
