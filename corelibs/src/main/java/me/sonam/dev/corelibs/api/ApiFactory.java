package me.sonam.dev.corelibs.api;

import android.text.TextUtils;

import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.bind.TypeAdapters;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.HashMap;

import me.sonam.dev.corelibs.common.Configuration;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 用于retrofit创建网络请求接口的Api实现，通过设置{@link Configuration#enableLoggingNetworkParams()}来启用请求参数和响应结果
 * 通过{@link #add(String)}或{@link #add(String, String)}来添加不同的BaseUrl，并通过create方法创建Api实现
 *
 * ApiFactory.getInstance().add(baseUrl)
 * ApiFactory.getInstance().create(ProductApi.class)
 *
 * ApiFactory.getInstance().add("dev", baseUrl);
 * ApiFactory.getInstance().create("dev", ApProductApi.class)
 * ApiFactory.getInstance().create(1, ApProductApi.class)
 *
 * Created by Administrator on 2017/2/23.
 */
public class ApiFactory {

    private static ApiFactory instance;
    private HashMap<String, Retrofit> retrofitMap = new HashMap<String, Retrofit>();

    /**
     * 获取单例
     * @return
     */
    public static ApiFactory getInstance(){
        if (instance == null){
            synchronized (ApiFactory.class){
                if (instance == null){
                    instance = new ApiFactory();
                }
            }
        }
        return instance;
    }

    /**
     * 新增一个retrofit实例，可以通过{@link #create(Class)}或{@link #create(int, Class)}方法获取Api实现
     * key默认自增
     * @param baseUrl
     */
    public void add(String baseUrl){
        retrofitMap.put(retrofitMap.size() + "", createRetrofit(baseUrl));
    }

    /**
     * 新增一个retrofit实例，可以通过{@link #create(String, Class)} 方法获取Api实现
     * @param key
     * @param baseUrl
     */
    public void add(String key, String baseUrl){
        retrofitMap.put(key, createRetrofit(baseUrl));
    }

    /**
     * 获取Api实现，默认通过第一个Retrofit实例创建
     * @param clz
     * @param <T>
     * @return
     */
    public <T>T create(Class<T> clz){
        checkRetrofitMap();
        return retrofitMap.get("0").create(clz);
    }

    /**
     * 通过key获取Api实现
     * @param key
     * @param clz
     * @param <T>
     * @return
     */
    public <T>T create(int key, Class<T> clz){
        checkRetrofitMap();
        return retrofitMap.get(key + "").create(clz);
    }

    /**
     * 通过key获取Api实现
     * @param key
     * @param clz
     * @param <T>
     * @return
     */
    public <T>T create(String key, Class<T> clz){
        checkRetrofitMap();
        return retrofitMap.get(key).create(clz);
    }

    private void checkRetrofitMap(){
        if (retrofitMap.size() < 0){
            throw new IllegalArgumentException("Please add a Retrofit instance.");
        }
    }

    private Retrofit createRetrofit(String baseUrl){
        if (baseUrl == null || baseUrl.length() <= 0){
            throw new IllegalArgumentException("BaseUrl cannot be null!");
        }

        Retrofit.Builder builder = new Retrofit.Builder();
        GsonBuilder gsonBuilder = new GsonBuilder();

        //Gson double 类型转换，避免空字符串解析出错
        final TypeAdapter<Number> DOUBLE = new TypeAdapter<Number>() {
            @Override
            public void write(JsonWriter out, Number value) throws IOException {
                out.value(value);
            }

            @Override
            public Number read(JsonReader in) throws IOException {
                if (in.peek() == JsonToken.NULL){
                    in.nextNull();
                    return null;
                }
                if (in.peek() == JsonToken.STRING){
                    String tmp = in.nextString();
                    if (TextUtils.isEmpty(tmp))
                        tmp = "0";
                    return Double.parseDouble(tmp);
                }
                return in.nextDouble();
            }
        };

        final TypeAdapter<Number> LONG = new TypeAdapter<Number>() {
            @Override
            public void write(JsonWriter out, Number value) throws IOException {
                out.value(value);
            }

            @Override
            public Number read(JsonReader in) throws IOException {
                if (in.peek() == JsonToken.NULL){
                    in.nextNull();
                    return null;
                }
                if (in.peek() == JsonToken.STRING){
                    String tmp = in.nextString();
                    if (TextUtils.isEmpty(tmp))
                        tmp = "0";
                    return Long.parseLong(tmp);
                }
                return in.nextLong();
            }
        };

        final TypeAdapter<Number> INT = new TypeAdapter<Number>() {
            @Override
            public void write(JsonWriter out, Number value) throws IOException {
                out.value(value);
            }

            @Override
            public Number read(JsonReader in) throws IOException {
                if (in.peek() == JsonToken.NULL){
                    in.nextNull();
                    return null;
                }
                if (in.peek() == JsonToken.STRING){
                    String tmp = in.nextString();
                    if (TextUtils.isEmpty(tmp))
                        tmp = "0";
                    return Integer.parseInt(tmp);
                }
                return in.nextInt();
            }
        };

        gsonBuilder.registerTypeAdapterFactory(TypeAdapters.newFactory(double.class, Double.class, DOUBLE));
        gsonBuilder.registerTypeAdapterFactory(TypeAdapters.newFactory(long.class, Long.class, LONG));
        gsonBuilder.registerTypeAdapterFactory(TypeAdapters.newFactory(int.class, Integer.class, INT));

        builder.baseUrl(baseUrl)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()));

        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();

        clientBuilder.addInterceptor(new HttpLoggingInterceptor());

        builder.client(clientBuilder.build());
        return builder.build();
    }

}