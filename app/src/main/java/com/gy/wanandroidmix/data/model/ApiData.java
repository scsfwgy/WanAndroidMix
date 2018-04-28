package com.gy.wanandroidmix.data.model;

/**
 * ============================================================
 * 作 者 :    wgyscsf@163.com
 * 更新时间 ：2018/04/28 13:36
 * 描 述 ：全局API数据模型
 * ============================================================
 */
public class ApiData<T> {
    public int errorCode;
    public String errorMsg;
    public T data;

    @Override
    public String toString() {
        return "ApiData{" +
                "errorCode=" + errorCode +
                ", errorMsg='" + errorMsg + '\'' +
                ", data=" + data +
                '}';
    }
}
