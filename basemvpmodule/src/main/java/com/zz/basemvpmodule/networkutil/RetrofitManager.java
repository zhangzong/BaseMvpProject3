package com.zz.basemvpmodule.networkutil;

import com.zz.basemvpmodule.networkutil.bean.SearchSuggestsResponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by zhangzong on 3/8/2018.
 * please let your network interface implement this interface
 */

public interface RetrofitManager {
    /**
     * get suggest list when do search
     */
    @Headers({"Accept: application/json"})
    @GET("suggest")
    Observable<SearchSuggestsResponse> getSuggests(
            @QueryMap Map<String, String> body);
    /**
     * login to readee bsn service after rakuten loign success
     */
    @Headers({"Accept: application/json","Connection: close"})
    @POST("login/rakuten")
    Observable<SearchSuggestsResponse> login(@Body Map<String, String> body);
}
