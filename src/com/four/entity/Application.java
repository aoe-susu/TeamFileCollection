package com.four.entity;

public class Application {
    private int id;
    private String number;
    private String name;
    private int teamId;

    public Application(String number, String name, int teamId) {
        this.number = number;
        this.name = name;
        this.teamId = teamId;
    }

    public Application(int id, String number, String name, int teamId) {
        this.id = id;
        this.number = number;
        this.name = name;
        this.teamId = teamId;
    }

    public Application() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    @Override
    public String toString() {
        return "Application{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", teamId=" + teamId +
                '}';
    }
}
