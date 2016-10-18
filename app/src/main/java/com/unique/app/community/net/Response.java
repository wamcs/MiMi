package com.unique.app.community.net;

/**
 * Author: Wamcs
 * mail: kaili@hustunique.com
 * Created on 10/18/16.
 */
public final class Response<T> {

    public static final int SUCCESS = 0;

    private int code = SUCCESS;

    private String message;

    private T data;

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Response {" + "\n" +
                " code=" + code +
                ",\n message='" + message + '\'' +
                ",\n data=" + data +
                "\n}";
    }
}
