package com.four.entity;

import java.util.Date;

public class ApplicationTask {
    private int id;
    private String number;
    private String name;
    private Date date;
    private String up;
    private int isNot;

    public ApplicationTask() {}
    public ApplicationTask(int id, String number, String name, Date date, String up, int isNot) {
        this.id = id;
        this.number = number;
        this.name = name;
        this.date = date;
        this.up = up;
        this.isNot = isNot;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ApplicationTask{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", up='" + up + '\'' +
                ", isNot=" + isNot +
                '}';
    }
}
