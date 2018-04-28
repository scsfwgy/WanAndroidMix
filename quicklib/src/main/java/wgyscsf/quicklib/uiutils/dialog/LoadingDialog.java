package wgyscsf.quicklib.uiutils.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.View;

import wgyscsf.quicklib.R;

/**
 * ============================================================
 * 作 者 :    wgyscsf@163.com
 * 创建日期 ：2017/11/2 11:45
 * 描 述 ：
 * ============================================================
 **/
public class LoadingDialog extends BaseDialog {
    View rootView;

    public LoadingDialog(@NonNull Context context) {
        this(context, R.style.DialogStyle);
    }

    public LoadingDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        rootView = View.inflate(context, R.layout.dialog_loading_view, null);

        setContentView(rootView);
        canCancelable(false);
    }

    /**
     * 显示提示框
     * 默认点击取消提示框消失
     */
    public void showDialog() {
        show();
    }

    public void dialogDisMiss() {
        dismiss();
    }


}
