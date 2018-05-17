package com.gy.wanandroidmix.data.model

/**
 * ============================================================
 * 作 者 :    wgyscsf@163.com
 * 更新时间 ：2018/05/17 14:12
 * 描 述 ：
 * ============================================================
 */
data class ApiSonTree(
        val courseId: String,
        val id: String,
        val name: String,
        val order: String,
        val parentChapterId: String,
        val visible: Int)
    : ApiBase()