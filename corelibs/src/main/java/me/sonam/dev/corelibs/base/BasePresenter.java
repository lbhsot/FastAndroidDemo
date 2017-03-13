package me.sonam.dev.corelibs.base;

import android.content.Context;

import com.trello.rxlifecycle.ActivityEvent;
import com.trello.rxlifecycle.FragmentEvent;

import java.util.List;

import me.sonam.dev.corelibs.api.ApiFactory;
import me.sonam.dev.corelibs.entity.BaseRows;
import rx.Observable;

/**
 * Presenter类基类，Fragment需要使用继承此类的子类，泛型需要传入继承自{@link BaseView}的View类
 *
 * 子类可直接调用通过attachView传递过来的View来操作Activity，无需再声明绑定
 *
 * 如果子类需要自行管理生命周期，请在Activity/Fragment的onCreate中调用{@link #attachView(BaseView)}方法
 * 并一定要在onDestroy中调用{@link #detachView()}，以防止内存溢出
 *
 * Created by Administrator on 2017/2/23.
 */
public abstract class BasePresenter<T extends BaseView>{

    private T view;

    protected PresenterListener listener = null;

    /**
     * 初始化监听器
     */
    public abstract void initListener();

    /**
     * 页面初始化完成调用，此时页面控件已初始化完成
     */
    public abstract void onStart();

    /**
     * View与Presenter绑定时回调，此时页面控件均未初始化
     */
    protected void onViewAttach(){}

    protected void onViewStart(){}

    protected void onViewResume(){}

    protected void onViewPause(){}

    protected void onViewStop(){}

    protected void onViewDetach(){}

    public T getView(){
        return view;
    }

    /**
     * 绑定View与Presenter
     * 初始化model,以及model回调监听器
     * @param view
     */
    public void attachView(T view){
        this.view = view;
        onViewAttach();
        initListener();
        setModel();
    }

    /**
     * Presenter与View解除绑定
     */
    public void detachView(){
        onViewDetach();
        view = null;
    }

    /**
     * 通过resId获取String
     * @param resId
     * @return
     */
    protected String getString(int resId){
        return view.getViewContext().getString(resId);
    }

    /**
     * Presenter与Model绑定
     */
    public abstract void setModel();

    /**
     * 字符串判空
     * @param str
     * @return
     */
    protected boolean stringIsNull(String str){
        return str == null || str.trim().length() <= 0 || str.trim().equals("null") || str.trim().equals("NULL");
    }

    protected Context getContext(){
        return view.getViewContext();
    }

    protected <V>Observable.Transformer<V, V> bindToLifeCycle(){
        return view.bind();
    }

    protected <V>Observable.Transformer<V, V> bindUntilEvent(ActivityEvent event){
        return view.bindUntil(event);
    }

    protected <V>Observable.Transformer<V, V> bindUntilEvent(FragmentEvent event){
        return view.bindUntil(event);
    }

    /**
     * 获取Retrofit Api接口
     * @param clz
     * @param <T>
     * @return
     */
    protected <T>T getApi(Class<T> clz){
        return ApiFactory.getInstance().create(clz);
    }

    protected <T>T getApi(int key, Class<T> clz){
        return ApiFactory.getInstance().create(key, clz);
    }

    protected <T>T getApi(String key, Class<T> clz){
        return ApiFactory.getInstance().create(key, clz);
    }

    /**
     * Presenter与Model间回调接口
     */
    public interface PresenterListener<T extends BaseRows>{
        void setResult(List<T> result);
        void setResponseError(String error);
        void setOtherStr(String str);
        void setError(Throwable e);
    }

}
