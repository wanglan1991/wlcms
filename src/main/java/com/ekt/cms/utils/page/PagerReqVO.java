package com.ekt.cms.utils.page;

/**
 * Created by sxjun on 2015/10/4/004.
 */
public class PagerReqVO {
    private int limit;//分页中一页的数量
    private int offset;//偏移量
    private String order;

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

	public int getPageNo() {
		return offset/limit+1;
	}
    
}
