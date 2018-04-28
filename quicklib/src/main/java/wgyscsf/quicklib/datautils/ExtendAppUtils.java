package wgyscsf.quicklib.datautils;

import android.app.Activity;
import android.content.ComponentName;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import com.blankj.utilcode.util.Utils;

/**
 * ============================================================
 * 作 者 :    wgyscsf@163.com
 * 创建日期 ：2017/12/29 18:02
 * 描 述 ：
 * ============================================================
 **/
public class ExtendAppUtils {
    /**
     * 根据key从Application中返回的Bundle中获取value
     *
     * @param key
     * @param defValue
     * @return
     */
    public static String getMetaDataStringApplication(String key, String defValue) {
        Bundle bundle = getAppMetaDataBundle(Utils.getApp().getPackageManager(), Utils.getApp().getPackageName());
        if (bundle != null && bundle.containsKey(key)) {
            return bundle.getString(key);
        }
        return defValue;
    }

    /**
     * 获取Application中的meta-data.
     *
     * @param packageManager
     * @param packageName
     * @return
     */
    public static Bundle getAppMetaDataBundle(PackageManager packageManager,
                                        String packageName) {
        Bundle bundle = null;
        try {
            ApplicationInfo ai = packageManager.getApplicationInfo(packageName,
                    PackageManager.GET_META_DATA);
            bundle = ai.metaData;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("getMetaDataBundle", e.getMessage(), e);
        }
        return bundle;
    }

    /**
     * 根据key从Activity中返回的Bundle中获取value
     *
     * @param key
     * @param defValue
     * @return
     */
    public static String getMetaDataStringFromActivity(Activity activity, String key, String defValue) {
        Bundle bundle = getActivityMetaDataBundle(Utils.getApp().getPackageManager(), activity.getComponentName());
        if (bundle != null && bundle.containsKey(key)) {
            return bundle.getString(key);
        }
        return defValue;
    }

    /**
     * 获取Activity中的meta-data.
     *
     * @param packageManager
     * @param component
     * @return
     */
    public static Bundle getActivityMetaDataBundle(PackageManager packageManager, ComponentName component) {
        Bundle bundle = null;
        try {
            ActivityInfo ai = packageManager.getActivityInfo(component,
                    PackageManager.GET_META_DATA);
            bundle = ai.metaData;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("getMetaDataBundle", e.getMessage(), e);
        }

        return bundle;
    }
}
