package me.sonam.dev.fastandroiddemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import me.sonam.dev.corelibs.base.BaseActivity;

public class MainActivity extends BaseActivity<LoginContract.View, LoginContract.Presenter> implements LoginContract.View{

    public static Intent getLaunchIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(Bundle saveInstanceState) {
        presenter.login("libinghan", "123456a");
    }

    @Override
    protected LoginContract.Presenter createPresenter() {
        return new LoginContract.Presenter();
    }

    @Override
    public void loginComplete() {
        showToast("loginComplete");
        hideLoading();
    }

    @Override
    public void usernameError() {
        showToast("usernameError");
        hideLoading();
    }

    @Override
    public void passwordError() {
        showToast("passwordError");
        hideLoading();
    }

    @Override
    public void loginError(String e) {
        showToast(e);
        hideLoading();
    }
}