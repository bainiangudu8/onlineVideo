package com.java.onlinevideo.exception;

/**
 * @author changchen
 * @site
 * @company
 * @create 2021-03-14 20:27
 */
public class CHException extends RuntimeException {

    private Integer code;

    private String msg;

    public CHException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
