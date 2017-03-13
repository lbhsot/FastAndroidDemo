package me.sonam.dev.fastandroiddemo;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import me.sonam.dev.corelibs.base.BaseModel;
import me.sonam.dev.corelibs.base.BasePresenter;
import me.sonam.dev.corelibs.base.BaseView;
import me.sonam.dev.corelibs.entity.BaseData;
import me.sonam.dev.corelibs.entity.BaseRows;
import rx.Observable;

/**
 * Created by SonamLee on 2017/3/7.
 */
public class LoginContract {
    /**
     * listener为回传数据给Presenter的监听器
     * 处理数据，在onNext中通过setResult(BaseRows)回传Rows中需要的数据，
     * 通过setResponseError(String)回传服务器返回的用户错误，
     * 通getOtherStr(String)回传不可预期的服务器返回的其他字符串
     */
    public static class Model extends BaseModel{

        @Override
        public void doRequest(Observable observable, Context context, BasePresenter.PresenterListener listener, Class cls) {
            super.doRequest(observable, context, listener, cls);
        }

    }

    /**
     * 实现abstract的初始化监听器方法，设置model方法
     * 登录方法
     */
    public static class Presenter extends BasePresenter<View>{

        /**
         * 在listener中的setResult(BaseRows)中获取model中回传的数据，通知View层更新ui，并存储数据
         */
        @Override
        public void initListener() {
            if (listener == null){
                listener = new PresenterListener() {
//                    @Override
//                    public void setResult(List<BaseRows> result) {
//                        App.getInstance().setUser((LoginEntity)result.get(0));
//                        getView().loginComplete();
//                    }

                    @Override
                    public void setResult(List result) {
                        App.getInstance().setUser((LoginEntity)result.get(0));
                        getView().loginComplete();
                    }

                    @Override
                    public void setResponseError(String error) {
                        getView().loginError(error);
                    }

                    @Override
                    public void setOtherStr(String str) {

                    }

                    @Override
                    public void setError(Throwable e) {
                        getView().showError(e);
                    }
                };
            }
        }

        /**
         * 调用model中的请求数据方法
         * @param username
         * @param password
         */
        public void login(String username, String password){
            if (!checkUserInput(username, password)) return;

            getView().showLoading();

            if (model != null){
                model.doRequest(getApi(ApiService.class).getLogin(username, password), getContext(), listener, LoginEntity.class);
            }else {
                setModel();
                model.doRequest(getApi(ApiService.class).getLogin(username, password), getContext(), listener, LoginEntity.class);
            }
        }

        private boolean checkUserInput(String username, String password) {
//        if (!ValidationUtil.validatePhone(username)) {
//            getView().usernameError();
//            return false;
//        }

            if (stringIsNull(password) || password.length() < 6 || password.length() > 16) {
                getView().passwordError();
                return false;
            }

            return true;
        }

        @Override
        public void onStart() {

        }

        /**
         * 设置model
         */
        private Model model;

        @Override
        public void setModel() {
            this.model = new Model();
        }
    }

    /**
     * 定义View层需要实现的方法
     */
    public interface View extends BaseView{
        void loginComplete();
        void loginError(String error);
        void usernameError();
        void passwordError();
    }
}
