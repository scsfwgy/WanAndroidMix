package wgyscsf.quicklib.uiutils;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import wgyscsf.quicklib.R;

/**
 * ============================================================
 * 作 者 :    wgyscsf@163.com
 * 创建日期 ：2017/11/28 17:44
 * 描 述 ：Recycle适配器扩展类
 * ============================================================
 **/
public abstract class MBaseQuickAdapter<T, K extends BaseViewHolder> extends BaseQuickAdapter<T, K> {
    View mEmptyView;
    TextView mEmptyText;
    Context mContext;

    protected MBaseQuickAdapter(int layoutResId, @Nullable List<T> data, Context context) {
        super(layoutResId, data);
        //可以保证该构造方法执行
        mContext = context;
        initAttrs();
    }

    //默认属性
    private void initAttrs() {
        //empty
        mEmptyView = LayoutInflater.from(mContext).inflate(R.layout.common_default_empty_text, null);
        mEmptyText = (TextView) mEmptyView.findViewById(R.id.empty_text);


        setEmptyView(mEmptyView);

        setEnableLoadMore(true);
        openLoadAnimation(MBaseQuickAdapter.ALPHAIN);
        setPreLoadNumber(5);


    }

    public void showEmptyText(String str) {
        if (StringUtils.isEmpty(str)) {
            mEmptyText.setText(mContext.getResources().getString(R.string.loading));
            return;
        }
        mEmptyText.setText(str);
    }

    public void showEmptyText() {
        showEmptyText(null);
    }


    public void showNoDataText() {
        showNoDataText(null);
    }

    public void showNoDataText(String str) {
        if (StringUtils.isEmpty(str)) {
            mEmptyText.setText(mContext.getResources().getString(R.string.no_data));
            return;
        }
        mEmptyText.setText(str);
    }
}

