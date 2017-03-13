package me.sonam.dev.corelibs.base;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.sonam.dev.corelibs.entity.BaseData;
import me.sonam.dev.corelibs.entity.BaseRows;
import me.sonam.dev.corelibs.exception.RetryWhenNetworkException;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by SonamLee on 2017/3/8.
 */
public class BaseModel<P extends BaseRows, E> implements IBaseModel{

    private BasePresenter.PresenterListener listener = null;
    private Class<E> cls = null;

    /**
     * 调用过ApiFactory后，从ApiService中获取Observable，请求网络
     * @param observable
     * @param context
     * @param listener
     */
    @Override
    public void doRequest(Observable observable, Context context, final BasePresenter.PresenterListener listener, Class cls) {
        this.cls = cls;
        this.listener = listener;
        observable.retryWhen(new RetryWhenNetworkException())
                .compose(((BaseActivity) context).<BaseData>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<BaseData>() {
                    @Override
                    public void call(BaseData baseData) {
                        onNext(baseData);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        onError(throwable);
                    }
                });
    }

    @Override
    public void onNext(BaseData result) {
        if (result.isSucceed()){
            List list = null;
            try {
                list = fromJsonArray(new Gson().toJson(result.getRows()), cls);
            } catch (Exception e) {
                e.printStackTrace();
                onError(e);
            }
            listener.setResult(list);
        }else {
            listener.setResponseError(result.getMessage() + "");
        }
    }

    @Override
    public void onError(Throwable e) {
        listener.setError(e);
    }

    @Override
    public void getOtherStr(String str) {
        listener.setOtherStr(str);
    }

    public static <E> List<E> fromJsonArray(String json, Class<E> clazz) throws Exception {
        List<E> lst =  new ArrayList<E>();
        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
        for(final JsonElement elem : array){
            lst.add(new Gson().fromJson(elem, clazz));
        }
        return lst;
    }

    public static <T> List<T> getListFromJSON(String json, Class<T[]> type) {
        T[] list = new Gson().fromJson(json, type);
        return Arrays.asList(list);
    }

    public static <T> List<T> fromJson2Array(BaseData result){
        Gson gson = new Gson();
        List<T> list  = new Gson().fromJson(gson.toJson(result.getRows()),new TypeToken<List<T>>(){}.getType());
        gson = null;
        return list;
    }
}
