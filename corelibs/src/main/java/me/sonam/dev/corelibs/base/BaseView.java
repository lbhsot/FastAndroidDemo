package me.sonam.dev.corelibs.base;

import android.content.Context;

import com.trello.rxlifecycle.ActivityEvent;
import com.trello.rxlifecycle.FragmentEvent;

import rx.Observable;

/**
 * View层基础接口
 * Created by Administrator on 2017/2/23.
 */
public interface BaseView {

    /**
     * 加载中显示加载框
     */
    void showLoading();

    /**
     * 加载完成隐藏加载框
     */
    void hideLoading();

    /**
     * 显示提示消息
     * @param message
     */
    void showToastMessage(String message);

    void showToastMessage(int message);

    /**
     * 隐藏空列表提示
     */
    void hideEmptyHint();

    /**
     * 显示空列表提示
     */
    void showEmptyHint();

    /**
     * 获取Context对象
     * @return
     */
    Context getViewContext();

    <T>Observable.Transformer<T, T> bind();

    <T>Observable.Transformer<T, T> bindUntil(FragmentEvent event);

    <T>Observable.Transformer<T, T> bindUntil(ActivityEvent event);
}
