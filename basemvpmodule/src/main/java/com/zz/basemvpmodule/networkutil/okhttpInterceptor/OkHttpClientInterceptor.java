package com.zz.basemvpmodule.networkutil.okhttpInterceptor;

import android.util.Log;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by zhangzong on 3/8/2018.
 */

public class OkHttpClientInterceptor implements Interceptor {
    private HashMap<String,String> requestHeaderParameter;
    private boolean hasRequestParameter;

    public OkHttpClientInterceptor(HashMap requestHeaderParameter,boolean hasRequestParameter) {
        this.requestHeaderParameter = requestHeaderParameter;
        this.hasRequestParameter = hasRequestParameter;
    }
    public OkHttpClientInterceptor(boolean hasRequestParameter) {
        this.requestHeaderParameter = null;
        this.hasRequestParameter = hasRequestParameter;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request newRequest;
        if(null != requestHeaderParameter && !requestHeaderParameter.isEmpty()){
            Request.Builder builder =request.newBuilder();
            for(HashMap.Entry<String,String> entry4:requestHeaderParameter.entrySet()){
                String key,value;
                key=entry4.getKey();
                value=entry4.getValue();
              builder.addHeader(key, value);
            }
            newRequest = builder.build();
        }else{
            newRequest = request.newBuilder()
                    .build();
            if(hasRequestParameter)
            Log.e("BaseMvp","you want to add parameter to request header ,but you had given a empty parameters  to manager");
        }

        Response response = chain.proceed(newRequest);
        String rawString = response.body().string();
        return response.newBuilder()
                .body(ResponseBody.create(response.body().contentType(), rawString))
                .build();
    }
}
