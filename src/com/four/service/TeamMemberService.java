package com.four.service;

import com.four.dao.TeamMemberDao;
import com.four.entity.Application;
import com.four.entity.TaskFile;
import com.four.entity.TeamMember;

import java.sql.SQLException;
import java.util.List;

public interface TeamMemberService {
     TeamMember getMemberByPwdAndNum(String password, String num,int teamId);//通过团队密码和成员编号获取成员数量

     List<TeamMember> getMemberListByTeamId(int teamId) throws SQLException;//通过团队id获取成员信息列表

     int deleteMemberById(int id);//通过id删除团员表成员






     int addMember(TeamMember member);//添加成员入成员表




     TeamMember getTeamMemberById(int id);//获取成员

     int modifyIconUrlById(int id, String s);
     int modifyIconUrlByNumber(String number, String img);

     public Boolean existApplicationNumber(String num);



     //public List<Application> getTeamMemberList() throws SQLException ;
     public List<TeamMember> getTeamMemberListByTeamId(int teamId) throws SQLException;
     public int getMemberCount(int teamId) throws SQLException;



}
