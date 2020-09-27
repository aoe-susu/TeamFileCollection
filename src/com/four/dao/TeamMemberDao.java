package com.four.dao;

import com.four.entity.Application;
import com.four.entity.TeamMember;

import java.sql.SQLException;
import java.util.List;

public interface TeamMemberDao {
    List<TeamMember> getTeamMemberListByTeamId(int teamId)throws SQLException;;

    //public Boolean existTeamPassword(String password);//通过团队id获取成员数量

    List<TeamMember> getMemberListByTeamId(int teamId) throws SQLException;//通过团队id获取成员信息列表

    int deleteMemberById(int id);//通过id删除成员


    int addMember(TeamMember member);//添加成员进入成员表


    TeamMember getTeamMemberById(int id);

    int modifyIconUrlById(int id, String s);

    int modifyIconUrlByNumber(String number, String img);

    TeamMember getMemberByPwdAndNum(String password, String num,int teamId);

    Boolean existMemberNumber(String num);


    int getMemberCount(int teamId);
}
