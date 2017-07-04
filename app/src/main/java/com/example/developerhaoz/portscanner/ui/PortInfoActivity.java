package com.example.developerhaoz.portscanner.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.example.developerhaoz.portscanner.R;
import com.example.developerhaoz.portscanner.bean.PortInfoBean;
import com.example.developerhaoz.portscanner.common.DialogFragmentHelper;
import com.example.developerhaoz.portscanner.common.PortInfoEvent;
import com.example.developerhaoz.portscanner.common.VolleyHelper;
import com.example.developerhaoz.portscanner.utils.AddressDecoder;
import com.example.developerhaoz.portscanner.utils.GsonHelper;
import com.example.developerhaoz.portscanner.utils.PortScannerHelper;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import bolts.Continuation;
import bolts.Task;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by developerHaoz on 2017/6/14.
 */

public class PortInfoActivity extends AppCompatActivity {

    public static Context mContext;
    public static List<Integer> mPortList = new ArrayList<>();
    public static List<PortInfoBean> mPortInfoBeanList = new ArrayList<>();
    private static final String START_IP = "start_ip";
    private static final String END_IP = "end_ip";
    private static final String STARTPORT = "startPort";
    private static final String ENDPORT = "endPort";
    private static final String MODULE = "module";

    private String startIp;
    private String endIp;
    private String startPort;
    private String endPort;

    /**
     * 用来判断是扫描一个 ip，还是扫描多个 ip
     */
    private int module;

    private DialogFragment mDialogFragment;



    @Bind(R.id.port_info_ll)
    LinearLayout mPortInfoLl;
    @Bind(R.id.port_info_rv)
    RecyclerView mPortInfoRv;

    public static void startActivity(int module, Context context, String startIp, String endIp, String startPort, String endPort) {
        Intent intent = new Intent(context, PortInfoActivity.class);
        intent.putExtra(MODULE, module);
        intent.putExtra(START_IP, startIp);
        intent.putExtra(END_IP, endIp);
        intent.putExtra(STARTPORT, startPort);
        intent.putExtra(ENDPORT, endPort);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mContext = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_port_info);
        mDialogFragment = DialogFragmentHelper.showProgress(getSupportFragmentManager(), "正在扫描中...");
        ButterKnife.bind(this);
        Intent intent = getIntent();
        module = intent.getIntExtra(MODULE, 1);
        startIp = intent.getStringExtra(START_IP);
        endIp = intent.getStringExtra(END_IP);
        startPort = intent.getStringExtra(STARTPORT);
        endPort = intent.getStringExtra(ENDPORT);
        initView();
    }

    private void initView() {
        try {
            Task.call(new Callable<List<PortInfoBean>>() {
                @Override
                public List<PortInfoBean> call() throws Exception {

                    if (mPortList.size() > 0 || mPortInfoBeanList.size() > 0) {
                        mPortList = new ArrayList<>();
                        mPortInfoBeanList.clear();
                    }

                    if (startPort.length() > 0 && endPort.length() > 0) {
                        PortScannerHelper.scanOneIp(String.valueOf(startIp), Integer.valueOf(startPort), Integer.valueOf(endPort), 100, 1000);
                    }
                    for (Integer integer : mPortList) {
                        String response = VolleyHelper.sendHttpGet(PortInfoActivity.this, AddressDecoder.getAddress(String.valueOf(integer)));
                        PortInfoBean portInfoBean = GsonHelper.getPortInfoBean(response);
                        mPortInfoBeanList.add(portInfoBean);
                    }
                    return mPortInfoBeanList;
                }
            }, Task.BACKGROUND_EXECUTOR).onSuccess(new Continuation<List<PortInfoBean>, Void>() {

                @Override
                public Void then(Task<List<PortInfoBean>> task) throws Exception {

                    List<PortInfoBean> portInfoBeanList = task.getResult();
                    Logger.d(portInfoBeanList.size());
                    PortInfoAdapter adapter = new PortInfoAdapter(startIp, endIp, PortInfoActivity.this, portInfoBeanList);
                    mPortInfoRv.setLayoutManager(new LinearLayoutManager(PortInfoActivity.this));
                    mPortInfoRv.setAdapter(adapter);
                    mPortInfoLl.setVisibility(View.VISIBLE);
                    mDialogFragment.dismiss();
                    return null;
                }
            }, Task.UI_THREAD_EXECUTOR);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initPortBeanList() {
    }

    @Subscribe
    public void initPortInfoList(PortInfoEvent event) {
        String response = event.getPortInfo();
        Logger.d(response);
        PortInfoBean portInfoBean = GsonHelper.getPortInfoBean(response);
        mPortInfoBeanList.add(portInfoBean);
    }
}
