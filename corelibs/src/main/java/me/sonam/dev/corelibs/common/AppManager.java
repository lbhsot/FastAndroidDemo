package me.sonam.dev.corelibs.common;

import android.app.Activity;

import java.util.Stack;

/**
 * 模拟Android Activity栈，方便管理Activity
 * Created by Administrator on 2017/2/23.
 */
public final class AppManager {

    private static Stack<Activity> activityStack;

    private static AppManager instance;

    private AppManager(){}

    public static AppManager getInstance(){
        if (instance == null){
            instance = new AppManager();
        }
        return instance;
    }

    public void addActivity(Activity activity){
        if (activityStack == null){
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }

    /**
     * 获取栈堆中最后一个Activity，当前Activity
     * @return
     */
    public Activity currentActivity(){
        return activityStack.lastElement();
    }

    /**
     * 结束当前Activity
     */
    public void finishActivity(){
        finishActivity(activityStack.lastElement());
    }

    /**
     * 结束指定Activity
     * @param activity
     */
    public void finishActivity(Activity activity){
        if (activity != null){
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束指定类名的Activity
     * @param cls
     */
    public void finishActivity(Class<?> cls){
        Activity activity = null;
        for (Activity a : activityStack){
            if (a.getClass().equals(cls)){
                activity = a;
                break;
            }
        }
        if (activity != null){
            finishActivity(activity);
        }
    }

    /**
     * 获取指定类名的Activity
     * @param cls
     * @return
     */
    public Activity getActivity(Class<?> cls){
        if (activityStack != null && activityStack.size() != 0){
            int size = activityStack.size();
            for (int i = 0; i < size; i++){
                Activity activity = activityStack.get(i);
                if (activity.getClass().equals(cls)){
                    return activity;
                }
            }
        }
        return null;
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity(){
        for (int i = 0; i < activityStack.size(); i++){
            if (activityStack.get(i) != null){
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 退出应用程序
     */
    public void appExit(){
        finishAllActivity();
//        System.exit(0);
    }
}
