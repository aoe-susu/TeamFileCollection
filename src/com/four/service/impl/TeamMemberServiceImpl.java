package com.four.service.impl;

import com.four.dao.TeamMemberDao;
import com.four.daoimpl.TeamMemberDaoImpl;
import com.four.entity.Application;
import com.four.entity.TeamMember;
import com.four.factory.DaoFactory;
import com.four.service.TeamMemberService;

import java.sql.SQLException;
import java.util.List;


public class TeamMemberServiceImpl implements TeamMemberService {
     TeamMemberDao teamMemberDao=new TeamMemberDaoImpl();

    public TeamMemberServiceImpl() {

    }


    @Override
    public int getMemberByPwdAndNum(String password, String num) {
        return teamMemberDao.getMemberByPwdAndNum(password,num);
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
    public int deleteMemberById1(int id) {
        return teamMemberDao.deleteMemberById1(id);
    }

    @Override
    public Boolean existMemberNumber(String num) {
        return teamMemberDao.existMemberNumber(num);
    }

    @Override
    public int addMember1(Application application) {
        return teamMemberDao.addMember1(application);
    }

    @Override
    public int addMember(TeamMember member) {
        return teamMemberDao.addMember(member);
    }

    @Override
    public List<TeamMember> getApplyMemberListByteamId(int teamId) throws SQLException {
        return teamMemberDao.getApplyMemberListByteamId(teamId);
    }

    @Override
    public TeamMember getTeamById(int id) {
        return teamMemberDao.getTeamById(id);
    }

    @Override
    public int modifyIconUrlById(int id, String s) {
       return teamMemberDao.modifyIconUrlById( id,  s);
    }

    @Override
    public int modifyIconUrlByNumber(String number, String img) {
        return teamMemberDao.modifyIconUrlByNumber(number,img);
    }


}
