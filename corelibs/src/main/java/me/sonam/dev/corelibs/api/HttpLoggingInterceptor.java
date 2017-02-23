package me.sonam.dev.corelibs.api;

import android.util.Log;

import java.io.IOException;

import me.sonam.dev.corelibs.RxRetrofitApp;
import me.sonam.dev.corelibs.common.Configuration;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okio.Buffer;
import okio.BufferedSource;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by Administrator on 2017/2/23.
 * 拦截器
 * 通过设置{@link Configuration#enableLoggingNetworkParams()}打印网络请求参数与响应结果
 * 当程序logout时或退出程序时，需要执行{@link RxRetrofitApp#resetCookie()}方法来清除Cookie
 */
public class HttpLoggingInterceptor implements Interceptor {
    public static final String TAG = "HttpLogging";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        long t1 = System.nanoTime();

        Request.Builder builder = request.newBuilder();
        builder = builder.addHeader("Cookie", RxRetrofitApp.getCookie());
        if (!RxRetrofitApp.getCookie().equals("")){
            builder = builder.addHeader("Cookie", RxRetrofitApp.getCookie());
        }
        request = builder.build();
        Buffer buffer = new Buffer();
        if (request.body() != null)
            request.body().writeTo(buffer);

        if (Configuration.isShowNetworkParams()){
            Log.e(TAG, String.format("Sending request %s on %s%n%sRequest Params: %s", request.url(), chain.connection(), request.headers(), buffer.clone().readUtf8()));
        }
        buffer.close();

        Response response = chain.proceed(request);
        long t2 = System.nanoTime();

        BufferedSource source = response.body().source();
        source.request(Long.MAX_VALUE);
        buffer = source.buffer().clone();
        if (Configuration.isShowNetworkParams()){
            Log.e(TAG, String.format("Received response for %s in %.1fms%n%sResponse Json: %s",
                    response.request().url(), (t2 - t1)/1e6d, response.headers(),
                    buffer.readUtf8()));
        }
        buffer.close();
        if (response.header("Set-Cookie") != null){
            final StringBuffer cookieBuffer = new StringBuffer();
            Observable.from(response.headers("Set-Cookie"))
                    .map(new Func1<String, String>() {
                        @Override
                        public String call(String s) {
                            String[] cookieArr = s.split(";");
                            return cookieArr[0];
                        }
                    }).subscribe(new Action1<String>() {
                        @Override
                        public void call(String cookie) {
                            cookieBuffer.append(cookie).append(";");
                        }
                    });
            if (RxRetrofitApp.getCookie().equals("")){
                String savedCookie = cookieBuffer.toString();
                RxRetrofitApp.setCookie(savedCookie);
            }
        }
        return response;
    }
}
