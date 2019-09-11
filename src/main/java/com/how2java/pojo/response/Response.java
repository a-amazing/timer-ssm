package com.how2java.pojo.response;

/**
 * @author:wangyi
 * @Date:2019/9/11
 */
public class Response {
    private String retCode;
    private String retMessage;
    private Object data;

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getRetMessage() {
        return retMessage;
    }

    public void setRetMessage(String retMessage) {
        this.retMessage = retMessage;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
