package com.ekt.cms.common.entity;

import java.util.Date;
/**
 * 返回页面用的包装类
 * @author 王岚
 *
 */
public final class Result {
	//返回信息提示
   private  String msg;
   //时间参
   private Date date;
   //返回值
   private Integer result ;
   //返回结果
   private Object data;
   
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Integer getResult() {
		return result;
	}
	public void setResult(Integer result) {
		this.result = result;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}

}
