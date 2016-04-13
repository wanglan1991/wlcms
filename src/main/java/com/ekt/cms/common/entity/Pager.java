package com.ekt.cms.common.entity;

import java.util.List;

import com.github.pagehelper.PageInfo;

/**
 * Created by Yaya on 2015/10/4/004.
 */
public class Pager<T> {
    private long total=0;//总数据条数
    private List<T> rows;//具体数据
    public Pager(List<T> list,long l){
    	this.setRows(list);
    	if(list!=null)
    	this.setTotal(l);
    } 
    
    public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
