package com.zz.basemvpmodule.networkutil;

import android.content.Context;

import com.zz.basemvpmodule.R;
import com.zz.basemvpmodule.customview.CustomDialog;
import com.zz.basemvpmodule.networkutil.bean.SearchSuggestsResponse;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by zhangzong on 3/8/2018.
 */

public class RestFactory {

    private CustomDialog customDialog;

    /**
     * if you want to add some parameter to header ,please use this method to create a manager.
     */
    public static <T> T getManagement(Class<T> clazz, Context context, String baseUrl, HashMap<String, String> requestHeaderParameter) {
        return RestClient.getInstance().setContext(context).setBaseUrl(baseUrl).getApi(clazz, baseUrl, requestHeaderParameter);
    }

    /**
     * if you don't need to add any parameter to header ,please use this method to create a manager.
     */
    public static <T> T getManagement(Class<T> clazz, Context context, String baseUrl) {
        return RestClient.getInstance().setContext(context).setBaseUrl(baseUrl).getApi(clazz, baseUrl);
    }

    private void demo1(Context context, HashMap<String, String> parameter) {
        customDialog = new CustomDialog(context, R.style.CustomDialog);
        RestFactory.getManagement(RetrofitManager.class, context, "").getSuggests(parameter)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).doOnSubscribe(new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {
                if(null != customDialog)
                    customDialog.show();
            }
        }).doFinally(new Action() {
            @Override
            public void run() throws Exception {
                if(null != customDialog)
                    customDialog.hide();
            }
        }).subscribe(new Observer<SearchSuggestsResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(SearchSuggestsResponse searchSuggestsResponse) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    private void demo2(Context context, String url, HashMap<String, String> requestBodyParameter, HashMap<String, String> requestHeaderParameter) {
        RestFactory.getManagement(RetrofitManager.class, context, url, requestHeaderParameter).getSuggests(requestBodyParameter)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<SearchSuggestsResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(SearchSuggestsResponse searchSuggestsResponse) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
