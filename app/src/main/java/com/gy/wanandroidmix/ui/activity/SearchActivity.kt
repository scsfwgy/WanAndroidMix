package com.gy.wanandroidmix.ui.activity

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.blankj.utilcode.util.TimeUtils
import com.chad.library.adapter.base.BaseViewHolder
import com.gy.wanandroidmix.R
import com.gy.wanandroidmix.data.api.ObserverCallBack
import com.gy.wanandroidmix.data.api.WanAndroidApi
import com.gy.wanandroidmix.data.api.WanAndroidService
import com.gy.wanandroidmix.data.model.ApiArticle
import com.gy.wanandroidmix.data.model.ApiBanner
import com.gy.wanandroidmix.data.model.ApiData
import com.gy.wanandroidmix.data.model.ApiPager
import com.gy.wanandroidmix.ui.base.MyBaseActivity
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_search.*
import wgyscsf.quicklib.uiutils.MBaseQuickAdapter
import wgyscsf.quicklib.uiutils.glide.GlideUtil
import java.util.*

class SearchActivity : MyBaseActivity() {


    lateinit var mBaseQuickAdapter: MBaseQuickAdapter<ApiArticle, BaseViewHolder>
    lateinit var mList: List<ApiArticle>
    var mPager: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        initView()
        initData()
        initListener()
        initAdapter()
    }

    private fun initView() {

    }

    private fun initData() {
        mList = ArrayList()
    }

    private fun initListener() {

    }

    private fun initAdapter() {
        mBaseQuickAdapter = object : MBaseQuickAdapter<ApiArticle, BaseViewHolder>(R.layout.item_activity_search, mList, mContext) {
            override fun convert(helper: BaseViewHolder?, item: ApiArticle?) {
                val mIfhTvTime: TextView
                val mIfhTvCategory: TextView
                val mIfhTvAuthor: TextView
                val mIfhTvTitle: TextView
                val mIfhIvAuthor: ImageView
                val mIfhIvLike: ImageView

                mIfhIvLike = helper?.getView(R.id.ifh_iv_like)!!
                mIfhIvAuthor = helper.getView(R.id.ifh_iv_author)
                mIfhTvTitle = helper.getView(R.id.ifh_tv_title)
                mIfhTvAuthor = helper.getView(R.id.ifh_tv_author)
                mIfhTvCategory = helper.getView(R.id.ifh_tv_category)
                mIfhTvTime = helper.getView(R.id.ifh_tv_time)

                if (item?.collect!!) {
                    mIfhIvLike.setImageResource(R.drawable.icon_navigation_selected)
                } else {
                    mIfhIvLike.setImageResource(R.drawable.icon_navigation_not_selected)
                }
                GlideUtil.getInstance().loadCircleImage(mContext, mIfhIvAuthor, item.envelopePic)
                mIfhTvTitle.text = item.title
                mIfhTvAuthor.text = "作者：" + item.author
                mIfhTvCategory.text = "分类：" + item.chapterName
                mIfhTvTime.text = "时间：" + TimeUtils.getFriendlyTimeSpanByNow(item.publishTime)
            }

        }
    }

    private fun loadData(keyWord:Objects) {
        var map=HashMap<String,Objects>()
        map.put("k",keyWord)
        WanAndroidApi.getQuery(mPager, map as Map<String, Any>?,object:ObserverCallBack<ApiData<ApiPager<ApiArticle>>>(mActivity){
            override fun onSuccess(t: ApiData<ApiPager<ApiArticle>>?, disposable: Disposable?) {
                val data = t?.data
                val curPage = data?.curPage

                mPager= curPage!!
                if(curPage==0){
                    as_srl_reflsh.isRefreshing=false
                }

                val datas = data.datas
            }

        })

    }
}
