package com.gy.wanandroidmix.ui.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * ============================================================
 * 作 者 :    wgyscsf@163.com
 * 更新时间 ：2018/05/18 15:04
 * 描 述 ：
 * ============================================================
 */
class SonTreeAdapter(fm: FragmentManager,
                     private val fragmentList: List<Fragment>,
                     private val titles: List<String>)
    : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return fragmentList.get(index = position)
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }
}