package com.gy.wanandroidmix.ui.activity

import android.os.Bundle
import com.gy.wanandroidmix.R
import com.gy.wanandroidmix.ui.base.MyBaseActivity
import com.gy.wanandroidmix.ui.fragment.ProfileFragment
import kotlinx.android.synthetic.main.parts_main_title_bar.*

/**
 * ============================================================
 * 作 者 :    wgyscsf@163.com
 * 更新时间 ：2018/04/27 11:47
 * 描 述 ：
 * ============================================================
 */
class ProflieActivity : MyBaseActivity() {

    var profileFragment: ProfileFragment? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        initView()
        loadFragment()
    }

    fun loadFragment() {
        val fragmentManager = supportFragmentManager
        val beginTransaction = fragmentManager.beginTransaction()
        if (profileFragment == null) profileFragment = ProfileFragment()
        beginTransaction.add(R.id.am_fl_content, profileFragment)
        beginTransaction.commit()
    }

    fun initView() {
        pmtb_tv_title.text = "个人中心"
    }

}