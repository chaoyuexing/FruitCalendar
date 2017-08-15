package com.example.hasee.fruitcalendar.http.impl;

import android.content.Context;

import com.example.hasee.fruitcalendar.bean.BannerBean;
import com.example.hasee.fruitcalendar.bean.CategoryTypeBean;
import com.example.hasee.fruitcalendar.bean.GoodsBean;
import com.example.hasee.fruitcalendar.bean.GoodsTypeBean;
import com.example.hasee.fruitcalendar.bean.RecommendBean;
import com.example.hasee.fruitcalendar.http.model.ResponseData;
import com.example.hasee.fruitcalendar.http.request.BaseRequestParam;
import com.example.hasee.fruitcalendar.http.request.RetrofitClient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

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
     * 获取首页列表的类别
     * @param url
     * @return
     */
    public Observable<ResponseData<ArrayList<GoodsTypeBean>>> goodsTypee(String url) {
        return getIP(url, null, new TypeToken<ArrayList<GoodsTypeBean>>(){
        }.getType()) ;
    }

    /**
     * 获取首页列表的详细信息
     * @param url
     * @param id 此参数就是获取列别类别
     * @return
     */
    public Observable<ResponseData<ArrayList<RecommendBean>>> recommend(String url , String id) {
        BaseRequestParam param = new BaseRequestParam();
        param.put("type",id);
        return getIP(url, param.build(), new TypeToken<ArrayList<RecommendBean>>(){
        }.getType()) ;
    }

    /**
     * 获取分类的水果类型
     * @param url
     * @return
     */
    public Observable<ResponseData<ArrayList<GoodsBean>>> categoryGoods(String url ,String pageindex,String type) {
        BaseRequestParam param = new BaseRequestParam();
        param.put("pageindex",pageindex);
        param.put("type",type);
        return getIP(url, param.build(), new TypeToken<ArrayList<GoodsBean>>(){
        }.getType()) ;
    }


    /**
     * 获取分类的商品
     * @param url
     * @return
     */
    public Observable<ResponseData<ArrayList<CategoryTypeBean>>> categoryType(String url) {
        return getIP(url, null, new TypeToken<ArrayList<CategoryTypeBean>>(){
        }.getType()) ;
    }

    /**
     * 基础数据请求
     *
     * @param url
     * @return
     */
    private <T> Observable<ResponseData<T>> getIP(String url , HashMap<String, Object> params, final Type typeToken) {
        //Rx被观测者
        Observable<String> observable;
        if (params == null || params.size() == 0 )
            observable = mRetrofitService.getIP(url);
        else
            observable = mRetrofitService.getIp(url,params);
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
