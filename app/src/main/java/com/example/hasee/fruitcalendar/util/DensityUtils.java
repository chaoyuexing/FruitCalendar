package com.example.hasee.fruitcalendar.util;

import android.content.Context;

/**
 * px dp sp单位转换
 * Created by xing on 2017/8/11.
 */

public class DensityUtils {

    /**
     * px转dp
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2dip(Context context,float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * px转sp
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2sp(Context context,float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

}
