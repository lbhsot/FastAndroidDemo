package me.sonam.dev.fastandroiddemo;

import android.app.Application;

import me.sonam.dev.corelibs.RxRetrofitApp;
import me.sonam.dev.corelibs.common.Configuration;

/**
 * Created by SonamLee on 2017/3/7.
 */
public class App extends Application {
    private static App instance;

    public static App getInstance() {
        return instance;
    }

    private LoginEntity user;

    public LoginEntity getUser() {
        return user;
    }

    public void setUser(LoginEntity user) {
        this.user = user;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        RxRetrofitApp.init(this, Urls.BASE_URL);
        Configuration.enableLoggingNetworkParams();
    }
}
