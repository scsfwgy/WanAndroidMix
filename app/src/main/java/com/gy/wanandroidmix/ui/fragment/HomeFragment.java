package com.gy.wanandroidmix.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blankj.utilcode.util.EmptyUtils;
import com.blankj.utilcode.util.StringUtils;
import com.chad.library.adapter.base.BaseViewHolder;
import com.gy.wanandroidmix.R;
import com.gy.wanandroidmix.data.api.ObserverCallBack;
import com.gy.wanandroidmix.data.api.WanAndroidApi;
import com.gy.wanandroidmix.data.model.ApiArticle;
import com.gy.wanandroidmix.data.model.ApiBanner;
import com.gy.wanandroidmix.data.model.ApiData;
import com.gy.wanandroidmix.data.model.ApiPager;
import com.gy.wanandroidmix.ui.activity.DealActivity;
import com.gy.wanandroidmix.ui.base.MyBaseFragment;
import com.gy.wanandroidmix.utils.ExtendIntentUtils;
import com.gy.wanandroidmix.view.BannerImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;
import wgyscsf.quicklib.uiutils.MBaseQuickAdapter;


/**
 * ============================================================
 * 作 者 :    wgyscsf@163.com
 * 更新时间 ：2018/04/22 17:48
 * 描 述 ：
 * ============================================================
 */
public class HomeFragment extends MyBaseFragment {
    @BindView(R.id.hf_rv_listview)
    RecyclerView mListview;
    @BindView(R.id.fm_banner_banner)
    Banner mBanner;

    //当前页码
    int mPager = 0;

    //MBaseQuickAdapter<ApiArticle, BaseViewHolder> mQuickAdapter;

    List<ApiBanner> mApiBannerList;
    List<ApiArticle> mApiArticleList;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_home, container, false);
        return contentView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
        initAdapter();
        initListener();
        loadData();
    }

    //如果你需要考虑更好的体验，可以这么操作
    @Override
    public void onStart() {
        super.onStart();
        //开始轮播
        mBanner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        //结束轮播
        mBanner.stopAutoPlay();
    }

    private void initView() {
        //设置banner样式
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
        mBanner.setImageLoader(new BannerImageLoader());
        //设置banner动画效果
        mBanner.setBannerAnimation(Transformer.Default);
        //设置自动轮播，默认为true
        mBanner.isAutoPlay(true);
        //设置轮播时间
        mBanner.setDelayTime(1500);
        //设置指示器位置（当banner模式中有指示器时）
        mBanner.setIndicatorGravity(BannerConfig.CENTER);
    }

    private void initData() {
        mApiBannerList = new ArrayList<>();
        mApiArticleList = new ArrayList<>();
    }

    private void initAdapter() {
//        mQuickAdapter=new MBaseQuickAdapter<ApiArticle, BaseViewHolder>() {
//            @Override
//            protected void convert(BaseViewHolder helper, ApiArticle item) {
//
//            }
//        };
    }

    private void initListener() {
        mBanner.setOnBannerListener(postion -> {
            ApiBanner apiBanner = mApiBannerList.get(postion);
            try {
                ExtendIntentUtils.openExplorer(mContext, apiBanner.url);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
//        mQuickAdapter.setOnLoadMoreListener(() -> {
//            mPager++;
//            loadArticle();
//        }, mListview);
    }

    private void loadData() {
        WanAndroidApi.getBanner(new ObserverCallBack<ApiData<List<ApiBanner>>>(mBaseFragment) {
            @Override
            public void onSuccess(ApiData<List<ApiBanner>> apiBannerApiData, Disposable disposable) {
                List<ApiBanner> data = apiBannerApiData.data;
                Log.d(TAG, "onSuccess: " + data.toString());
                if (EmptyUtils.isEmpty(data)) {
                    Log.e(TAG, "onSuccess: 数据为空！！！");
                    return;
                }
                mApiBannerList.addAll(data);
                processBanner();
            }
        });

        loadArticle();
    }

    private void loadArticle() {
        WanAndroidApi.getArticle(mPager, new ObserverCallBack<ApiData<ApiPager<ApiArticle>>>(mBaseFragment) {
            @Override
            public void onSuccess(ApiData<ApiPager<ApiArticle>> apiPagerApiData, Disposable disposable) {
                ApiPager<ApiArticle> data = apiPagerApiData.data;
                if (data == null) {
                    Log.e(TAG, "onSuccess: NPE");
                    return;
                }
                //mQuickAdapter.loadMoreComplete();
                mPager = data.curPage - 1;
                if (data.over) {
                   // mQuickAdapter.loadMoreEnd();
                }
                List<ApiArticle> datas = data.datas;
                if (EmptyUtils.isEmpty(datas)) {
                    Log.d(TAG, "onSuccess: 集合为空");
                } else {
                    Log.d(TAG, "onSuccess: " + datas.toString());
                    mApiArticleList.addAll(datas);
                }
            }
        });
    }

    private void processBanner() {
        List<String> urlList = new ArrayList<>();
        List<String> titleList = new ArrayList<>();
        for (ApiBanner datum : mApiBannerList) {
            urlList.add(datum.imagePath);
            titleList.add(datum.title);
        }
        mBanner.setImages(urlList);
        mBanner.setBannerTitles(titleList);
        mBanner.start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
