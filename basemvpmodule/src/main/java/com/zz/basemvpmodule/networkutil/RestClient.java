package com.zz.basemvpmodule.networkutil;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zz.basemvpmodule.networkutil.okhttpInterceptor.LoggingInterceptor;
import com.zz.basemvpmodule.networkutil.okhttpInterceptor.OkHttpClientInterceptor;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zhangzong on 3/8/2018.
 */

public class RestClient {
    private static RestClient instance = null;
    private Retrofit mRetrofit;
    private OkHttpClient mOkHttpClient;
    protected Context context = null;
    private String baseUrl = "";

    private RestClient() {
    }

    public static RestClient getInstance() {
        if (instance == null) {
            synchronized (RestClient.class) {
                if (instance == null) {
                    instance = new RestClient();
                }
            }
        }
        return instance;
    }

    public RestClient setContext(Context context) {
        this.context = context;
        return this;
    }

    public RestClient setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    /**
     * connect timeout (s)
     *
     * @return
     */
    protected int getTimeOut() {
        return 20;
    }

    public long getReadTimeOut() {
        return 60;
    }

    public long getWriteTimeOut() {
        return 60;
    }

    /**
     * @return
     */
    protected int getCacheSize() {
        //10 * 1024 * 1024 = 10M
        return 10485760;
    }

    /**
     * network request cache fileName
     *
     * @return
     */
    protected String getCacheFileName() {
        return "responses";
    }

    /**
     * @return Cache Settings
     */
    private Cache getCache() {
        //setup cache
        File httpCacheDirectory = new File(context.getCacheDir(), getCacheFileName());
        return new Cache(httpCacheDirectory, getCacheSize());
    }

    /**
     * log Interceptor
     *
     * @return log Interceptor
     */
    protected Interceptor getLoggingInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return loggingInterceptor;
    }

    private static class TrustAllHostnameVerifier implements HostnameVerifier {

        @Override
        public boolean verify(String s, SSLSession sslSession) {
            return true;
        }
    }

    protected Converter.Factory getConverter() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        return GsonConverterFactory.create(gson);
    }

    protected CallAdapter.Factory getCallAdapter() {
        return RxJavaCallAdapterFactory.create();
    }

    public <T> T getApi(Class<T> clazz, String baseUrl, HashMap<String, String> requestHeaderParameter) {
        init(baseUrl, requestHeaderParameter);
        Object result = null;
        String name = clazz.getName();
        result = mRetrofit.create(clazz);
        return (T) result;
    }

    public <T> T getApi(Class<T> clazz, String baseUrl) {
        init(baseUrl);
        Object result = null;
        String name = clazz.getName();
        result = mRetrofit.create(clazz);
        return (T) result;
    }

    /**
     * please set baseUrl at first
     */
    private void init(String baseUrl, HashMap<String, String> requestHeaderParameter) {
        if (context == null) {
            throw new IllegalStateException("contextとbaseUrlセットしてください。context");
        }
        if (TextUtils.isEmpty(baseUrl)) {
            throw new IllegalStateException("contextとbaseUrlセットしてください。baseurl");
        }
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        mOkHttpClient = builder.addInterceptor(new OkHttpClientInterceptor(requestHeaderParameter, true))
                .retryOnConnectionFailure(true)
                .connectTimeout(getTimeOut(), TimeUnit.SECONDS)
                .readTimeout(getReadTimeOut(), TimeUnit.SECONDS)
                .writeTimeout(getWriteTimeOut(), TimeUnit.SECONDS)
                .addNetworkInterceptor(new LoggingInterceptor())
                .cache(getCache())
                .protocols(Arrays.asList(Protocol.HTTP_1_1))
                .addInterceptor(getLoggingInterceptor())
                .hostnameVerifier(new TrustAllHostnameVerifier())
                .build();
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder().client(mOkHttpClient).baseUrl(baseUrl);
        Converter.Factory converter = getConverter();
        if (converter != null) {
            retrofitBuilder.addConverterFactory(converter);
        }
//        retrofitBuilder.addConverterFactory(GsonConverterFactory.create(createGson()));
        CallAdapter.Factory callAdapter = getCallAdapter();
        if (callAdapter != null) {
            retrofitBuilder.addCallAdapterFactory(callAdapter);
        }
//        retrofitBuilder.addCallAdapterFactory(RxJavaCallAdapterFactory.create());
        mRetrofit = retrofitBuilder.build();
    }

    /**
     * please set baseUrl at first
     */
    private void init(String baseUrl) {
        if (context == null) {
            throw new IllegalStateException("contextとbaseUrlセットしてください。context");
        }
        if (TextUtils.isEmpty(baseUrl)) {
            throw new IllegalStateException("contextとbaseUrlセットしてください。baseurl");
        }
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        mOkHttpClient = builder.addInterceptor(new OkHttpClientInterceptor(false))
                .retryOnConnectionFailure(true)
                .connectTimeout(getTimeOut(), TimeUnit.SECONDS)
                .readTimeout(getReadTimeOut(), TimeUnit.SECONDS)
                .writeTimeout(getWriteTimeOut(), TimeUnit.SECONDS)
                .addNetworkInterceptor(new LoggingInterceptor())
                .cache(getCache())
                .protocols(Arrays.asList(Protocol.HTTP_1_1))
                .addInterceptor(getLoggingInterceptor())
                .hostnameVerifier(new TrustAllHostnameVerifier())
                .build();
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder().client(mOkHttpClient).baseUrl(baseUrl);
        Converter.Factory converter = getConverter();
        if (converter != null) {
            retrofitBuilder.addConverterFactory(converter);
        }
//        retrofitBuilder.addConverterFactory(GsonConverterFactory.create(createGson()));
        CallAdapter.Factory callAdapter = getCallAdapter();
        if (callAdapter != null) {
            retrofitBuilder.addCallAdapterFactory(callAdapter);
        }
//        retrofitBuilder.addCallAdapterFactory(RxJavaCallAdapterFactory.create());
        mRetrofit = retrofitBuilder.build();
    }
}
