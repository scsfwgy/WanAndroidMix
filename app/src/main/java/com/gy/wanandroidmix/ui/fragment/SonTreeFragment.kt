package com.gy.wanandroidmix.ui.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.blankj.utilcode.util.TimeUtils
import com.chad.library.adapter.base.BaseViewHolder
import com.gy.wanandroidmix.R
import com.gy.wanandroidmix.data.api.ObserverCallBack
import com.gy.wanandroidmix.data.api.WanAndroidApi
import com.gy.wanandroidmix.data.model.ApiArticle
import com.gy.wanandroidmix.data.model.ApiData
import com.gy.wanandroidmix.data.model.ApiPager
import com.gy.wanandroidmix.ui.base.MyBaseFragment
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_son_tree.*
import wgyscsf.quicklib.uiutils.MBaseQuickAdapter
import wgyscsf.quicklib.uiutils.glide.GlideUtil


// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [SonTreeFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [SonTreeFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class SonTreeFragment : MyBaseFragment() {
    private var sId: Int = 0
    private var mPager: Int = 0
    private lateinit var mApiArticleList: MutableList<ApiArticle>
    private lateinit var mBaseQuickAdapter: MBaseQuickAdapter<ApiArticle, BaseViewHolder>

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SonTreeFragment.
         */
        @JvmStatic
        fun newInstance(param1: Int) =
                SonTreeFragment().apply {
                    arguments = Bundle().apply {
                        putInt(ARG_PARAM1, param1)
                    }
                }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            sId = it.getInt(ARG_PARAM1)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_son_tree, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initAdapter()
        loadData()
        initListener()
    }

    private fun initListener() {
        fst_srl_fresh.setOnRefreshListener {
            mPager = 0
            loadData()
        }
        mBaseQuickAdapter.setOnLoadMoreListener({
            mPager++
            loadData()
        }, fst_rv_recyclervier)
    }

    private fun initData() {
        mApiArticleList = ArrayList()
    }

    private fun initAdapter() {
        mBaseQuickAdapter = object : MBaseQuickAdapter<ApiArticle, BaseViewHolder>(
                R.layout.item_fragment_son_tree,
                mApiArticleList, mContext) {
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
                mIfhTvAuthor.text = "作者：${item.author}"
                mIfhTvCategory.text = "分类：${item.chapterName}"
                mIfhTvTime.text = "时间：${TimeUtils.getFriendlyTimeSpanByNow(item.publishTime)}"
            }
        }
        fst_rv_recyclervier.layoutManager = LinearLayoutManager(mContext)
        fst_rv_recyclervier.adapter = mBaseQuickAdapter
    }

    private fun loadData() {
        WanAndroidApi.getArticle(mPager, sId, object : ObserverCallBack<ApiData<ApiPager<ApiArticle>>>(mBaseFragment) {
            override fun onSuccess(t: ApiData<ApiPager<ApiArticle>>, disposable: Disposable?) {
                if (fst_srl_fresh.isRefreshing) {
                    fst_srl_fresh.isRefreshing = false
                }
                if (mPager == 0) {
                    mApiArticleList.clear()
                }
                mBaseQuickAdapter.loadMoreComplete()

                val data = t.data
                if (data.over) {
                    mBaseQuickAdapter.loadMoreEnd()
                }
                mApiArticleList.addAll(data.datas)
                mBaseQuickAdapter.notifyDataSetChanged()
            }

        })
    }
}
