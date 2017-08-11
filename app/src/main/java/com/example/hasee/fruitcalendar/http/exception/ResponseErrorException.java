package com.example.hasee.fruitcalendar.http.exception;

/**
 * Created by xing on 15/12/22.
 * 请求成功,但是服务器提示失败.即code不是success.
 */
public class ResponseErrorException extends Exception {
    private String code;

    public ResponseErrorException() {
    }

    public ResponseErrorException(String detailMessage) {
        super(detailMessage);
    }

    public ResponseErrorException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public ResponseErrorException(Throwable throwable) {
        super(throwable);
    }

    public ResponseErrorException(String code, String detailMessage) {
        super(detailMessage);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
