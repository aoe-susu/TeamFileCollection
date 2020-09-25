package com.four.entity;

import java.util.List;

public class PageBean {

    //总记录条数
    private int totalSize;
    //当前页码
    private int currentPage;
    //总页数
    private int pageCount;
    //一页的数据
    private List<Team> teamList;
    //一页记录条数
    private int SIZE = 10;
    //起始页
    private int start;
    //结束页
    private int end;

    public PageBean() {
    }

    public PageBean(int totalSize, int currentPage, int pageCount,
                    List<Team> teamList, int sIZE, int start, int end) {
        super();
        this.totalSize = totalSize;
        this.currentPage = currentPage;
        this.pageCount = pageCount;
        this.teamList = teamList;
        SIZE = sIZE;
        this.start = start;
        this.end = end;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
        if (totalSize % SIZE == 0) {
            this.pageCount = totalSize / SIZE;
        } else {
            this.pageCount = totalSize / SIZE + 1;
        }

    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
        //在这里设置起始页和结束页，如果总页数小于10，就只显示有几页，
        //如果大于10，最开始显示的页码就是1-10
        if(pageCount<=10){
            start=1;
            end=pageCount;
        }else{
            start=currentPage-5;
            end=currentPage+4;
            if(start<=0){
                start=1;
                end=10;
            }
            if(end>pageCount){
                start=pageCount-10;
                end=pageCount;
            }
        }
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;

    }

    public List<Team> getTeamList() {
        return teamList;
    }

    public void setTeamList(List<Team> teamList) {
        this.teamList = teamList;
    }

    public int getSIZE() {
        return SIZE;
    }

    public void setSIZE(int SIZE) {
        this.SIZE = SIZE;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "totalSize=" + totalSize +
                ", currentPage=" + currentPage +
                ", pageCount=" + pageCount +
                ", teamList=" + teamList +
                ", SIZE=" + SIZE +
                ", start=" + start +
                ", end=" + end +
                '}';
    }

}
