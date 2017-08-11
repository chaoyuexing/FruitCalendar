package com.example.hasee.fruitcalendar.http.request;


import com.example.hasee.fruitcalendar.http.factory.StringConverterFactory;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;


/**
 * 网络请求客户端，在这里可以全局配置okhtt属性
 * Created by xing on 2016/3/24.
 */
public class RetrofitClient {

      private static String BASE_URL = "http://www.erdange.com";



    private static final RetrofitClient instance = new RetrofitClient();

    public static RetrofitClient getInstance() {
        return instance;
    }

    private Retrofit retrofit;

    public RetrofitClient() {
        createRetrofit();
    }

    private void createRetrofit() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                })
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(StringConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }


    public <T> T create(Class<?> clazz) {
        return (T) retrofit.create(clazz);
    }

}
