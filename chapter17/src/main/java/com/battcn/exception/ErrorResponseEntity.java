package com.battcn.exception;

/**
 * @author Levin
 * @since 2018/6/1 0001
 */
public class ErrorResponseEntity {

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public ErrorResponseEntity(){}
    public ErrorResponseEntity(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
