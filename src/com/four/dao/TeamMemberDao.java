package com.four.dao;

import com.four.entity.TeamMember;

import java.util.List;

public interface TeamMemberDao {

    public int getMemberCountByTeamId(int teamId);//通过团队id获取成员数量

    public List<TeamMember> getMemberListByTeamId(int teamId);//通过团队id获取成员信息列表

    public int deleteMemberById(int id);//通过id删除成员

    public int deleteMemberByTeamId(int teamId);//通过团队id删除成员

    public Boolean existMemberNumber(String num);//是否存在某成员编号的成员

    int addMember(TeamMember member);//添加成员

}
