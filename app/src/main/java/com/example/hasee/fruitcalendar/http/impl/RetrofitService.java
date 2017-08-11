package com.example.hasee.fruitcalendar.http.impl;

import java.util.HashMap;

import okhttp3.RequestBody;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

/**
 * 基础请求接口
 * Created by xing on 2016/9/29.
 */
public interface RetrofitService {


    @Multipart
    @POST
    Observable<String> loadData(@Url String url,
                                @Header("headerk") String did,
                                @Header("headers") String sign,
                                @QueryMap HashMap<String, Object> params,
                                @PartMap HashMap<String, RequestBody> files

    );

    @POST
    Observable<String> loadData(@Url String url,
                                @Header("headerk") String did,
                                @Header("headers") String sign,
                                @QueryMap HashMap<String, Object> params

    );

    @GET
    Observable<String> getIP(@Url String ip);

    @GET
    Observable<String> getIp(@Url String ip, @Query("type")String type);

}
