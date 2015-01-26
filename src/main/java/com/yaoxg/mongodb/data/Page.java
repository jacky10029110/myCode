package com.yaoxg.mongodb.data;

import java.util.List;

/**
 * 分页对象
 * 
 * @Title:
 * @Description:
 * @Author:Administrator
 * @Since:2014年9月19日
 * @Version:1.1.0
 */
public class Page<T> {
    /**
     * 总数
     */
    private long total;

    /**
     * 每页展示数量 默认为10
     */
    private int pageSize = 10;

    /**
     * 当前查询页数
     */
    private int pageNumber;

    /**
     * 查询记录列表
     */
    private List<T> rows;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
