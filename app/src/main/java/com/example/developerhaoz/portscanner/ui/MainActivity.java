package com.example.developerhaoz.portscanner.ui;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.developerhaoz.portscanner.R;

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
    @Bind(R.id.main_tie_start_ip)
    TextInputEditText mTieStartIp;
    @Bind(R.id.main_tie_end_ip)
    TextInputEditText mTieEndIp;
    @Bind(R.id.main_ll_one)
    LinearLayout mLlOne;
    @Bind(R.id.main_ll_multi)
    LinearLayout mLlMulti;

    private static final int MODULE_ONE = 1;
    private static final int MODULE_MULTI = 2;

    private int module = 1;

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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.scan_one:
                module = MODULE_ONE;
                mLlMulti.setVisibility(View.GONE);
                mLlOne.setVisibility(View.VISIBLE);
                break;
            case R.id.scan_multi:
                module = MODULE_MULTI;
                mLlOne.setVisibility(View.GONE);
                mLlMulti.setVisibility(View.VISIBLE);
                break;
        }
        return true;
    }

    /**
     * 开始扫描
     */
    private void startScan() {
        String ip = mTidIp.getText().toString();
        String startPort = mTieStartPort.getText().toString();
        String endPort = mTieEndPort.getText().toString();
        String startIp = mTieStartIp.getText().toString();
        String endIp = mTieEndIp.getText().toString();
        PortInfoActivity.startActivity(module, this, ip, startPort, endPort);
    }

    @OnClick(R.id.main_btn_scanner)
    public void onViewClicked() {
        startScan();
    }
}
