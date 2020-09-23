package com.four.dao;

import com.four.entity.Application;
import com.four.entity.TeamMember;

import java.sql.SQLException;
import java.util.List;

public interface TeamMemberDao {

    //public Boolean existTeamPassword(String password);//通过团队id获取成员数量

    public List<TeamMember> getMemberListByTeamId(int teamId) throws SQLException;//通过团队id获取成员信息列表

    public int deleteMemberById(int id);//通过id删除成员

    public int deleteMemberById1(int id);//通过团队id删除成员

    public Boolean existMemberNumber(String num);//是否存在某成员编号的成员

    int addMember(TeamMember member);//添加成员进入成员表

    int addMember1(Application application);//添加成员进入申请表


    List<TeamMember> getApplyMemberListByteamId(int id) throws SQLException;


    TeamMember getTeamById(int id);

    int modifyIconUrlById(int id, String s);

    int modifyIconUrlByNumber(String number,String img);

    int getMemberByPwdAndNum(String password, String num);
}
