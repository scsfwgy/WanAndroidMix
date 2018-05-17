package com.gy.wanandroidmix.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.widget.ImageView
import android.widget.TextView
import com.blankj.utilcode.util.EmptyUtils
import com.blankj.utilcode.util.KeyboardUtils
import com.blankj.utilcode.util.TimeUtils
import com.blankj.utilcode.util.ToastUtils
import com.chad.library.adapter.base.BaseViewHolder
import com.gy.wanandroidmix.R
import com.gy.wanandroidmix.data.api.ObserverCallBack
import com.gy.wanandroidmix.data.api.WanAndroidApi
import com.gy.wanandroidmix.data.model.ApiArticle
import com.gy.wanandroidmix.data.model.ApiData
import com.gy.wanandroidmix.data.model.ApiPager
import com.gy.wanandroidmix.ui.base.MyBaseActivity
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.parts_search_title_bar.*
import wgyscsf.quicklib.uiutils.MBaseQuickAdapter
import wgyscsf.quicklib.uiutils.glide.GlideUtil
import java.util.*

class SearchActivity : MyBaseActivity() {


    private lateinit var mBaseQuickAdapter: MBaseQuickAdapter<ApiArticle, BaseViewHolder>
    private lateinit var mList: MutableList<ApiArticle>
    private var mPager: Int = 0
    private var mKeyWord: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        initView()
        initData()
        initAdapter()
        initListener()
    }

    private fun initView() {

    }

    private fun initData() {
        mList = ArrayList()
    }

    private fun initListener() {
        as_srl_reflsh.setOnRefreshListener {
            mPager = 0
            if (mKeyWord.isEmpty()) {
                as_srl_reflsh.isRefreshing = false
            }
            loadData()
        }
        pmtb_acev_input.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val trim = pmtb_acev_input.text.toString().trim()
                if (EmptyUtils.isNotEmpty(trim)) {
                    mKeyWord = trim
                    loadData()
                } else {
                    ToastUtils.setBgColor(resources.getColor(R.color.color_global_red))
                    ToastUtils.setMsgColor(resources.getColor(R.color.color_global_item_bg))
                    ToastUtils.showLong("请输入关键字后搜索")
                }

                KeyboardUtils.hideSoftInput(mActivity)
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
        mBaseQuickAdapter.setOnLoadMoreListener({
            mPager++
            loadData()
        }, as_rv_listview)

        pdtb_iv_back.setOnClickListener { finish() }
    }

    private fun initAdapter() {
        mBaseQuickAdapter = object : MBaseQuickAdapter<ApiArticle, BaseViewHolder>(R.layout.item_activity_search,
                mList, mContext) {
            @SuppressLint("SetTextI18n")
            override fun convert(helper: BaseViewHolder, item: ApiArticle?) {
                val mIfhTvTime: TextView = helper.getView(R.id.ifh_tv_time)
                val mIfhTvCategory: TextView = helper.getView(R.id.ifh_tv_category)
                val mIfhTvAuthor: TextView = helper.getView(R.id.ifh_tv_author)
                val mIfhTvTitle: TextView = helper.getView(R.id.ifh_tv_title)
                val mIfhIvAuthor: ImageView = helper.getView(R.id.ifh_iv_author)
                val mIfhIvLike: ImageView = helper.getView(R.id.ifh_iv_like)!!

                if (item?.collect!!) {
                    mIfhIvLike.setImageResource(R.drawable.icon_navigation_selected)
                } else {
                    mIfhIvLike.setImageResource(R.drawable.icon_navigation_not_selected)
                }
                GlideUtil.getInstance().loadCircleImage(mContext, mIfhIvAuthor, item.envelopePic)
                mIfhTvTitle.text = item.title
                mIfhTvAuthor.text = "作者：$item.author"
                mIfhTvCategory.text = "分类：$item.chapterName"
                mIfhTvTime.text = "时间：${TimeUtils.getFriendlyTimeSpanByNow(item.publishTime)}"
            }
        }
        as_rv_listview.layoutManager = LinearLayoutManager(mContext)
        as_rv_listview.adapter = mBaseQuickAdapter
        mBaseQuickAdapter.showEmptyText("暂无数据")
    }

    private fun loadData() {
        if (mKeyWord.isEmpty()) return
        var map = HashMap<String, String>()
        map.put("k", mKeyWord)
        WanAndroidApi.getQuery(mPager, map as Map<String, Any>?, object : ObserverCallBack<ApiData<ApiPager<ApiArticle>>>(mActivity) {
            override fun onSuccess(t: ApiData<ApiPager<ApiArticle>>?, disposable: Disposable?) {
                Log.d(TAG, "getQuery: " + t.toString());
                val data = t?.data
                mPager = data?.curPage?.minus(1)!!
                mBaseQuickAdapter.loadMoreComplete()
                if (mPager == 0) {
                    as_srl_reflsh.isRefreshing = false
                    mList.clear()
                }
                if (data.over) {
                    mBaseQuickAdapter.loadMoreEnd()
                }


                val datas = data.datas
                mList.addAll(datas)


                if (mList.isEmpty()) {
                    mBaseQuickAdapter.showNoDataText()
                    ToastUtils.showLong("暂未搜到数据，请尝试其它关键字")
                }
                mBaseQuickAdapter.notifyDataSetChanged()
            }

        })

    }
}
