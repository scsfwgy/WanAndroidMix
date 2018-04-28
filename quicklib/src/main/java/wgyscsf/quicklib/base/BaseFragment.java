package wgyscsf.quicklib.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * ============================================================
 * 作 者 :    wgyscsf@163.com
 * 更新时间 ：2018/04/22 12:46
 * 描 述 ：
 * ============================================================
 */
public class BaseFragment extends Fragment {
    protected BaseFragment mBaseFragment;
    protected Context mContext;
    protected String TAG;
    protected Toast mToast;
    protected CompositeDisposable mCompositeSubscription;//rx订阅者集合

    protected Unbinder mBind;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(null);
        if (isBindEventBusHere()) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, null);
        if (isBindButterKnife()) {
            mBind = ButterKnife.bind(this, view);
        }
        TAG = this.getClass().getSimpleName();
        mContext = this.getActivity();
        mBaseFragment = this;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!isHidden()) {
            onFragmentUiVisableListener(true);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (!isHidden()) {
            onFragmentUiVisableListener(false);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (isBindButterKnife() && mBind != null)
            mBind.unbind();
        if (isBindEventBusHere()) {
            EventBus.getDefault().unregister(this);
        }
        if (mCompositeSubscription != null && !mCompositeSubscription.isDisposed()) {
            mCompositeSubscription.dispose();
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

    //无参数的构造方法
    public BaseFragment() {
    }

    protected boolean isBindButterKnife() {
        return true;
    }

    protected boolean isBindEventBusHere() {
        return false;
    }

    public void showToast(String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(mContext.getApplicationContext(), msg, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(msg);
        }
        mToast.show();
    }


    /**
     * startActivity
     *
     * @param clazz target Activity
     */
    protected void go(Class<? extends Activity> clazz) {
        _goActivity(clazz, null, NON_CODE, false);
    }

    /**
     * startActivity with bundle
     *
     * @param clazz  target Activity
     * @param bundle
     */
    protected void go(Class<? extends Activity> clazz, Bundle bundle) {
        _goActivity(clazz, bundle, NON_CODE, false);
    }

    protected void goForResult(Class<? extends Activity> clazz, int requestCode) {
        _goActivity(clazz, null, requestCode, false);
    }

    protected void goForResult(Class<? extends Activity> clazz, Bundle bundle, int requestCode) {
        _goActivity(clazz, bundle, requestCode, false);
    }

    /**
     * Activity 跳转
     *
     * @param clazz  目标activity
     * @param bundle 传递参数
     * @param finish 是否结束当前activity
     */
    private static final int NON_CODE = -1;

    private void _goActivity(Class<? extends Activity> clazz, Bundle bundle, int requestCode, boolean finish) {
        if (null == clazz) {
            throw new IllegalArgumentException("you must pass a target activity where to go.");
        }
        Intent intent = new Intent(getActivity(), clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        if (requestCode > NON_CODE) {
            startActivityForResult(intent, requestCode);
        } else {
            startActivity(intent);
        }
        if (finish) {
            getActivity().finish();
        }
    }


    //该方法第一次打开帧布局并不会执行
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            onFragmentUiVisableListener(false);
        } else {
            onFragmentUiVisableListener(true);
        }
    }

    protected void onFragmentUiVisableListener(boolean uiVisable) {
        Log.e(TAG, "onFragmentUiVisableListener: 帧布局可见与否？" + uiVisable);
    }
}
