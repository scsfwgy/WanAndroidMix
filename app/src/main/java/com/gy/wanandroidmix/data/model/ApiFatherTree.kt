package com.gy.wanandroidmix.data.model

/**
 * ============================================================
 * 作 者 :    wgyscsf@163.com
 * 更新时间 ：2018/05/17 14:12
 * 描 述 ：
 * ============================================================
 */
data class ApiFatherTree(
        val children: List<ApiSonTree>,
        val courseId:Int,
        val id:Int,
        val name:String,
        val order:Int,
        val parentChapterId:Int,
        val visible:Int
) : ApiBase()