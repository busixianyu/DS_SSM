package cn.ds.model;

import cn.ds.context.SystemContext;
import com.github.pagehelper.PageInfo;

import java.util.List;

public class Datas {

    private List rows;

    private int page;

    private int limit;

    private int pageSize;

    private long total;

    public Datas(List rows, int page,int limit,int pageSize,long total) {
        this.rows = rows;
        this.page = page;
        this.pageSize = pageSize;
        this.total = total;
        this.limit = limit;
    }

    public Datas(List rows,PageInfo pageInfo) {
        this.rows = rows;
        this.page = SystemContext.getOffset();
        this.pageSize = pageInfo.getPages();
        this.total = pageInfo.getTotal();
        this.limit = pageInfo.getPageSize();
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

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

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
