package com.example.Test;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;
//服务

/**
 * Created by Administrator on 2016/6/15.
 */
public class MyService extends Service {
    //创建内部类的实例
    LocalBind mLocalBind = new LocalBind();
    //绑定服务
    @Override
    public IBinder onBind(Intent intent) {
        return mLocalBind;
    }

    //首先创建一个内部类
    public class LocalBind extends Binder {
        public void method() {
            Toast.makeText(getApplication(), "localBind方法被调用了", Toast.LENGTH_SHORT).show();
        }
    }

    //创建服务
    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "开始创建服务", Toast.LENGTH_SHORT).show();
    }

    //开始服务
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "开启服务", Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent, flags, startId);
    }

    //销毁
    @Override
    public void onDestroy() {
        Toast.makeText(this, "停止服务", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }
}
