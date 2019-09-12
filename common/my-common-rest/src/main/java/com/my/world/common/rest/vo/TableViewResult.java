package com.my.world.common.rest.vo;

import com.my.world.common.rest.vo.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: MyWorld
 * @description: 查询表格数据
 * @author: xue chi
 * @create: 2019-08-19 09:05
 **/
public class TableViewResult implements Result {

    private static final long serialVersionUID = -4863573342923445341L;

    private Integer total;

    private Integer pageSize;

    private Integer currentPage;

    private List<?> items;

    public TableViewResult() {
        items = new ArrayList<>();
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public List<?> getItems() {
        return items;
    }

    public void setItems(List<?> items) {
        this.items = items;
    }
}
