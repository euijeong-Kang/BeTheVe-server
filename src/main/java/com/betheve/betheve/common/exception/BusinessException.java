package com.betheve.betheve.common.exception;

public class BusinessException extends RuntimeException{

    private Object data;

    public Object getData() {
        return this.data;
    }

    public BusinessException() {

    }

    public BusinessException(String message){
        super(message);
    }

    public BusinessException(String message, Object data){
        super(message);
        this.data = data;
    }

    public BusinessException(String message, Object data, Throwable cause) {
        super(message, cause);
        this.data = data;
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException (String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
