package me.sonam.dev.corelibs;

import android.app.Application;

import com.liulishuo.filedownloader.FileDownloader;

import me.sonam.dev.corelibs.api.ApiFactory;
import me.sonam.dev.corelibs.common.Configuration;
import me.sonam.dev.corelibs.utils.GalleryFinalConfigurator;
import me.sonam.dev.corelibs.utils.PreferencesHelper;
import me.sonam.dev.corelibs.utils.ToastMgr;

/**
 * Created by Administrator on 2017/2/23.
 */
public class RxRetrofitApp {
    private static Application application;

    private static String cookie = "";

    public static void init(Application app, String baseUrl){
        setApplication(app);
        ToastMgr.init(app.getApplicationContext());
        ApiFactory.getInstance().add(baseUrl);
        PreferencesHelper.init(app.getApplicationContext());
        FileDownloader.init(app.getApplicationContext());
        GalleryFinalConfigurator.config(app.getApplicationContext());
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

    private static void setApplication(Application application) {
        RxRetrofitApp.application = application;
    }

    public static void resetCookie(){
        RxRetrofitApp.cookie = "";
    }
}
