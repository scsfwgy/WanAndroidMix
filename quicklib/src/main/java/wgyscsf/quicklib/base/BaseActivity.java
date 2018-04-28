package wgyscsf.quicklib.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.gyf.barlibrary.ImmersionBar;
import com.umeng.analytics.MobclickAgent;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;
import wgyscsf.quicklib.R;
import wgyscsf.quicklib.uiutils.BaseAppManager;
import wgyscsf.quicklib.uiutils.dialog.CustomerDialog;
import wgyscsf.quicklib.uiutils.dialog.LoadingDialog;

/**
 * ============================================================
 * 作 者 :    wgyscsf
 * 创建日期 ：2017/10/16 16:27
 * 描 述 ：
 * ============================================================
 **/
public class BaseActivity extends SwipeBackActivity {
    public static final int NON_CODE = -1;

    protected Context mContext = null;
    protected String TAG = null;

    //小刀
    protected Unbinder mBind;

    protected Activity mActivity;
    //retrofit请求集合
    private CompositeDisposable mCompositeSubscription;
    //loadingDialog
    LoadingDialog mLoadingDialog;
    //对话框
    CustomerDialog mCustomerDialog;
    /**
     * 对系统系统的toast进行简单封装，方便使用
     */
    private Toast toast = null;

    //实时监听键盘
    private ViewTreeObserver.OnGlobalLayoutListener mLayoutChangeListener;
    private boolean mIsSoftKeyboardShowing;
    private int mScreenHeight;

    //沉浸式
    protected ImmersionBar mImmersionBar;
    private int MImersiveColor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        mContext = this;
        TAG = this.getClass().getSimpleName();

        //add Activity
        BaseAppManager.getInstance().addActivity(this);

