package com.gy.wanandroidmix.ui.fragment

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import com.gy.wanandroidmix.R
import com.gy.wanandroidmix.ui.base.MyBaseFragment
import kotlinx.android.synthetic.main.fragment_profile.*


/**
 * ============================================================
 * 作 者 :    wgyscsf@163.com
 * 更新时间 ：2018/04/27 13:13
 * 描 述 ：
 * ============================================================
 */
class ProfileFragment : MyBaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val inflate = inflater.inflate(R.layout.fragment_profile, container, false)
        return inflate
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
        initAdapter()
        initListner()
        loadData()
    }

    private fun initView() {
        fp_tv_txt.text = "我已经被修改"
    }

    private fun initData() {

    }

    private fun initAdapter() {

    }

    private fun initListner() {
        fp_b_btn.setOnClickListener {
            showToast("Hello,Toast")
        }
        fp_iv_img.setOnClickListener {
            var its: ImageView = it as ImageView
            its.setImageResource(R.drawable.mar_typ_ico_up)
            its.scaleType = ImageView.ScaleType.FIT_XY
            its.setBackgroundColor(resources.getColor(R.color.color_fundView_brokenLineColor))
        }

        fp_v_breath.setOnClickListener {
            it.isClickable = false
            val width = fp_v_breath.width
            val height = fp_v_breath.height


            var valueAnimator = ObjectAnimator.ofFloat(0.5f, 1.0f)
            valueAnimator.repeatMode = ValueAnimator.REVERSE
            valueAnimator.duration = 1000
            valueAnimator.repeatCount = -1
            valueAnimator.addUpdateListener {
                val animatedValue = it.getAnimatedValue() as Float
                fp_v_breath.alpha = animatedValue

                val lLayoutlayoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                lLayoutlayoutParams.width = (width * animatedValue).toInt()
                lLayoutlayoutParams.height = (height * animatedValue).toInt()
                fp_v_breath.setLayoutParams(lLayoutlayoutParams)


                Log.d(TAG, "--->" + animatedValue)
            }
            valueAnimator.start()

        }
    }

    private fun loadData() {

    }
}