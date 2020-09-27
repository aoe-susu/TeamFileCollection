package com.four.entity;

import java.util.Date;

public class ApplicationTask {
    private int memberId;
    private int taskId;
    private String number;
    private String name;
    private Date date;
    private String up;
    private int isNot;

    public ApplicationTask() {}

    public ApplicationTask(int memberId, int taskId, String number, String name, Date date, String up, int isNot) {
        this.memberId = memberId;
        this.taskId = taskId;
        this.number = number;
        this.name = name;
        this.date = date;
        this.up = up;
        this.isNot = isNot;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUp() {
        return up;
    }

    public void setUp(String up) {
        this.up = up;
    }

    public int getIsNot() {
        return isNot;
    }

    public void setIsNot(int isNot) {
        this.isNot = isNot;
    }

    @Override
    public String toString() {
        return "ApplicationTask{" +
                "memberId=" + memberId +
                ", taskId=" + taskId +
                ", number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", up='" + up + '\'' +
                ", isNot=" + isNot +
                '}';
    }
}
