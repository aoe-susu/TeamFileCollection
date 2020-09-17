package com.four.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CollectionTask {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private  int id;
    private String title;
    private String content;
    private int teamId;
    private Date deadline;//截止时间

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "CollectionTask{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", teamId=" + teamId +
                ", deadline=" + deadline +
                '}';
    }
}
