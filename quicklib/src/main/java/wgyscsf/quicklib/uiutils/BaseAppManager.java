package wgyscsf.quicklib.uiutils;

import android.app.Activity;

import java.util.LinkedList;
import java.util.List;


public class BaseAppManager {

    private static final String TAG = BaseAppManager.class.getSimpleName();
    private static BaseAppManager manager;
    private List<Activity> mActivities;

    private BaseAppManager() {
        mActivities = new LinkedList<Activity>();
    }

    public static BaseAppManager getInstance() {
        if (null == manager) {
            synchronized (BaseAppManager.class) {
                if (null == manager) {
                    manager = new BaseAppManager();
                }
            }
        }
        return manager;
    }

    /**
     * 添加Activity
     *
     * @param activity
     */
    public synchronized void addActivity(Activity activity) {
        mActivities.add(activity);
    }

    public int size() {
        return mActivities.size();
    }

    /**
     * 取得栈顶Activity
     *
     * @return
     */
    public synchronized Activity getFrontActivity() {
        for (int i = mActivities.size()-1; i >=0 ; i--) {
            Activity activity = mActivities.get(i);
            if (!activity.isFinishing()) {
                return activity;
            }
        }

        return  null;
    }

    /**
     * 移除Activity
     *
     * @param activity
     */
    public synchronized void removeActivity(Activity activity) {
        if (mActivities.contains(activity)) {
            mActivities.remove(activity);
        }
    }

    /**
     * 结束所有Activity
     */
    public synchronized void clear() {
        for (int i = mActivities.size() - 1; i > -1; i--) {
            Activity activity = mActivities.get(i);
            removeActivity(activity);
            activity.finish();
        }
    }

    /**
     * 结束所有后台Activity
     */
    public synchronized void clearBackActivities() {
        for (int i = mActivities.size() - 2; i > -1; i--) {
            Activity activity = mActivities.get(i);
            removeActivity(activity);
            activity.finish();
        }
    }
}
