package com.four.dao;

import com.four.entity.Team;
import com.four.entity.TeamMember;

import java.util.List;

public interface TeamDao {

    public List<Team> getTeams();
    public int getTotalCounts();
    public Team getTeamById(int id);//通过id获取团队

    public Team getTeamByName(String name);//通过名字获取团队

    public Team getTeamByAccount(String account);//通过账号获取团队

    public Boolean existTeamAccount(String account);//是否存在团队账号

    public Boolean existTeamAccountAndPassword(String account, String password);//用于登录

    public int addTeam(Team team); //返回值>0表示插入成功

    public int deleteTeamById(int id);//返回值>0表示删除成功

    public int modefyPasswordById(int id, String password);//通过id修改密码

    public int modefyBaseInfoById(int id, Team team);//只会修改名称 简介 以及 图标地址

}
