package com.example.Test;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mStartService;
    private Button mStopService;
    private Button mBindService;
    private Button mUnBindService;
    private MyService.LocalBind mLocalBind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initListener();
    }

    //服务绑定连接
    ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mLocalBind = (MyService.LocalBind) iBinder;
            mLocalBind.method();//调用绑定服务中的方法
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    //按钮监听
    private void initListener() {
        mStartService.setOnClickListener(this);
        mStopService.setOnClickListener(this);
        mBindService.setOnClickListener(this);
        mUnBindService.setOnClickListener(this);
    }

    private void initView() {
        setContentView(R.layout.activity_main);
        mStartService = (Button) findViewById(R.id.Start_Service);
        mStopService = (Button) findViewById(R.id.Stop_Service);
        mBindService = (Button) findViewById(R.id.Bind_Service);//绑定服务
        mUnBindService = (Button) findViewById(R.id.unBind_Service);//解绑服务
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Start_Service:
                Intent Start_intent = new Intent(this, MyService.class);
                startService(Start_intent);
                break;
            case R.id.Bind_Service:
                Intent Bindintent = new Intent(this, MyService.class);
                bindService(Bindintent, mConnection, BIND_AUTO_CREATE);
                break;
            case R.id.unBind_Service:
                Intent unBindIntent = new Intent(this, MyService.class);
                unbindService(mConnection);
            case R.id.Stop_Service:
                Intent Stop_intent = new Intent(this, MyService.class);
                stopService(Stop_intent);
                break;
        }
    }
}
