package wgyscsf.quicklib.global.app;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.blankj.utilcode.util.Utils;
import com.umeng.analytics.MobclickAgent;

import wgyscsf.quicklib.BuildConfig;

/**
 * ============================================================
 * 作 者 :    wgyscsf@163.com
 * 创建日期 ：2018/02/12 16:47
 * 描 述 ：todo:copy blow code to your app`s Application
 * ============================================================
 **/
public class App extends Application {
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
        MobclickAgent.setScenarioType(getApp(), MobclickAgent.EScenarioType.E_UM_NORMAL);
        MobclickAgent.setCatchUncaughtExceptions(false);
        MobclickAgent.setDebugMode(BuildConfig.DEBUG);//是否是测试环境
    }

    //put this to app modal
    private void initBugly() {
//        BuglyConfig buglyConfig = new BuglyConfig(getApp());
//        String umengChannel = ExtendAppUtils.getMetaDataStringApplication("UMENG_CHANNEL", "unknown");
//        buglyConfig.initBugly(umengChannel);
    }

    private void initEventBus() {
        //EventBus.builder().addIndex(new MyEventBusIndex()).installDefaultEventBus();
    }
}
