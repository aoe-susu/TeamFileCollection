package com.four.dao;

import com.four.entity.Application;
import com.four.entity.TeamMember;
import com.four.entity.TaskFile;
import com.four.entity.TeamMember;

import java.sql.SQLException;
import java.util.List;

public interface TeamMemberDao {

    public int getMemberCountByTeamId(int teamId);//通过团队id获取成员数量

    public List<TeamMember> getMemberListByTeamId(int teamId);//通过团队id获取成员信息列表

    public int deleteMemberById(int id);//通过id删除成员

    public int deleteMemberByTeamId(int teamId);//通过团队id删除成员

    public Boolean existMemberNumber(String num);//是否存在某成员编号的成员

    int addMember(TeamMember member);//添加成员

    public List<Application> getTeamMemberListByTeamId(int teamId) throws SQLException;//通过团队id获取申请信息列表

    public List<Application> getTeamMemberList() throws SQLException;

    public List<TaskFile> getTeamMember(int teamId) throws SQLException;

    public List<TaskFile> getTimeByMemberId(int memId) throws SQLException;


    public int getTotalCount(int teamId) throws SQLException;

    public int getUpCount(int teamId) throws SQLException;

}
