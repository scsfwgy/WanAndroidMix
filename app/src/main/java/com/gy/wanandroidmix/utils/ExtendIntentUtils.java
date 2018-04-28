package com.gy.wanandroidmix.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * ============================================================
 * 作 者 :    wgyscsf@163.com
 * 更新时间 ：2018/04/28 16:20
 * 描 述 ：
 * ============================================================
 */
public class ExtendIntentUtils {
    /**
     * 打开外置浏览器
     *
     * @param context
     * @return url
     */
    public static void openExplorer(Context context, String url) throws Exception {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri content_url = Uri.parse(url);
        intent.setData(content_url);
        context.startActivity(intent);
    }
}
