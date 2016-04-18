package com.ekt.cms.common.entity;

import java.util.List;

/**
 * 返回页面用的包装类
 * 
 * @author 王岚
 * @param <T>
 *
 */
public class Result {

	private String msg;
	
	private List <Object> list;
	
	private Boolean ok;

	public Boolean getOk() {
		return ok;
	}

	public void setOk(Boolean ok) {
		this.ok = ok;
	}

	public List<Object> getList() {
		return list;
	}

	public void setList(List<Object> list) {
		this.list = list;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	private int result;

	private Object value;
	public static Result getResults(){
		return new Result();
	}

}