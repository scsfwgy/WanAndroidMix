package com.gy.wanandroidmix;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.blankj.utilcode.util.Utils;

import wgyscsf.quicklib.datautils.ExtendAppUtils;
import wgyscsf.quicklib.global.bugly.BuglyConfig;

/**
 * ============================================================
 * 作 者 :    wgyscsf@163.com
 * 更新时间 ：2018/04/22 12:22
 * 描 述 ：
 * ============================================================
 */
public class App extends MultiDexApplication {
    public static final String TAG = App.class.getSimpleName();
    private App mApp;

    public App getApp() {
        return mApp;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        // 将MultiDex注入到项目中
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化一些常用参数
        initCommonData();

        //初始化一些业务具体的参数
        initBusinessData();

        //初始化第三方参数
        initOtherData();
    }

    private void initCommonData() {
        initAppContext();
        initAndroidUtils();
        initEventBus();
    }

    private void initAppContext() {
        mApp = this;
    }

    private void initAndroidUtils() {
        // init it in the function of onCreate in ur Application
        Utils.init(getApp());
    }


    private void initBusinessData() {

    }

    private void initOtherData() {
        initBugly();
        initUmeng();
    }

    private void initUmeng() {

    }

    //put this to app modal
    private void initBugly() {
        BuglyConfig buglyConfig = new BuglyConfig(getApp());
        String umengChannel = ExtendAppUtils.getMetaDataStringApplication("UMENG_CHANNEL", "unknown");
        buglyConfig.initBugly(umengChannel, BuildConfig.BUGLY_APPID);
    }

    private void initEventBus() {
        //EventBus.builder().addIndex(new MyEventBusIndex()).installDefaultEventBus();
    }
}
