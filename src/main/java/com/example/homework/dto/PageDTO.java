package com.example.homework.dto;

import java.util.List;

public class PageDTO {

    private List<TieDTO> tie;
    private boolean showNext;
    private boolean showLast;

    public List<TieDTO> getTie() {
        return tie;
    }

    public void setTie(List<TieDTO> tie) {
        this.tie = tie;
    }

    public boolean isShowNext() {
        return showNext;
    }

    public void setShowNext(boolean showNext) {
        this.showNext = showNext;
    }

    public boolean isShowLast() {
        return showLast;
    }

    public void setShowLast(boolean showLast) {
        this.showLast = showLast;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    //当前页
    private Integer page;

    private Integer totalPage;

    public void setPageNation(Integer totalCount, Integer page, Integer size) {

        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }

        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }

        this.page = page;

        //是否展示上一页
        if (page == 1) {
            showLast = false;
        } else {
            showNext = true;
        }

        //是否展示下一页
        if (page == totalPage) {
            showLast = true;
        } else {
            showNext = false;
        }

    }
}
