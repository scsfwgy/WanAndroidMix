package com.gy.wanandroidmix.data.api;

import com.gy.wanandroidmix.data.model.ApiArticle;
import com.gy.wanandroidmix.data.model.ApiBanner;
import com.gy.wanandroidmix.data.model.ApiData;
import com.gy.wanandroidmix.data.model.ApiPager;

import java.util.List;

import wgyscsf.quicklib.datautils.RxUtils;

/**
 * ============================================================
 * 作 者 :    wgyscsf@163.com
 * 更新时间 ：2018/04/28 14:33
 * 描 述 ：简化api的调用复杂度，当然如果你想运用rx的各种特性，
 * 直接使用RetrofitManager.getInstance().create(WanAndroidService.class)去获取Observer也是可以的，随意。
 * ============================================================
 */
public class WanAndroidApi {
    public static WanAndroidService getWanAndroidApi() {
        return RetrofitManager.getInstance().create(WanAndroidService.class);
    }

    public static void getBanner(ObserverCallBack<ApiData<List<ApiBanner>>> apiDataObserverCallBack) {
        getWanAndroidApi()
                .banner()
                .compose(RxUtils.rxApiSchedulerHelper())
                .subscribe(apiDataObserverCallBack);
    }

    public static void getArticle(int pager, ObserverCallBack<ApiData<ApiPager<ApiArticle>>> apiDataObserverCallBack) {
        getWanAndroidApi()
                .article(pager)
                .compose(RxUtils.rxApiSchedulerHelper())
                .subscribe(apiDataObserverCallBack);
    }
}
