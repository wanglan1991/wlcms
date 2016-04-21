package com.ekt.cms.utils.pageHelper;

import com.github.pagehelper.PageHelper;

public class PageContext {
	
	 // 定义两个threadLocal变量：pageNum和pageSize
    private static ThreadLocal<Integer> pageNum = new ThreadLocal<Integer>();    // 保存第几页
    private static ThreadLocal<Integer> pageSize = new ThreadLocal<Integer>();    // 保存每页记录条数
    private int total = 0;
	private int offset = 0;//偏移量
	private int limit = 10;//分页中一页的数量



	/*
     * pageNum ：get、set、remove
     */
    public static int getPageNum() {
        Integer pn = pageNum.get();
        if (pn == null) {
            return 0;
        }
        return pn;
    }

    public static void setPageNum(int pageNumValue) {
        pageNum.set(pageNumValue);
    }

    public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public static void removePageNum() {
        pageNum.remove();
    }

    /*
     * pageSize ：get、set、remove
     */
    public static int getPageSize() {
        Integer ps = pageSize.get();
        if (ps == null) {
            return 0;
        }
        return ps;
    }

    public static void setPageSize(int pageSizeValue) {
        pageSize.set(pageSizeValue);
    }

    public static void removePageSize() {
        pageSize.remove();
    }
    
    /**
     * 分页需调用的方法
     */
    public void paging(){
    	
    	PageHelper.startPage(getOffset()/getLimit()+1,(getOffset()/getLimit()+1)==1?10:getOffset());
    }
    
    
    
    
    
    
    
}
