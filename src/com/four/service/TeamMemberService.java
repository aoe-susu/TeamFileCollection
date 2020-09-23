package com.four.service;

import com.four.entity.Application;
import com.four.entity.TeamMember;

import java.sql.SQLException;
import java.util.List;

public interface TeamMemberService {
     int getMemberByPwdAndNum(String password,String num);//通过团队密码和成员编号获取成员数量

     List<TeamMember> getMemberListByTeamId(int teamId) throws SQLException;//通过团队id获取成员信息列表

     int deleteMemberById(int id);//通过id删除团员表成员

      int deleteMemberById1(int id);//通过id删除申请表成员

     Boolean existMemberNumber(String num);//是否存在某成员编号的成员

     int addMember1(Application application);//添加成员进入申请表

     int addMember(TeamMember member);//添加成员入成员表


     List<TeamMember> getApplyMemberListByteamId(int teamId) throws SQLException;

     TeamMember getTeamById(int id);//获取成员

     int modifyIconUrlById(int id, String s);
     int modifyIconUrlByNumber(String number,String img);
}
