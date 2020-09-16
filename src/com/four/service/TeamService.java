package com.four.service;

import com.four.entity.Team;

public interface TeamService {

    public Boolean existTeamAccount(String account);//是否存在团队账号
    public int addTeam(Team team); //返回值>0表示插入成功
    public Boolean existTeamAccountAndPassword(String account, String password);//用于登录
    public int deleteTeamById(int id);//返回值>0表示删除成功

    public Team getTeamById(int id);//通过id获取团队

    public int modefyNameById(int id, String Name);//通过id修改名称

    public int modefyIntroductionById(int id, String introduction);//通过id修改简介

    public int modefyIconUrlById(int id, String iconUrl);//通过id修改图标地址
}
