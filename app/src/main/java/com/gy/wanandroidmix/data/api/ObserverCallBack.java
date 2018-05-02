package com.gy.wanandroidmix.data.api;

import android.content.Context;
import android.util.Log;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.gy.wanandroidmix.data.model.ApiData;
import com.gy.wanandroidmix.ui.base.MyBaseActivity;

import java.net.SocketTimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import wgyscsf.quicklib.base.BaseFragment;

/**
 * ============================================================
 * 作 者 :    wgyscsf@163.com
 * 更新时间 ：2018/04/28 13:51
 * 描 述 ：包装后的Observer
 * ============================================================
 */
public abstract class ObserverCallBack<T> implements Observer<T> {
    public static final String TAG = ObserverCallBack.class.getSimpleName();
    private Context mContext;
    private BaseFragment mBaseFragment;
    private Disposable mDisposable;

    protected ObserverCallBack() {
        Log.w(TAG, "onSubscribe: 建议传递Activity、Fragment相关的上下文，否则请自行处理Disposabe");
    }

    protected ObserverCallBack(Context context) {
        this.mContext = context;
    }

    protected ObserverCallBack(BaseFragment fragment) {
        this.mBaseFragment = fragment;
    }


    @Override
    public void onSubscribe(Disposable d) {
        mDisposable = d;
        if (mContext != null && (mContext instanceof MyBaseActivity))
            ((MyBaseActivity) mContext).addDisposable(mDisposable);
        else if (mBaseFragment != null) {
            mBaseFragment.addDisposable(mDisposable);
        } else {
            Log.w(TAG, "onSubscribe: 建议传递Activity、Fragment相关的上下文，否则请自行处理Disposabe");
        }
    }

    @Override
    public void onNext(T t) {
        if (t instanceof ApiData) {
            ApiData apiData = (ApiData) t;
            if (apiData.errorCode < 0) {
                onFail(new WanAndroidException(), mDisposable, apiData.errorCode, apiData.errorMsg);
                return;
            }
        }
        onSuccess(t, mDisposable);
    }

    @Override
    public void onError(Throwable e) {
        onFail(e, mDisposable);
    }

    @Override
    public void onComplete() {

    }

    //---使用以下两个包装后的方法---

    public abstract void onSuccess(T t, Disposable disposable);


    /**
     * 错误信息回调,you can Override this
     *
     * @param t
     * @param disposable
     * @param errCode
     * @param errMsg     maybe null
     */
    public void onFail(Throwable t, Disposable disposable, int errCode, String errMsg) {
        //统一的处理
        if (!StringUtils.isEmpty(errMsg)) {
            ToastUtils.showLong(errMsg + ":" + errCode);
        } else {
            if (t != null) {
                t.printStackTrace();
                String message = t.getMessage();
                if (message != null) {
                    if (message.contains("500")) {
                        ToastUtils.showLong("服务器异常，请稍后重试");
                    } else if (t instanceof SocketTimeoutException) {
                        ToastUtils.showLong("当前网络不佳，请稍后重试");
                    } else {
                        ToastUtils.showLong("未知错误，请稍后重试");
                    }
                }
            } else {
                ToastUtils.showLong("未知错误，请稍后重试");
            }
        }

    }

    private void onFail(Throwable t, Disposable disposable, int errCode) {
        onFail(t, disposable, errCode, null);
    }

    private void onFail(Throwable t, Disposable disposable, String errMsg) {
        onFail(t, disposable, 0, errMsg);
    }

    private void onFail(Throwable t, Disposable disposable) {
        onFail(t, disposable, 0, null);
    }
}
