package com.zz.basemvpmodule.networkutil;

import android.content.Context;

import com.zz.basemvpmodule.networkutil.bean.SearchSuggestsResponse;

import java.util.HashMap;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


/**
 * Created by zhangzong on 3/8/2018.
 */

public class RestFactory {
    /**
     * if you want to add some parameter to header ,please use this method to create a manager.
     * */
    public static  <T>T getManagement(Class<T> clazz,Context context, String baseUrl, HashMap<String,String> requestHeaderParameter) {
        return RestClient.getInstance().setContext(context).setBaseUrl(baseUrl).getApi(clazz, baseUrl, requestHeaderParameter);
    }
    /**
     * if you don't need to add any parameter to header ,please use this method to create a manager.
     * */
    public static  <T>T getManagement(Class<T> clazz,Context context, String baseUrl) {
        return RestClient.getInstance().setContext(context).setBaseUrl(baseUrl).getApi(clazz, baseUrl);
    }
    private void demo(Context context,HashMap<String,String > parameter){
        RestFactory.getManagement(RetrofitManager.class,context,"").getSuggests(parameter)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<SearchSuggestsResponse>() {
            @Override
            public void call(SearchSuggestsResponse searchSuggestsResponse) {

            }
        })
    }
}
