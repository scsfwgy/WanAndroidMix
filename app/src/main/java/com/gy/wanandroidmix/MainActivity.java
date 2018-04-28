package com.gy.wanandroidmix;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.gy.wanandroidmix.ui.base.MyBaseActivity;
import com.gy.wanandroidmix.ui.fragment.NavigationFragment;
import com.gy.wanandroidmix.ui.fragment.KnowledgeFragment;
import com.gy.wanandroidmix.ui.fragment.HomeFragment;
import com.gy.wanandroidmix.ui.fragment.MineFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * ============================================================
 * 作 者 :    wgyscsf@163.com
 * 更新时间 ：2018/04/22 11:50
 * 描 述 ：
 * ============================================================
 */
public class MainActivity extends MyBaseActivity {


    @BindView(R.id.pmtb_tv_title)
    TextView mPmtbTvTitle;
    @BindView(R.id.pmtb_rl_container)
    RelativeLayout mPmtbRlContainer;
    @BindView(R.id.am_fl_content)
    FrameLayout mAmFlContent;
    @BindView(R.id.pmbb_ib_home)
    ImageButton mPmbbIbHome;
    @BindView(R.id.pmbb_tv_home)
    TextView mPmbbTvHome;
    @BindView(R.id.pmbb_ll_home)
    LinearLayout mPmbbLlHome;
    @BindView(R.id.pmbb_ib_knowledge)
    ImageButton mPmbbIbKnowledge;
    @BindView(R.id.pmbb_tv_knowledge)
    TextView mPmbbTvKnowledge;
    @BindView(R.id.pmbb_ll_knowledge)
    LinearLayout mPmbbLlKnowledge;
    @BindView(R.id.pmbb_ib_navigation)
    ImageButton mPmbbIbNavigation;
    @BindView(R.id.pmbb_tv_navigation)
    TextView mPmbbTvNavigation;
    @BindView(R.id.pmbb_ll_navigation)
    LinearLayout mPmbbLlNavigation;
    @BindView(R.id.pmbb_ib_mine)
    ImageButton mPmbbIbMine;
    @BindView(R.id.pmbb_tv_mine)
    TextView mPmbbTvMine;
    @BindView(R.id.pmbb_ll_mine)
    LinearLayout mPmbbLlMine;

    //帧布局管理
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private int mCurrentIndex = -1;

    //首页的帧布局
    Fragment mHomeFragment;
    Fragment mKnowledgeFragment;
    Fragment mNavigationFragment;
    Fragment mMineFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initListener();
    }

    private void initView() {
        fragmentManager = getSupportFragmentManager();
        setTabSelection(0);
    }

    private void initData() {

    }

    private void initListener() {

    }

    public void setTabSelection(int index) {
        if (mCurrentIndex == index) {
            return;
        }

        // 开启一个Fragment事务
        transaction = fragmentManager.beginTransaction();
        mCurrentIndex = index;
        resetBtn();
        hideFragments();
        mPmtbRlContainer.setVisibility(View.VISIBLE);
        switch (index) {
            case 0:
                mPmtbRlContainer.setVisibility(View.GONE);
                gotoHomePage();
                break;
            case 1:
                gotoKnowledgePage();
                break;
            case 2:
                gotoNavigationPage();
                break;
            case 3:
                gotoMinePage();
                break;
            default:
                break;
        }

        transaction.commit();
    }

    /**
     * 行情
     */
    private void gotoHomePage() {
        mPmbbIbHome.setImageResource(
                R.drawable.market_press);

        if (mHomeFragment == null) {
            mHomeFragment = new HomeFragment();
            transaction.add(R.id.am_fl_content, mHomeFragment);
        } else {
            transaction.show(mHomeFragment);
        }
        mPmbbTvHome.setTextColor(getResources().getColor(R.color.color_global_text_first));
    }


    /**
     * 持仓
     */
    private void gotoKnowledgePage() {
        mPmbbIbKnowledge.setImageResource(
                R.drawable.hold_press);

        if (mKnowledgeFragment == null) {
            mKnowledgeFragment = new KnowledgeFragment();
            transaction.add(R.id.am_fl_content, mKnowledgeFragment);
        } else {
            transaction.show(mKnowledgeFragment);
        }
        mPmbbTvKnowledge.setTextColor(getResources().getColor(R.color.color_global_text_first));
    }

    /**
     * 动态
     */
    private void gotoNavigationPage() {
        mPmbbIbNavigation.setImageResource(
                R.drawable.dynamic_press);

        if (mNavigationFragment == null) {
            mNavigationFragment = new NavigationFragment();
            transaction.add(R.id.am_fl_content, mNavigationFragment);
        } else {
            transaction.show(mNavigationFragment);
        }
        mPmbbTvNavigation.setTextColor(getResources().getColor(R.color.color_global_text_first));
    }

    /**
     * 我的
     */
    private void gotoMinePage() {
        mPmbbIbMine.setImageResource(
                R.drawable.min_press);

        if (mMineFragment == null) {
            mMineFragment = new MineFragment();
            transaction.add(R.id.am_fl_content, mMineFragment);
        } else {
            transaction.show(mMineFragment);
        }
        mPmbbTvMine.setTextColor(getResources().getColor(R.color.color_global_text_first));
    }


    private void hideFragments() {
        if (mHomeFragment != null) {
            transaction.hide(mHomeFragment);
        }
        if (mKnowledgeFragment != null) {
            transaction.hide(mKnowledgeFragment);
        }
        if (mNavigationFragment != null) {
            transaction.hide(mNavigationFragment);
        }

        if (mMineFragment != null) {
            transaction.hide(mMineFragment);
        }
    }

    /**
     * 清除掉所有的选中状态。
     */
    private void resetBtn() {
        mPmbbIbHome.setImageResource(
                R.drawable.market_nopress);
        mPmbbIbKnowledge.setImageResource(
                R.drawable.hold_nopress);
        mPmbbIbNavigation.setImageResource(
                R.drawable.dynamic_nopress);
        mPmbbIbMine.setImageResource(
                R.drawable.min_nopress);

        mPmbbTvHome.setTextColor(getResources().getColor(R.color.color_global_text_second));
        mPmbbTvKnowledge.setTextColor(getResources().getColor(R.color.color_global_text_second));
        mPmbbTvNavigation.setTextColor(getResources().getColor(R.color.color_global_text_second));
        mPmbbTvMine.setTextColor(getResources().getColor(R.color.color_global_text_second));
    }

    @OnClick({R.id.pmbb_ll_home, R.id.pmbb_ll_knowledge, R.id.pmbb_ll_navigation, R.id.pmbb_ll_mine})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.pmbb_ll_home:
                setTabSelection(0);
                break;
            case R.id.pmbb_ll_knowledge:
                setTabSelection(1);
                break;
            case R.id.pmbb_ll_navigation:
                setTabSelection(2);
                break;
            case R.id.pmbb_ll_mine:
                setTabSelection(3);
                break;
        }
    }

    /**
     * 首页不允许侧滑
     *
     * @return
     */
    @Override
    protected boolean isSwipeBackHere() {
        return false;
    }
}
