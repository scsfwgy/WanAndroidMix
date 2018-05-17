package com.gy.wanandroidmix.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.chad.library.adapter.base.BaseViewHolder
import com.gy.wanandroidmix.R
import com.gy.wanandroidmix.data.api.ObserverCallBack
import com.gy.wanandroidmix.data.api.WanAndroidApi
import com.gy.wanandroidmix.data.model.ApiData
import com.gy.wanandroidmix.data.model.ApiFatherTree
import com.gy.wanandroidmix.ui.base.MyBaseFragment
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_navigation.*
import wgyscsf.quicklib.uiutils.MBaseQuickAdapter


/**
 * ============================================================
 * 作 者 :    wgyscsf@163.com
 * 更新时间 ：2018/04/22 17:48
 * 描 述 ：
 * ============================================================
 */
class NavigationFragment : MyBaseFragment() {

    lateinit var mList: MutableList<ApiFatherTree>
    lateinit var mQuickAdapter: MBaseQuickAdapter<ApiFatherTree, BaseViewHolder>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_navigation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
        initAdapter()
        initListener()
        loadData()
    }

    private fun initView() {

    }

    private fun initData() {
        mList = ArrayList()
    }

    private fun initAdapter() {
        mQuickAdapter = object : MBaseQuickAdapter<ApiFatherTree, BaseViewHolder>(R.layout.item_fragment_navigation, mList, mContext) {
            override fun convert(helper: BaseViewHolder, item: ApiFatherTree) {
                val title: TextView = helper.getView(R.id.ifn_tv_title)
                val content: TextView = helper.getView(R.id.ifn_tv_content)
                title.text = item.name
                var txt = ""
                item.children.forEach { txt += it.name + "  " }
                content.text = txt
            }
        }
        fn_rv_listview.layoutManager = LinearLayoutManager(mContext)
        fn_rv_listview.adapter = mQuickAdapter
    }

    private fun initListener() {

    }

    private fun loadData() {
        WanAndroidApi.getTree(object : ObserverCallBack<ApiData<List<ApiFatherTree>>>(mBaseFragment) {
            override fun onSuccess(t: ApiData<List<ApiFatherTree>>, disposable: Disposable?) {
                val data = t.data
                mList.addAll(data)
                mQuickAdapter.notifyDataSetChanged()
            }

        })
    }
}
