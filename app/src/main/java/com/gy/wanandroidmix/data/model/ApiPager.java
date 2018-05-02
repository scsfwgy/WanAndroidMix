package com.gy.wanandroidmix.data.model;

import java.util.List;

/**
 * ============================================================
 * 作 者 :    wgyscsf@163.com
 * 更新时间 ：2018/05/02 15:20
 * 描 述 ：带分页的数据集合
 * ============================================================
 */
public class ApiPager<T> {

    /**
     * curPage : 2
     * datas : [{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{}]
     * offset : 20
     * over : false
     * pageCount : 62
     * size : 20
     * total : 1238
     */

    public int curPage;
    public int offset;
    public boolean over;
    public int pageCount;
    public int size;
    public int total;
    public List<T> datas;

    @Override
    public String toString() {
        return "ApiPager{" +
                "curPage=" + curPage +
                ", offset=" + offset +
                ", over=" + over +
                ", pageCount=" + pageCount +
                ", size=" + size +
                ", total=" + total +
                ", datas=" + datas +
                '}';
    }
}
