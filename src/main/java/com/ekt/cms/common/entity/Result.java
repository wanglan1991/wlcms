package com.ekt.cms.common.entity;

import java.util.List;

/**
 * 返回页面用的包装类
 * 
 * @author 王岚
 * @param <T>
 *
 */
public final class Result {
	
	
	public Result(){
		
	}
	
	/**
	 * 
	 * @param msg
	 * @param result
	 * @param value
	 */
	public Result(int result,String msg, Object value) {
		this.msg = msg;
		this.result = result;
		this.value = value;
	}
	
	public Result(int result,String msg) {
		this.msg = msg;
		this.result = result;
	}

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
	
	public static Result getResults(int result,String msg){
		Result r =getResults();
		r.setResult(result);
		r.setMsg(msg);
		return r;
	}
	
	public static Result getResults(int result,String msg,Object value){
		Result r =getResults();
		r.setResult(result);
		r.setMsg(msg);
		r.setValue(value);
		return r;
	}
	
	public static Result getResults(int result,Object value){
		Result r =getResults();
		r.setResult(result);
		r.setValue(value);
		return r;
	}
	
	public static Result getResults(Object value){
		Result r =getResults();
		r.setValue(value);
		r.setResult(1);
		return r;
	}

}