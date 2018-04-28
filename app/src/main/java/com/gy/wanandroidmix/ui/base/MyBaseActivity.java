package com.gy.wanandroidmix.ui.base;


import com.gy.wanandroidmix.R;

import wgyscsf.quicklib.base.BaseActivity;

/**
 * ============================================================
 * 作 者 :    wgyscsf@163.com
 * 更新时间 ：2018/04/22 12:53
 * 描 述 ：使用时继承这个类，不要继承BaseActivity。
 * 这个时候这里什么都不用做，直接继承使用就好。后期添加一些共性的业务在这里加即可。
 * ============================================================
 */
public class MyBaseActivity extends BaseActivity {
    //这里添加后期变态的业务


    //设置沉浸式的颜色
    @Override
    protected void setMImersive(int colorId) {
        super.setMImersive(R.color.color_global_navbar);
    }
}
