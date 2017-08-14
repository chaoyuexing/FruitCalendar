package com.example.hasee.fruitcalendar.http.impl;

import android.content.Context;

import com.example.hasee.fruitcalendar.bean.BannerBean;
import com.example.hasee.fruitcalendar.http.common.StringUtils;
import com.example.hasee.fruitcalendar.http.model.ResponseData;
import com.example.hasee.fruitcalendar.http.request.RetrofitClient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


/**
 * 接口实现
 * Created by Administrator on 2016/9/29.
 */

public class HttpImpl {

    private Context context;
    private RetrofitService mRetrofitService;

    public HttpImpl(Context context) {
        this.context = context;
        mRetrofitService = RetrofitClient.getInstance().create(RetrofitService.class);
    }



    /**
     *  banner
     * @param url
     * @return
     */
    public Observable<ResponseData<ArrayList<BannerBean>>> banner(String url) {
        return getIP(url, null, new TypeToken<ArrayList<BannerBean>>(){
        }.getType()) ;
    }

    /**
     * 基础数据请求
     *
     * @param url
     * @return
     */
    private <T> Observable<ResponseData<T>> getIP(String url ,String type,final Type typeToken) {
        //Rx被观测者
        Observable<String> observable;
        if (StringUtils.isEmpty(type))
            observable = mRetrofitService.getIP(url);
        else
            observable = mRetrofitService.getIp(url,type);
        return observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Func1<String, Observable<ResponseData<T>>>() {
                    @Override
                    public Observable<ResponseData<T>> call(String strResponse) {
                        ResponseData<T> resultResponseData = new ResponseData<T>();
                        if (null != strResponse) {
                            //创建对象,承载数据.
                            T s = new Gson().fromJson(strResponse, typeToken);
                            resultResponseData.setData(s);
                        }
                        return Observable.just(resultResponseData);
                    }
                })
                //错误处理，防止错误返回导致数据链断裂
                .onErrorReturn(new Func1<Throwable, ResponseData<T>>() {
                    @Override
                    public ResponseData<T> call(Throwable throwable) {
                        throwable.printStackTrace();
                        ResponseData<T> errorData = new ResponseData<>();
                        return errorData;
                    }
                });

    }


}
