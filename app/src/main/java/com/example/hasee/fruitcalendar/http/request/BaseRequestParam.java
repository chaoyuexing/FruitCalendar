package com.example.hasee.fruitcalendar.http.request;




import java.util.HashMap;




/**
 * 基础公共参数
 * Created by LOVE on 2016/4/19 0019.
 */
public class BaseRequestParam {


    private HashMap<String, Object> baseParam;



    public BaseRequestParam() {
        baseParam = new HashMap<>();

    }


    public BaseRequestParam putAll(HashMap<String, Object> paramsMap) {

        baseParam.putAll(paramsMap);

        return this;
    }


//    public BaseRequestParam setStartIndex(String mstartIndex) {
//        baseParam.put(startIndex, mstartIndex);
//        return this;
//    }
//
//    public BaseRequestParam setPageSize(String mpageSize) {
//        baseParam.put(pageSize, mpageSize);
//        return this;
//    }


    public BaseRequestParam put(String key, Object value) {
        baseParam.put(key, value);
        return this;
    }




    public HashMap<String, Object> build() {
        return baseParam;
    }




}




