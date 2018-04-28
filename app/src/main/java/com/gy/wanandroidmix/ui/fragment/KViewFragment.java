package com.gy.wanandroidmix.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.reflect.TypeToken;

import com.gy.wanandroidmix.R;
import com.gy.wanandroidmix.data.api.kview.ForexTab;
import com.gy.wanandroidmix.data.api.kview.KViewSimulateNetAPI;
import com.gy.wanandroidmix.data.model.OriginQuotes;
import com.gy.wanandroidmix.ui.base.MyBaseFragment;
import com.gy.wanandroidmix.view.kview.utils.StringUtils;
import com.gy.wanandroidmix.view.kview.view.kview.KView;
import com.gy.wanandroidmix.view.kview.view.kview.KViewListener;
import com.gy.wanandroidmix.view.kview.view.kview.Quotes;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import wgyscsf.quicklib.datautils.GsonUtil;

public class KViewFragment extends MyBaseFragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final int DEF_COUNT = 100;
    @BindView(R.id.fk_kv_kview)
    KView mFkKvKview;
    private View mRootView;
    ForexTab mForexTab;

    //模拟网络过来的列表数据
    List<Quotes> mQuotesList;
    //模拟加载更多的数据
    List<Quotes> mLoadMoreData;
    //模拟socket推送过来的单个数据
    List<Quotes> mPushData;
    //加载更多，加载到哪儿了。因为真实应用中，也存在加载完毕的情况。这里对应加载到list的最后
    int index = 0;

    public KViewFragment() {

    }

    public static KViewFragment newInstance(ForexTab mForexTab) {
        KViewFragment fragment = new KViewFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, mForexTab);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mForexTab = (ForexTab) getArguments().getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_kview, container, false);
        return mRootView;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
        loadData();
        pushData();
        initAdapter();
        initListener();
    }
    private void pushData() {
        Disposable disposable = Observable.interval(StringUtils.getRadomNum(300, 3000), TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        data -> {
                            int size = mPushData.size();
                            if (data < size) {
                                mFkKvKview.pushKViewData(mPushData.get(data.intValue()), 0);
                            } else {

                            }


                        }
                        , throwable -> throwable.printStackTrace());

        addDisposable(disposable);
    }

    private void initView() {
        if (mForexTab.mForexType == ForexTab.ForexType._timeShring) {
            mFkKvKview.setShowTimSharing(true);
            mFkKvKview.setShowMinor(true);
        } else {
            mFkKvKview.setShowTimSharing(false);
            mFkKvKview.setShowMinor(true);
        }
        mFkKvKview.setDigit(5);

    }

    private void initData() {
        mQuotesList = new ArrayList<>();
        mLoadMoreData = new ArrayList<>();
        mPushData = new ArrayList<>();

        //这里先预加载加载更多的数据，然后加载更多的时候分段取出来，模拟加载更多
        initLoadMoreData();

        //pushData
        initPushData();

    }

    private void initPushData() {
        String originalFundData = KViewSimulateNetAPI.getOriginalFundData(mContext, 2);
        if (originalFundData == null) {
            Log.e(TAG, "loadData: 从网络获取到的数据为空");
            return;
        }
        List<OriginQuotes> OriginFundModeList;
        try {
            OriginFundModeList = GsonUtil.fromJson2Object(originalFundData, new TypeToken<List<OriginQuotes>>() {
            }.getType());
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        //开始适配图表数据
        mPushData = adapterData(OriginFundModeList);
    }

    private List<Quotes> adapterData(List<OriginQuotes> originFundModeList) {
        List<Quotes> fundModeList = new ArrayList<>();//适配后的数据
        for (OriginQuotes OriginQuotes : originFundModeList) {
            Quotes Quotes = new Quotes(OriginQuotes.o, OriginQuotes.h, OriginQuotes.l,
                    OriginQuotes.c, OriginQuotes.t);
            fundModeList.add(Quotes);
        }
        return fundModeList;
    }

    private void initLoadMoreData() {
        String originalFundData = KViewSimulateNetAPI.getOriginalFundData(mContext, 0);
        if (originalFundData == null) {
            Log.e(TAG, "loadData: 从网络获取到的数据为空");
            return;
        }
        try {
            List<OriginQuotes> quotesList = GsonUtil.fromJson2Object(originalFundData,
                    new TypeToken<List<OriginQuotes>>() {
                    }.getType());
            mLoadMoreData = adapterData(quotesList);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    private void initAdapter() {

    }

    private void loadData() {
        //模拟网络环境加载数据列表
        Disposable disposable = Observable.timer(StringUtils.getRadomNum(500, 2000),
                TimeUnit.MILLISECONDS)
                .doOnNext(data -> {
                    String originalData = KViewSimulateNetAPI.getOriginalFundData(mContext, 2);
                    if (originalData == null) {
                        Log.e(TAG, "loadData: 从网络获取到的数据为空");
                        return;
                    }
                    try {
                        List<OriginQuotes> originQuotes = GsonUtil
                                .fromJson2Object(originalData, new TypeToken<List<OriginQuotes>>() {
                                }.getType());
                        mQuotesList = adapterData(originQuotes);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(TAG, "loadData: json转换错误");
                        return;
                    }
                })
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        data -> {
                            mFkKvKview.setKViewData(mQuotesList, new KViewListener.MasterTouchListener() {
                                @Override
                                public void onLongTouch(Quotes preQuotes, Quotes currentQuotes) {
                                    //showContanier(preQuotes, currentQuotes);
                                }

                                @Override
                                public void onUnLongTouch() {
                                    //mAkvLlContainer.setVisibility(View.INVISIBLE);
                                }

                                @Override
                                public void needLoadMore() {
                                    loadMore();
                                }
                            });
                        },
                        throwable -> throwable.printStackTrace()
                );
        addDisposable(disposable);
    }

    private void loadMore() {
        if (mLoadMoreData == null) return;
        Disposable disposable = Observable.timer(StringUtils.getRadomNum(1000, 5000), TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        data -> {
                            int size = mLoadMoreData.size();
                            int min = size / 20;
                            int max = size / 5;//一次最多加载多少
                            int loadSize = StringUtils.getRadomNum(min, max);
                            if (index == loadSize) {
                                //没有更多数据了
                                mFkKvKview.loadMoreNoData();
                            }
                            if ((index + loadSize) > mLoadMoreData.size()) {
                                loadSize = mLoadMoreData.size();
                            }
                            List<Quotes> loadList = mLoadMoreData.subList(index, index + loadSize);
                            index = index + loadSize;//重置起始位置
                            mFkKvKview.loadKViewData(loadList);
                        }
                        , throwable -> throwable.printStackTrace());
        addDisposable(disposable);
    }

    private void initListener() {
    }

}