        // getBundleExtras
        Bundle extras = getIntent().getExtras();
        if (null != extras) {
            getBundleExtras(extras);
        }
        // EventBus.register
        if (isBindEventBusHere()) {
            EventBus.getDefault().register(this);
        }
        //设置入场动画
        if (isOverridePendingTransition()) {
            _overridePendingTransition();
        }
        //打开右滑返回上一级
        if (isSwipeBackHere()) {
            setSwipeBackEnable(true);
        } else {
            setSwipeBackEnable(false);
        }
        if (isBindKeyBoardHere()) {
            keyboardListener();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isUmengSession()) {
            MobclickAgent.onResume(this);
        }
        //是否开沉浸式
        if (isMImmersive()) {
            setMImersive();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isUmengSession()) {
            MobclickAgent.onPause(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isBindButterKnife()) {
            if (mBind != null)
                mBind.unbind();

        }
        if (isBindEventBusHere()) {
            EventBus.getDefault().unregister(this);
        }
        if (isOverridePendingTransition()) {
            _overridePendingTransition();
        }
        if (mCompositeSubscription != null) {
            Log.d(TAG, "base activity unscbscribe");
            mCompositeSubscription.clear();
        }
        if (isMImmersive()) {
            if (mImmersionBar != null)
                //必须调用该方法，防止内存泄漏，不调用该方法，如果界面bar发生改变，在不关闭app的情况下，退出此界面再进入将记忆最后一次bar改变的状态
                mImmersionBar.destroy();
        }
        //移除布局变化监听
        if (mLayoutChangeListener != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                getWindow().getDecorView().getViewTreeObserver().removeOnGlobalLayoutListener(mLayoutChangeListener);
            } else {
                getWindow().getDecorView().getViewTreeObserver().removeGlobalOnLayoutListener(mLayoutChangeListener);
            }
        }
    }

    @Override
    public void setContentView(int view) {
        super.setContentView(view);
        if (isBindButterKnife()) {
            mBind = ButterKnife.bind(this);
        }
    }

    /**
     * 设置沉浸式
     */
    protected void setMImersive() {
        setMImersive(R.color.color_global_immersion);
    }

    protected void setMImersive(@ColorRes int colorId) {
        setMImersiveColor(colorId);
        //所有子类都将继承这些相同的属性
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar
                .fitsSystemWindows(true)  //使用该属性,必须指定状态栏颜色
                .statusBarColor(colorId)
                .init();
    }

    public int getMImersiveColor() {
        return MImersiveColor;
    }

    public void setMImersiveColor(int MImersiveColor) {
        this.MImersiveColor = MImersiveColor;
    }

    /**
     * 显示隐藏状态栏，全屏不变，只在有全屏时有效
     *
     * @param enable
     */
    protected void setStatusBarVisibility(boolean enable) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        if (enable) {
            lp.flags |= WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN;
        } else {
            lp.flags &= (~WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        }
        getWindow().setAttributes(lp);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }

    /**
     * 设置是否全屏
     *
     * @param enable
     */
    protected void setFullScreen(boolean enable) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        if (enable) {
            lp.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        } else {
            lp.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        getWindow().setAttributes(lp);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }

    public CustomerDialog showDialog(String msg) {
        showDialog(msg, true);
        return mCustomerDialog;
    }

    public CustomerDialog showDialog(String msg, boolean canCancel) {
        initSingletonCustomerDialog();
        mCustomerDialog.setMessage(msg);
        mCustomerDialog.showDialog();
        mCustomerDialog.canCancelable(canCancel);
        return mCustomerDialog;
    }

    public CustomerDialog hideCustomter() {
        if (mCustomerDialog != null) {
            mCustomerDialog.showDialog();
        }
        return mCustomerDialog;
    }

    private void initSingletonCustomerDialog() {
        if (mCustomerDialog == null) {
            synchronized (CustomerDialog.class) {
                if (mCustomerDialog == null) {
                    mCustomerDialog = new CustomerDialog(mContext);
                }
            }
        }
    }

    public void showLoading() {
        showLoading(true);
    }

    public void showLoading(boolean canCancle) {
        initSingletonLoadingDialog();
        mLoadingDialog.showDialog();
        mLoadingDialog.canCancelable(canCancle);
    }

    private void initSingletonLoadingDialog() {
        if (mLoadingDialog == null) {
            synchronized (LoadingDialog.class) {
                if (mLoadingDialog == null) {
                    mLoadingDialog = new LoadingDialog(mContext);
                }
            }
        }
    }

    public void hideLoading() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.dialogDisMiss();
        }
    }

    /**
     * 添加Subscription
     *
     * @param disposable
     */
    public void addDisposable(Disposable disposable) {
        if (mCompositeSubscription == null) {
            synchronized (CompositeDisposable.class) {
                if (mCompositeSubscription == null) {
                    mCompositeSubscription = new CompositeDisposable();
                }
            }
        }
        mCompositeSubscription.add(disposable);
    }


    /**
     * 在你的activity动作完成的时候，或者Activity需要关闭的时候，调用此方法。
     * 当你调用此方法的时候，系统只是将最上面的Activity移出了栈，
     * 并**没有及时**的调用onDestory（）方法，其占用的资源也没有被及时释放。
     * 因为移出了栈，所以当你点击手机上面的“back”按键的时候，也不会再找到这个Activity。
     */
    @Override
    public void finish() {
        super.finish();
        //由上述解释可以看出，在onDestory执行这句话不合理，因此放到这里。
        BaseAppManager.getInstance().removeActivity(this);
    }

    /**
     * @param extras desc：获取bundle
     */
    protected void getBundleExtras(Bundle extras) {
    }


    /**
     * 是否绑定ButterKnife
     */
    protected boolean isBindButterKnife() {
        return true;
    }

    /**
     * 是否绑定EventBus
     */
    protected boolean isBindEventBusHere() {
        return false;
    }

    /**
     * 是否开启沉浸式
     */
    protected boolean isMImmersive() {
        return true;
    }

    /**
     * 是否开启入出场动画
     */
    protected boolean isOverridePendingTransition() {
        return true;
    }

    /**
     * 是否开启侧滑删除activity
     */
    protected boolean isSwipeBackHere() {
        return true;
    }

    //设置沉浸式样式
    protected TransitionMode getTransitionMode() {
        return TransitionMode.NONE;
    }

    /**
     * 是否开启友盟session的统计
     */
    protected boolean isUmengSession() {
        return true;
    }

    /**
     * 是否开启监听键盘
     */
    protected boolean isBindKeyBoardHere() {
        return false;
    }

    /**
     * 监听键盘
     *
     * @param isKeyBoardShow 是否弹出了键盘
     * @param keyboardHeight 弹出高度
     */
    protected void keyboardListenering(boolean isKeyBoardShow, int keyboardHeight) {

    }

    /**
     * startActivity
     *
     * @param clazz target Activity
     */
    public void go(Class<? extends Activity> clazz) {
        _goActivity(clazz, null, NON_CODE, false);
    }

    /**
     * startActivity with bundle
     *
     * @param clazz  target Activity
     * @param bundle
     */
    public void go(Class<? extends Activity> clazz, Bundle bundle) {
        _goActivity(clazz, bundle, NON_CODE, false);
    }

    /**
     * startActivity then finish this
     *
     * @param clazz target Activity
     */
    public void goAndFinish(Class<? extends Activity> clazz) {
        _goActivity(clazz, null, NON_CODE, true);
    }

    /**
     * startActivity with bundle and then finish this
     *
     * @param clazz  target Activity
     * @param bundle bundle extra
     */
    public void goAndFinish(Class<? extends Activity> clazz, Bundle bundle) {
        _goActivity(clazz, bundle, NON_CODE, true);
    }

    /**
     * startActivityForResult
     *
     * @param clazz
     * @param requestCode
     */
    protected void goForResult(Class<? extends Activity> clazz, int requestCode) {
        _goActivity(clazz, null, requestCode, false);
    }

    /**
     * startActivityForResult with bundle
     *
     * @param clazz
     * @param bundle
     * @param requestCode
     */
    protected void goForResult(Class<? extends Activity> clazz, Bundle bundle, int requestCode) {
        _goActivity(clazz, bundle, requestCode, false);
    }

    /**
     * startActivityForResult then finish this
     *
     * @param clazz
     * @param requestCode
     */
    protected void goForResultAndFinish(Class<? extends Activity> clazz, int requestCode) {
        _goActivity(clazz, null, requestCode, true);
    }

    /**
     * startActivityForResult with bundle and then finish this
     *
     * @param clazz
     * @param bundle
     * @param requestCode
     */
    protected void goForResultAndFinish(Class<? extends Activity> clazz, Bundle bundle, int requestCode) {
        _goActivity(clazz, bundle, requestCode, true);
    }

    /**
     * ============= interval methods =================
     */
    /**
     * 设置转场动画
     */
    private void _overridePendingTransition() {
        switch (getTransitionMode()) {
            case LEFT:
                overridePendingTransition(R.anim.left_in, R.anim.left_out);
                break;
            case RIGHT:
                overridePendingTransition(R.anim.right_in, R.anim.right_out);
                break;
            case TOP:
                overridePendingTransition(R.anim.top_in, R.anim.top_out);
                break;
            case BOTTOM:
                overridePendingTransition(R.anim.bottom_in, R.anim.bottom_out);
                break;
            case SCALE:
                overridePendingTransition(R.anim.scale_in, R.anim.scale_out);
                break;
            case FADE:
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                break;
            case NONE:
            default:
                break;
        }
    }

    /**
     * overridePendingTransition mode: 转场动画
     */
    public enum TransitionMode {
        NONE, LEFT, RIGHT, TOP, BOTTOM, SCALE, FADE
    }

    //可以立刻刷新Toast。推荐使用该方式。
    public void showSingletonToast(String str) {
        if (toast == null) {
            toast = Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT);
        } else {
            toast.setText(str);
        }
        toast.show();
    }

    public void showToast(String msg) {
        showToast(msg, Toast.LENGTH_SHORT);
    }

    public void showToast(String msg, int toastDuration) {
        if (null != msg && (toastDuration == Toast.LENGTH_SHORT || toastDuration == Toast.LENGTH_LONG)) {
            Toast.makeText(getApplicationContext(), msg, toastDuration).show();
        }
    }

    private void _goActivity(Class<? extends Activity> clazz, Bundle bundle, int requestCode, boolean finish) {
        if (null == clazz) {
            throw new IllegalArgumentException("you must pass a target activity where to go.");
        }
        Intent intent = new Intent(this, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        if (requestCode > NON_CODE) {
            startActivityForResult(intent, requestCode);
        } else {
            startActivity(intent);
        }
        if (finish) {
            finish();
        }
    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    private void keyboardListener() {
        mScreenHeight = getWindowManager().getDefaultDisplay().getHeight();
        mIsSoftKeyboardShowing = false;
        mLayoutChangeListener = new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //判断窗口可见区域大小
                Rect r = new Rect();
                getWindow().getDecorView().getWindowVisibleDisplayFrame(r);

                //如果屏幕高度和Window可见区域高度差值大于整个屏幕高度的1/3，则表示软键盘显示中，否则软键盘为隐藏状态。
                int heightDifference = mScreenHeight - (r.bottom - r.top);
                boolean isKeyboardShowing = heightDifference > mScreenHeight / 3;

                //如果之前软键盘状态为显示，现在为关闭，或者之前为关闭，现在为显示，则表示软键盘的状态发生了改变
                if ((mIsSoftKeyboardShowing && !isKeyboardShowing) || (!mIsSoftKeyboardShowing && isKeyboardShowing)) {
                    mIsSoftKeyboardShowing = isKeyboardShowing;
                    keyboardListenering(isKeyboardShowing, heightDifference);
                }
            }
        };
        //注册布局变化监听
        getWindow().getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(mLayoutChangeListener);
    }
}
