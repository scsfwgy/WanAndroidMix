package com.gy.wanandroidmix.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.gy.wanandroidmix.R;
import com.gy.wanandroidmix.data.api.kview.ForexTab;
import com.gy.wanandroidmix.ui.adapter.KViewAdapter;
import com.gy.wanandroidmix.ui.base.MyBaseActivity;
import com.gy.wanandroidmix.ui.fragment.KViewFragment;
import com.gy.wanandroidmix.view.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class DealActivity extends MyBaseActivity {

    @BindView(R.id.pdtb_iv_back)
    ImageView mPdtbIvBack;
    @BindView(R.id.pdtb_tv_symbol)
    TextView mPdtbTvSymbol;
    @BindView(R.id.pdtb_tv_status)
    TextView mPdtbTvStatus;
    @BindView(R.id.pdtb_ll_rule)
    LinearLayout mPdtbLlRule;
    @BindView(R.id.pdtb_rl_container)
    RelativeLayout mPdtbRlContainer;
    @BindView(R.id.ad_tv_buytitle)
    TextView mAdTvBuytitle;
    @BindView(R.id.ad_tv_buyprice)
    TextView mAdTvBuyprice;
    @BindView(R.id.ad_ll_buyContainer)
    LinearLayout mAdLlBuyContainer;
    @BindView(R.id.ad_tv_selltitle)
    TextView mAdTvSelltitle;
    @BindView(R.id.ad_tv_sellprice)
    TextView mAdTvSellprice;
    @BindView(R.id.ad_ll_sellContainer)
    LinearLayout mAdLlSellContainer;
    @BindView(R.id.ad_tl_tablayout)
    TabLayout mAdTlTablayout;
    @BindView(R.id.ad_vp_viewpager)
    NoScrollViewPager mAdVpViewpager;


    private List<ForexTab> mForexTabList;
    private List<Fragment> mKViewFragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal);
        initView();
        initData();
        initAdapter();
        initListener();
    }

    private void initAdapter() {
        mAdVpViewpager.setAdapter(new KViewAdapter(getSupportFragmentManager(), mKViewFragmentList, mForexTabList));
        mAdTlTablayout.setupWithViewPager(mAdVpViewpager);
    }

    private void initView() {
        mForexTabList = new ArrayList<>();
        mForexTabList.add(new ForexTab(ForexTab.ForexType._timeShring, "分时"));
        mForexTabList.add(new ForexTab(ForexTab.ForexType._1m, "1分"));
        mForexTabList.add(new ForexTab(ForexTab.ForexType._5m, "5分"));
        mForexTabList.add(new ForexTab(ForexTab.ForexType._15m, "15分"));
        mForexTabList.add(new ForexTab(ForexTab.ForexType._30m, "30分"));
        mForexTabList.add(new ForexTab(ForexTab.ForexType._1h, "1时"));
        mForexTabList.add(new ForexTab(ForexTab.ForexType._4h, "4时"));
        mForexTabList.add(new ForexTab(ForexTab.ForexType._1d, "日K"));
        mForexTabList.add(new ForexTab(ForexTab.ForexType._1w, "周K"));
        mForexTabList.add(new ForexTab(ForexTab.ForexType._1mon, "月"));

        mAdTlTablayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        mKViewFragmentList = new ArrayList<>();
        for (ForexTab forexTab : mForexTabList) {
            mKViewFragmentList.add(KViewFragment.newInstance(forexTab));
        }
    }

    private void initData() {

    }

    private void initListener() {

    }

    @OnClick({R.id.pdtb_iv_back, R.id.pdtb_ll_rule, R.id.ad_ll_buyContainer, R.id.ad_ll_sellContainer})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.pdtb_iv_back:
                finish();
                break;
            case R.id.pdtb_ll_rule:
                break;
            case R.id.ad_ll_buyContainer:
                break;
            case R.id.ad_ll_sellContainer:
                break;
        }
    }
}
