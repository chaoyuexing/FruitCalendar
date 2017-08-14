package com.example.hasee.fruitcalendar.util;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.hasee.fruitcalendar.http.request.RequsetUrlConstant;

/**
 * 图片加载工具类
 * Created by xing on 2017/8/11.
 */

public class LoadImageUtils {

    /**
     * 图片加载 用的是Glide
     * @param context 上下文
     * @param url 图片url地址  接口返回的url 需要加上Baseurl
     * @param view  显示图片的VIew
     */
    public static void LoadImage(Context context, String url, View view) {
        Glide.with(context).load(RequsetUrlConstant.BASE_URL + url).into((ImageView) view);
    }
}