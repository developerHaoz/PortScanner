package com.example.developerhaoz.portscanner.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.developerhaoz.portscanner.R;
import com.orhanobut.logger.Logger;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by developerHaoz on 2017/6/24.
 */

public class TestActivity extends AppCompatActivity {

    @Bind(R.id.test_btn_start_service)
    Button mBtnStartService;
    @Bind(R.id.test_btn_stop_service)
    Button mBtnStopService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.test_btn_start_service, R.id.test_btn_stop_service})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.test_btn_start_service:
                Logger.d("hello world");
                Intent intent = new Intent(this, MyService.class);
                startService(intent);
                break;
            case R.id.test_btn_stop_service:
                Intent intent1 = new Intent(this, MyService.class);
                stopService(intent1);
                break;
        }
    }
}
