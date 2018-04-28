package wgyscsf.quicklib.global.bugly;

import android.content.Context;
import android.os.Environment;
import android.view.View;

import com.blankj.utilcode.BuildConfig;
import com.blankj.utilcode.util.AppUtils;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.UpgradeInfo;
import com.tencent.bugly.beta.ui.UILifecycleListener;
import com.tencent.bugly.crashreport.CrashReport;

import wgyscsf.quicklib.R;


public class BuglyConfig {
    private Context mContext;

    public BuglyConfig(Context context) {
        this.mContext = context.getApplicationContext();
    }

    /**
     * 用户 配置
     */

    public void initBugly(String UMENG_CHANNEL,String APP_ID) {
        /**** Beta高级设置*****/
        /**
         * true表示app启动自动初始化升级模块；
         * false不好自动初始化
         * 开发者如果担心sdk初始化影响app启动速度，可以设置为false
         * 在后面某个时刻手动调用
         */
        Beta.autoInit = true;

        /**
         * true表示初始化时自动检查升级
         * false表示不会自动检查升级，需要手动调用Beta.checkUpgrade()方法
         */
        Beta.autoCheckUpgrade = true;

        /**
         * 设置升级周期为60s（默认检查周期为0s），60s内SDK不重复向后天请求策略
         */
        Beta.initDelay = 10 * 1000;

        /**
         * 设置通知栏大图标，largeIconId为项目中的图片资源；
         */
        Beta.largeIconId = R.mipmap.ic_launcher_round;

        /**
         * 设置状态栏小图标，smallIconId为项目中的图片资源id;
         */
        Beta.smallIconId = R.mipmap.ic_launcher_round;


        /**
         * 设置更新弹窗默认展示的banner，defaultBannerId为项目中的图片资源Id;
         * 当后台配置的banner拉取失败时显示此banner，默认不设置则展示“loading“;
         */
        Beta.defaultBannerId = R.mipmap.ic_launcher_round;

        /**
         * 设置sd卡的Download为更新资源保存目录;
         * 后续更新资源会保存在此目录，需要在manifest中添加WRITE_EXTERNAL_STORAGE权限;
         */
        Beta.storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);

        //wifi下自动下载
        Beta.autoDownloadOnWifi = false;

        /**
         * 点击过确认的弹窗在APP下次启动自动检查更新时会再次显示;
         */
        Beta.showInterruptedStrategy = true;

        /**
         * 只允许在MainActivity上显示更新弹窗，其他activity上不显示弹窗;
         * 不设置会默认所有activity都可以显示弹窗;
         */
        //Beta.canShowUpgradeActs.add(MainActivity.class);


        /**
         *  设置自定义升级对话框UI布局
         *  注意：因为要保持接口统一，需要用户在指定控件按照以下方式设置tag，否则会影响您的正常使用：
         *  标题：beta_title，如：android:tag="beta_title"
         *  升级信息：beta_upgrade_info  如： android:tag="beta_upgrade_info"
         *  更新属性：beta_upgrade_feature 如： android:tag="beta_upgrade_feature"
         *  取消按钮：beta_cancel_button 如：android:tag="beta_cancel_button"
         *  确定按钮：beta_confirm_button 如：android:tag="beta_confirm_button"
         *  详见layout/layer_upgrade_dialog.xmlog.xml
         */
        Beta.upgradeDialogLayoutId = R.layout.layout_upgrade;

        /**
         * 设置自定义tip弹窗UI布局
         * 注意：因为要保持接口统一，需要用户在指定控件按照以下方式设置tag，否则会影响您的正常使用：
         *  标题：beta_title，如：android:tag="beta_title"
         *  提示信息：beta_tip_message 如： android:tag="beta_tip_message"
         *  取消按钮：beta_cancel_button 如：android:tag="beta_cancel_button"
         *  确定按钮：beta_confirm_button 如：android:tag="beta_confirm_button"
         *  详见layout/tips_dialog.xml
         */
        Beta.tipsDialogLayoutId = R.layout.layout_upgrade_tips;

        /**
         *  如果想监听升级对话框的生命周期事件，可以通过设置OnUILifecycleListener接口
         *  回调参数解释：
         *  context - 当前弹窗上下文对象
         *  view - 升级对话框的根布局视图，可通过这个对象查找指定view控件
         *  upgradeInfo - 升级信息
         */
        Beta.strUpgradeDialogCancelBtn = "";
        Beta.upgradeDialogLifecycleListener = new UILifecycleListener<UpgradeInfo>() {
            @Override
            public void onCreate(Context context, View view, UpgradeInfo upgradeInfo) {
            }

            @Override
            public void onStart(Context context, View view, UpgradeInfo upgradeInfo) {

            }

            @Override
            public void onResume(Context context, View view, UpgradeInfo upgradeInfo) {
            }

            @Override
            public void onPause(Context context, View view, UpgradeInfo upgradeInfo) {
            }

            @Override
            public void onStop(Context context, View view, UpgradeInfo upgradeInfo) {

            }

            @Override
            public void onDestroy(Context context, View view, UpgradeInfo upgradeInfo) {
            }
        };

        /**
         * 已经接入Bugly用户改用上面的初始化方法,不影响原有的crash上报功能;
         * init方法会自动检测更新，不需要再手动调用Beta.checkUpdate(),如需增加自动检查时机可以使用Beta.checkUpdate(false,false);
         * 参数1： applicationContext
         * 参数2：appId
         * 参数3：是否开启debug
         */

        /**
         * 如果想自定义策略，按照如下方式设置
         */

        /***** Bugly高级设置 *****/
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(mContext);
        strategy.setDeviceID("I am device ID");
        /**
         * 设置app渠道号
         */
        strategy.setAppChannel(UMENG_CHANNEL);  //设置渠道
        strategy.setAppVersion(AppUtils.getAppVersionName());      //App的版本
        strategy.setAppPackageName(AppUtils.getAppPackageName());  //App的包名
        CrashReport.initCrashReport(mContext, APP_ID, BuildConfig.DEBUG, strategy);

        CrashReport.setUserId("I am user id");
        CrashReport.setUserSceneTag(mContext,6666);
    }
}
