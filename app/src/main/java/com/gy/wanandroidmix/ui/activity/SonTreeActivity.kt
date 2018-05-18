package com.gy.wanandroidmix.ui.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import com.blankj.utilcode.util.ToastUtils

import com.gy.wanandroidmix.R
import com.gy.wanandroidmix.data.model.ApiFatherTree
import com.gy.wanandroidmix.ui.adapter.SonTreeAdapter
import com.gy.wanandroidmix.ui.base.MyBaseActivity
import com.gy.wanandroidmix.ui.fragment.SonTreeFragment
import kotlinx.android.synthetic.main.activity_son_tree.*

const val APIFATHERTREE: String = "APIFATHERTREE"

class SonTreeActivity : MyBaseActivity() {

    var apiFatherTree: ApiFatherTree? = null
    lateinit var mTitleList: MutableList<String>
    lateinit var mFragmentList: MutableList<Fragment>

    override fun getBundleExtras(extras: Bundle?) {
        super.getBundleExtras(extras)
        apiFatherTree = extras?.get(APIFATHERTREE) as ApiFatherTree?
        if (apiFatherTree == null) {
            ToastUtils.showLong("数据异常")
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_son_tree)
        initView()
        initData()
        initAdapter()
        initListener()
    }

    private fun initView() {
        ast_tl_tab.tabMode = TabLayout.MODE_SCROLLABLE;
    }

    private fun initData() {
        mTitleList = ArrayList()
        mFragmentList = ArrayList()


        val children = apiFatherTree?.children
        children?.forEach {
            mTitleList.add(it.name)
            mFragmentList.add(SonTreeFragment.newInstance(it.id))
        }
    }

    private fun initAdapter() {
        ast_vp_viewpager.adapter = SonTreeAdapter(supportFragmentManager, mFragmentList, mTitleList)
        ast_tl_tab.setupWithViewPager(ast_vp_viewpager)
    }

    private fun initListener() {

    }
}
