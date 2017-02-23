package me.sonam.dev.corelibs;

import android.app.Application;

/**
 * Created by Administrator on 2017/2/23.
 */
public class RxRetrofitApp {
    private static Application application;

    private static String cookie = "";

    public static void init(Application app){
        setApplication(app);
    }

    public static void setCookie(String cookie) {
        RxRetrofitApp.cookie = cookie;
    }

    public static String getCookie() {
        return cookie;
    }

    public static Application getApplication() {
        return application;
    }

    public static void setApplication(Application application) {
        RxRetrofitApp.application = application;
    }

    public static void resetCookie(){
        RxRetrofitApp.cookie = "";
    }
}
