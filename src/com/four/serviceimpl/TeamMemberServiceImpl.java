package com.four.serviceimpl;

import com.four.dao.ApplicationDao;
import com.four.dao.TaskFileDao;
import com.four.dao.TeamMemberDao;
import com.four.daoimpl.ApplicationDaoImpl;
import com.four.daoimpl.TaskFileDaoImpl;
import com.four.daoimpl.TeamMemberDaoImpl;
import com.four.entity.Application;
import com.four.entity.TaskFile;
import com.four.entity.TeamMember;
import com.four.service.TeamMemberService;

import java.sql.SQLException;
import java.util.List;


public class TeamMemberServiceImpl implements TeamMemberService {
    TeamMemberDao teamMemberDao=new TeamMemberDaoImpl();
    ApplicationDao applicationDao=new ApplicationDaoImpl();
    TaskFileDao taskFileDao=new TaskFileDaoImpl();
    public TeamMemberServiceImpl() {

    }


    @Override
    public TeamMember getMemberByPwdAndNum(String password, String num,int teamId) {
        return teamMemberDao.getMemberByPwdAndNum(password,num,teamId);
    }

    @Override
    public List<TeamMember> getMemberListByTeamId(int teamId) throws SQLException {
        return teamMemberDao.getMemberListByTeamId(teamId);
    }

    @Override
    public int deleteMemberById(int id) {
        return teamMemberDao.deleteMemberById(id);
    }



    @Override
    public Boolean existApplicationNumber(String num) {
        return applicationDao.existApplicationNumber(num)||teamMemberDao.existMemberNumber(num);
    }


    @Override
    public int addMember(TeamMember member) {
        return teamMemberDao.addMember(member);
    }


    @Override
    public TeamMember getTeamMemberById(int id) {
        return teamMemberDao.getTeamMemberById(id);
    }

    @Override
    public int modifyIconUrlById(int id, String s) {
       return teamMemberDao.modifyIconUrlById( id,  s);
    }

    @Override
    public int modifyIconUrlByNumber(String number, String img) {
        return teamMemberDao.modifyIconUrlByNumber(number,img);
    }

//    @Override
//    public List<Application> getTeamMemberList() throws SQLException {
//        return TeamMemberDao.getTeamMemberList();
//    }
    @Override
    public List<TeamMember> getTeamMemberListByTeamId(int teamId) throws SQLException{
        return teamMemberDao.getTeamMemberListByTeamId(teamId);
    }

    @Override
    public int getMemberCount(int teamId) throws SQLException{
        return teamMemberDao.getMemberCount(teamId);
    }


}
