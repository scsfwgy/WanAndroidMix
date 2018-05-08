package com.gy.wanandroidmix.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gy.wanandroidmix.R;
import com.gy.wanandroidmix.ui.activity.PieChartActivity;
import com.gy.wanandroidmix.ui.activity.RadarChart2Activity;
import com.gy.wanandroidmix.ui.activity.RadarChartActivity;
import com.gy.wanandroidmix.ui.base.MyBaseFragment;

import butterknife.BindView;


/**
 * ============================================================
 * 作 者 :    wgyscsf@163.com
 * 更新时间 ：2018/04/22 17:48
 * 描 述 ：
 * ============================================================
 */
public class KnowledgeFragment extends MyBaseFragment {
    @BindView(R.id.fk_tv_piechart)
    TextView mFkTvPiechart;
    @BindView(R.id.fk_tv_radarchart)
    TextView mFkTvRadarchart;

    private void initView() {

    }

    private void initData() {

    }

    private void initAdapter() {

    }

    private void initListener() {
        mFkTvPiechart.setOnClickListener(view -> go(PieChartActivity.class));
        mFkTvRadarchart.setOnClickListener(view -> go(RadarChart2Activity.class));
    }

    private void loadData() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_knowledge, container, false);
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
}
