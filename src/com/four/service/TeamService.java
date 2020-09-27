package com.four.service;

import com.four.entity.Team;

public interface TeamService {

    public Boolean existTeamAccount(String account);//是否存在团队账号
    public int addTeam(Team team); //返回值>0表示插入成功
    public Boolean existTeamAccountAndPassword(String account, String password);//用于登录

    public String getPasswordById(int id);//通过id获取密码，用于修改密码

    public int deleteTeamById(int id);//返回值>0表示删除成功

    public Team getTeamById(int id);//通过id获取团队

    public Team getTeamByName(String name);//通过名字获取团队

    public Team getTeamByAccount(String account);//通过账号获取团队

    public int modifyPasswordById(int id, String password);//通过id修改密码

    public int modifyNameById(int id, String Name);//通过id修改名称

    public int modifyIntroductionById(int id, String introduction);//通过id修改简介

    public int modifyIconUrlById(int id, String iconUrl);//通过id修改图标地址
}
