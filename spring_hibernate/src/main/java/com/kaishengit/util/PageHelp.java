package com.kaishengit.util;

import java.util.List;

public class PageHelp<T> {

    private Integer total;

    private Integer size = 20;

    private Integer pageTotal;

    private Integer pageNo;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    private List<T> items;

    private Integer start;

    public PageHelp(Integer pageNo,int total){
        this.total = total;

        pageTotal = total/size;

        if (total % size != 0){
            pageTotal++;
        }

        if (pageNo > pageTotal){
            pageNo = pageTotal;
        }
        if (pageNo < 1){
            pageNo = 1;
        }
        this.pageNo = pageNo;

        start = (pageNo -1) * size;
    }
}
