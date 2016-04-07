package com.ekt.cms.index.entity;

/**
 * @author sxjun
 * @time 2015/8/27 13:32
 */
public class ResultVO {

    public ResultVO(boolean ok) {
        this.ok = ok;
    }

    public ResultVO() {
    }

    private boolean ok;

    private String msg;

    private String url;

    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
