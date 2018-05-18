package com.gy.wanandroidmix.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.gy.wanandroidmix.R;
import com.gy.wanandroidmix.ui.activity.ProflieActivity;
import com.gy.wanandroidmix.ui.activity.SonTreeActivity;
import com.gy.wanandroidmix.ui.base.MyBaseFragment;

import butterknife.OnClick;

/**
 * ============================================================
 * 作 者 :    wgyscsf@163.com
 * 更新时间 ：2018/04/22 17:48
 * 描 述 ：
 * ============================================================
 */
public class MineFragment extends MyBaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_mine, container, false);
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

    private void initView() {

    }

    private void initData() {

    }

    private void initAdapter() {

    }

    private void initListener() {

    }

    private void loadData() {

    }

    @OnClick(R.id.fm_tv_goProfile)
    public void goProfile(View view) {
        go(ProflieActivity.class);
    }

}
