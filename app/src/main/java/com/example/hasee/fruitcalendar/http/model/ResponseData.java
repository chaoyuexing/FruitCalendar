package com.example.hasee.fruitcalendar.http.model;

import com.google.gson.Gson;

/**
 * Created by LOVE on 2016/4/18 0018.
 */
public class ResponseData<T> {
    /**
     * code : C10005
     * message : 非法请求
     */
//
//    private String code;
//    private String message;
    private T data;
//
//    public String getCode() {
//        return code;
//    }
//
//    public void setCode(String code) {
//        this.code = code;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }

    public T getData() {

        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


    @Override
    public String toString() {
        return new Gson().toJson(data);
    }
}
