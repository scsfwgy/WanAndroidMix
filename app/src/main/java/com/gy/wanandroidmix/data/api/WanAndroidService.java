package com.gy.wanandroidmix.data.api;


import com.gy.wanandroidmix.data.model.ApiArticle;
import com.gy.wanandroidmix.data.model.ApiBanner;
import com.gy.wanandroidmix.data.model.ApiData;
import com.gy.wanandroidmix.data.model.ApiPager;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * ============================================================
 * 作 者 :    wgyscsf@163.com
 * 更新时间 ：2018/04/28 13:34
 * 描 述 ：玩安卓相关的api
 * ============================================================
 */
public interface WanAndroidService {
    @GET("banner/json")
    Observable<ApiData<List<ApiBanner>>> banner();

    @GET("article/list/{page}/json")
    Observable<ApiData<ApiPager<ApiArticle>>> article(@Path("page") int page);
}
