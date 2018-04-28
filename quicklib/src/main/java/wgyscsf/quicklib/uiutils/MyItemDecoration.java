package wgyscsf.quicklib.uiutils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.ColorInt;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import wgyscsf.quicklib.R;

/**
 * ============================================================
 * 作 者 :    wgyscsf@163.com
 * 创建日期 ：2017/12/7 17:28
 * 描 述 ：recycle分割线
 * ============================================================
 **/
public class MyItemDecoration extends RecyclerView.ItemDecoration {
    //当设置padding之后，padding的颜色是recycle背景的颜色
    //垂直方向使用
    protected float mLeftPadding;
    protected float mRightPadding;
    //水平方向使用
    protected float mTopPadding;
    protected float mBottomPadding;
    //分割线的高度
    protected float mDividerHeight;
    //分割线的颜色
    protected int mDividerColor;
    protected Paint mDividerPaint;
    protected Context mContext;
    //是否绘制最后一条分割线
    protected boolean mDrawLastDivider = true;

    public MyItemDecoration(Context context) {
        this(context, 0, 0, true);
    }

    public MyItemDecoration(Context context, int dividerColor) {
        this(context, dividerColor, 0, true);
    }

    public MyItemDecoration(Context context, boolean drawLastDivider) {
        this(context, 0, 0, drawLastDivider);
    }

    public MyItemDecoration(Context context, @ColorInt int dividerColor, float dividerHeight, boolean drawLastDivider) {
        this.mContext = context;
        this.mDividerColor = dividerColor;
        this.mDividerHeight = dividerHeight;
        this.mDrawLastDivider = drawLastDivider;
        if (mDividerColor == 0) {
            mDividerColor = mContext.getResources().getColor(R.color.color_global_divider_color);
        }
        if (mDividerHeight <= 0) {
            mDividerHeight = mContext.getResources().getDimension(R.dimen.dimens_global_divider_hight);
        }
        mDividerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDividerPaint.setStyle(Paint.Style.FILL);
        mDividerPaint.setColor(mDividerColor);
    }

    @Override
    public void onDraw(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            LinearLayoutManager manager = (LinearLayoutManager) layoutManager;
            if (manager.getOrientation() == LinearLayoutManager.VERTICAL) {
                drawVertical(canvas, parent);
            } else {
                drawHorizontal(canvas, parent);
            }
        }

    }

    private void drawHorizontal(Canvas canvas, RecyclerView parent) {
        int childCount = parent.getChildCount();
        int height = parent.getHeight();
        int top = parent.getPaddingTop() + (int) Math.ceil(mTopPadding);
        int bottom = height - parent.getPaddingBottom() - (int) Math.ceil(mBottomPadding);
        for (int i = 0; i < childCount; i++) {
            if (i == childCount - 1 && !mDrawLastDivider) return;

            View view = parent.getChildAt(i);
            float left = view.getRight();
            float right = view.getRight() + mDividerHeight;
            canvas.drawRect(left, top, right, bottom, mDividerPaint);
        }
    }

    private void drawVertical(Canvas canvas, RecyclerView parent) {
        int childCount = parent.getChildCount();
        int width = parent.getWidth();
        int left = parent.getPaddingLeft() + (int) Math.ceil(mLeftPadding);
        int right = width - parent.getPaddingRight() - (int) Math.ceil(mRightPadding);
        for (int i = 0; i < childCount; i++) {
            if (i == childCount - 1 && !mDrawLastDivider) return;

            View view = parent.getChildAt(i);
            float top = view.getBottom();
            float bottom = view.getBottom() + mDividerHeight;
            canvas.drawRect(left, top, right, bottom, mDividerPaint);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            LinearLayoutManager manager = (LinearLayoutManager) layoutManager;
            int hight = (int) Math.ceil(mDividerHeight);
            if (manager.getOrientation() == LinearLayoutManager.VERTICAL) {
                outRect.set(0, 0, 0, hight);
            } else {
                outRect.set(0, 0, hight, 0);
            }
        }
    }

    public MyItemDecoration setLeftPadding(float leftPadding) {
        mLeftPadding = leftPadding;
        return this;

    }

    public MyItemDecoration setRightPadding(float rightPadding) {
        mRightPadding = rightPadding;
        return this;

    }

    public MyItemDecoration setDrawLastDivider(boolean drawLastDivider) {
        mDrawLastDivider = drawLastDivider;
        return this;
    }

    public MyItemDecoration setTopPadding(float topPadding) {
        mTopPadding = topPadding;
        return this;
    }

    public MyItemDecoration setBottomPadding(float bottomPadding) {
        mBottomPadding = bottomPadding;
        return this;
    }
}
