package me.sonam.dev.corelibs.base;

import android.content.Context;

import java.lang.reflect.Type;
import java.util.List;

import me.sonam.dev.corelibs.entity.BaseData;
import me.sonam.dev.corelibs.entity.BaseRows;
import rx.Observable;

/**
 * Created by SonamLee on 2017/3/8.
 */
public interface IBaseModel<T extends BaseData, E> {

    void doRequest(Observable observable, Context context, BasePresenter.PresenterListener listener, Class<E> cls);

    /**
     * 成功后回调方法
     * @param result
     */
    void onNext(T result);

    /**
     * 失败或者错误方法
     * 主动调用，更加灵活
     * @param e
     */
    void onError(Throwable e);

    /**
     * 获取服务器返回的不可知字符串
     * @param str
     */
    void getOtherStr(String str);

//    List jsonAn(List list, BaseData result);
}
