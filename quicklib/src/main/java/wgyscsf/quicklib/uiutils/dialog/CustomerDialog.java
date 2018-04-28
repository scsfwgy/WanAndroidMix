package wgyscsf.quicklib.uiutils.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;

import wgyscsf.quicklib.R;

/**
 * ============================================================
 * 作 者 :    wgyscsf@163.com
 * 创建日期 ：2017/11/2 9:46
 * 描 述 ：
 * ============================================================
 **/
public class CustomerDialog extends BaseDialog {
    View rootView;
    private TextView mDcvTvTitle;
    private TextView mDcvTvMessage;
    private View mDcvVHorizontalLine;
    private LinearLayout mDcvLlBottom;
    private TextView mDcvTvTrue;
    private View mDcvVVerticalLine;
    private TextView mDcvTvCancle;

    public CustomerDialog(@NonNull Context context) {
        this(context, R.style.DialogStyle);
    }

    public CustomerDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        rootView = View.inflate(context, R.layout.dialog_customer_view, null);
        mDcvTvTitle = (TextView) rootView.findViewById(R.id.dcv_tv_title);
        mDcvTvMessage = (TextView) rootView.findViewById(R.id.dcv_tv_message);
        mDcvVHorizontalLine = (View) rootView.findViewById(R.id.dcv_v_horizontalLine);
        mDcvLlBottom = (LinearLayout) rootView.findViewById(R.id.dcv_ll_bottom);
        mDcvTvTrue = (TextView) rootView.findViewById(R.id.dcv_tv_true);
        mDcvVVerticalLine = (View) rootView.findViewById(R.id.dcv_v_verticalLine);
        mDcvTvCancle = (TextView) rootView.findViewById(R.id.dcv_tv_cancle);

        setContentView(rootView);
    }

    /**
     * 显示提示框
     * 默认点击取消提示框消失
     */
    public void showDialog() {
        show();
    }

    /**
     * @param title 提示信息  设置为空则默认显示为:提示
     *              不设置提示则默认不显示提示
     */
    public void setTitle(String title) {
        if (!StringUtils.isEmpty(title)) {
            mDcvTvTitle.setText(title);
        } else {
            mDcvTvTitle.setText(getContext().getString(R.string.dcv_dialog_title));
        }
        mDcvTvTitle.setVisibility(View.VISIBLE);
    }

    /**
     * @param message 提示内容  为空或者不设置则不显示
     */
    public void setMessage(String message) {
        if (!StringUtils.isEmpty(message)) {
            mDcvTvMessage.setText(message);
            mDcvTvMessage.setVisibility(View.VISIBLE);
        } else {
            mDcvTvMessage.setVisibility(View.GONE);
        }
    }

    private void dialogDisMiss() {
        dismiss();
    }

    /**
     * @param text     左侧按钮显示内容 设置为空则默认显示确定
     * @param listener 左侧按钮点击事件处理,且diaolog消失
     */
    public void setPositiveButton(String text, final View.OnClickListener listener) {
        if (!StringUtils.isEmpty(text)) {
            mDcvTvTrue.setText(text);
        } else {
            mDcvTvTrue.setText(getContext().getString(R.string.dcv_dialog_ok));
        }
        mDcvTvTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(view);
                dialogDisMiss();
            }
        });
    }

    /**
     * @param text 右侧按钮显示 为空默认显示取消
     *             不设置则默认不显示,同时竖线也不再显示
     */
    public void setNegativeButton(String text) {
        if (!StringUtils.isEmpty(text)) {
            mDcvTvCancle.setText(text);
        } else {
            mDcvTvCancle.setText(getContext().getString(R.string.dcv_dialog_cancle));
        }
        mDcvTvCancle.setVisibility(View.VISIBLE);
        mDcvVVerticalLine.setVisibility(View.VISIBLE);
        mDcvTvCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogDisMiss();
            }
        });
    }

    /**
     * @param text     右侧按钮显示 为空默认显示取消 不设置则默认不显示,同时竖线也不再显示
     * @param listener 右侧按钮点击事件处理
     */
    public void setNegativeButton(String text, final View.OnClickListener listener) {
        if (!StringUtils.isEmpty(text)) {
            mDcvTvCancle.setText(text);
        } else {
            mDcvTvCancle.setText(getContext().getString(R.string.dcv_dialog_cancle));
        }
        mDcvTvCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(view);
                dialogDisMiss();
            }
        });
        mDcvTvCancle.setVisibility(View.VISIBLE);
        mDcvVVerticalLine.setVisibility(View.VISIBLE);
    }

    public void setTitleSize(float size) {
        mDcvTvTitle.setTextSize(size);
    }

    public void setTitleColor(int color) {
        if (color != 0) {
            mDcvTvTitle.setTextColor(color);
        }
    }

    public void setMessageColor(int color) {
        if (color != 0) {
            mDcvTvMessage.setTextColor(color);
        }
    }

    public void setMessageGravity(int gravity) {
        mDcvTvMessage.setGravity(gravity);
    }

    public void setMessageSize(float size) {
        mDcvTvMessage.setTextSize(size);
    }

    public void setPositiveButtonColor(int color) {
        if (color != 0) {
            mDcvTvTrue.setTextColor(color);
        }
    }

    public void setPositiveButton(String text) {
        if (!StringUtils.isEmpty(text)) {
            mDcvTvTrue.setText(text);
        } else {
            mDcvTvTrue.setText(getContext().getString(R.string.dcv_dialog_ok));
        }
        mDcvTvTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogDisMiss();
            }
        });
    }

    public void setPositiveButtonVisible(int visibility) {
        mDcvVVerticalLine.setVisibility(visibility);
        mDcvTvTrue.setVisibility(visibility);
    }

    public void setNegativeButtonVisible(int visibility) {
        mDcvVVerticalLine.setVisibility(visibility);
        mDcvTvCancle.setVisibility(visibility);
    }

    public void setNegativeButtonColor(int color) {
        if (color != 0) {
            mDcvTvCancle.setTextColor(color);
        }
    }

    public void setPositiveButtonEnabled(boolean enabled) {
        mDcvTvTrue.setEnabled(enabled);
    }

    public void setNegativeButtonEnabled(boolean enabled) {
        mDcvTvCancle.setEnabled(enabled);
    }

    public void setMessageBottomMargin(int bottomMargin) {
        ViewGroup.LayoutParams layoutParams = mDcvTvMessage.getLayoutParams();
        if (layoutParams instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) layoutParams;
            params.bottomMargin = bottomMargin;
        }
    }

    public void setBottomGone() {
        mDcvVHorizontalLine.setVisibility(View.GONE);
        mDcvLlBottom.setVisibility(View.GONE);
    }

}
