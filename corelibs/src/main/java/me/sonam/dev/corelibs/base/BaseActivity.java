package me.sonam.dev.corelibs.base;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.trello.rxlifecycle.ActivityEvent;
import com.trello.rxlifecycle.FragmentEvent;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;
import me.sonam.dev.corelibs.common.AppManager;
import me.sonam.dev.corelibs.utils.ToastMgr;
import me.sonam.dev.corelibs.views.LoadingDialog;
import rx.Observable;

/**
 * Activity基类，继承此类的Activity需实现
 * Created by Administrator on 2017/2/23.
 */
public abstract class BaseActivity<V extends BaseView, T extends BasePresenter<V>> extends RxAppCompatActivity implements BaseView{

    protected T presenter;
    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        AppManager.getInstance().addActivity(this);
        presenter = createPresenter();
        if (presenter != null) presenter.attachView((V) this);

        ButterKnife.bind(this);
        loadingDialog = new LoadingDialog(this);

        setTranslucentStatusBar();
        init(savedInstanceState);
        if (presenter != null) presenter.onStart();
    }

    /**
     * 指定Activity需加载的布局ID, {@link BaseActivity}BaseActivity
     * 会通过{@link #setContentView}方法来加载布局
     *
     * @return 需加载的布局ID
     */
    protected abstract int getLayoutId();

    /**
     * 初始化方法, 类似OnCreate, 仅在此方法中做初始化操作, findView与事件绑定请使用ButterKnife
     */
    protected abstract void init(Bundle saveInstanceState);

    /**
     * 创建Presenter, 然后通过调用{@link #getPresenter()}来使用生成的Presenter
     * @return Presenter
     */
    protected abstract T createPresenter();

    /**
     * 获取通过{@link #createPresenter()}生成的presenter对象
     * @return Presenter
     */
    public T getPresenter(){
        return presenter;
    }

    public LoadingDialog getLoadingDialog(){
        return loadingDialog;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) presenter.detachView();
        presenter = null;
        AppManager.getInstance().finishActivity(this);
    }

    public void showToast(String s){
        ToastMgr.show(s);
    }

    public void showToast(int r){
        ToastMgr.show(r);
    }

    public String getText(TextView editText){
        return editText.getText().toString().trim();
    }

    public void call(String tel){
        Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + tel));
        startActivity(callIntent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (presenter != null) presenter.onViewStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (presenter != null) presenter.onViewResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (presenter != null) presenter.onViewPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (presenter != null) presenter.onViewStop();
    }

    @Override
    public Context getViewContext() {
        return this;
    }

    @Override
    public void showLoading() {
        loadingDialog.show();
    }

    @Override
    public void hideLoading() {
        loadingDialog.dismiss();
    }

    @Override
    public void showToastMessage(String message) {
        showToast(message);
    }

    @Override
    public void showToastMessage(int message) {
        showToast(message);
    }

    @Override
    public void hideEmptyHint() {

    }

    @Override
    public void showEmptyHint() {

    }

    @Override
    public <T> Observable.Transformer<T, T> bind() {
        return bindToLifecycle();
    }

    @Override
    public <T> Observable.Transformer<T, T> bindUntil(FragmentEvent event) {
        return bindUntil(event);
    }

    @Override
    public <T> Observable.Transformer<T, T> bindUntil(ActivityEvent event) {
        return bindUntil(event);
    }

    /**
     * 设置全屏模式，并将状态栏设置为透明，支持4.4及以上系统
     */
    protected void setTranslucentStatusBar(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                getWindow().setStatusBarColor(Color.TRANSPARENT);

                setFullScreen();
            }
        }
    }


    /**
     * 设置状态栏为浅色模式，状态栏上的图标都会变为深色。仅支持6.0及以上系统
     */
    protected void setLightStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    /**
     * 设置全屏模式
     */
    protected void setFullScreen() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
    }

    /**
     * 设置系统状态颜色，仅支持6.0及以上系统
     */
    protected void setStatusBarColor(int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(color);
        }
    }
}
