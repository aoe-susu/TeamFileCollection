package com.four.entity;

import java.util.Date;

public class Discussion {

    private int id;
    private int sendMemberId;
    private int receiveMemberId;
    private int teamId;
    private String message;
    private Date sendTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSendMemberId() {
        return sendMemberId;
    }

    public void setSendMemberId(int sendMemberId) {
        this.sendMemberId = sendMemberId;
    }

    public int getReceiveMemberId() {
        return receiveMemberId;
    }

    public void setReceiveMemberId(int receiveMemberId) {
        this.receiveMemberId = receiveMemberId;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }
}
