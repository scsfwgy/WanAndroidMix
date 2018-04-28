package wgyscsf.quicklib.uiutils.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;

/**
 * ============================================================
 * 作 者 :    wgyscsf@163.com
 * 创建日期 ：2017/11/2 11:43
 * 描 述 ：
 * ============================================================
 **/
public class BaseDialog extends Dialog {
    public BaseDialog(@NonNull Context context) {
        super(context);
    }

    public BaseDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    protected BaseDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    /**
     * 是否可以取消
     * 默认:不可取消
     */
    public void canCancelable(boolean cancel) {
        if (!cancel) {
            setCancelable(false);
            setCanceledOnTouchOutside(false);
        } else {
            setCancelable(true);
            setCanceledOnTouchOutside(true);
        }

    }
}
