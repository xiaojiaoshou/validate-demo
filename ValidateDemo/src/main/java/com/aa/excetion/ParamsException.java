package com.aa.excetion;


/**
 * @description: 自定义 参数校验异常
 * @author  ghx
 * @date  2019/7/15
 */
public class ParamsException extends RuntimeException {

    private String errorCode ;  //异常对应的返回码
    private String msg;  //异常对应的描述信息

    public ParamsException() {
        super();
    }

    public ParamsException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public ParamsException(String errorCode, String msg) {
        super(msg);
        this.errorCode = errorCode;
        this.msg = msg;
    }

    public ParamsException(Throwable t) {
        super(t);
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

