package com.ekt.cms.utils.page;

public class Page {
	
	private int total ;
	private int offset;//偏移量
	private int limit;//分页中一页的数量

	public void setOffset(int offset) {
		this.offset = offset;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit+offset;
	}
	public int getOffset() {
		return offset;
	}

	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	private String order;
}
