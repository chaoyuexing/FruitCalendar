package com.example.hasee.fruitcalendar.http.request;

/**
 * 返回码
 * Created by LOVE on 2016/4/19 0019.
 */
public class ResponseCode {
    /**
     * 服务器请求，返回成功。
     */
    public static final String SUCCESS = "C10000";
    /**
     * session失效
     */
    public static final String SESSION_EXPIRE = "T10001";

    /**
     * 其他错误
     */
    public static final String OTHER_ERROR = "E10001";
    /**
     * "对不起,您的账号已经在其他客户端登陆,为确保您账号的安全,请重新登陆
     */
    public static final String LOGINE_CONFLICT = "T10002";

    /**
     * 对不起,您所用的版本需要更新才能继续使用!
     */
    public static final String FORCE_UPDATE = "T10003";

    /**
     * 暂无数据
     */
    public static final String DATA_NULL = "C10002";
}
