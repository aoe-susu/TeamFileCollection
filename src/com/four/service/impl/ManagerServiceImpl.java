package com.four.service.impl;

import com.four.dao.TeamDao;
import com.four.dao.TeamMemberDao;
import com.four.daoimpl.TeamDaoImpl;
import com.four.daoimpl.TeamMemberDaoImpl;
import com.four.entity.Team;
import com.four.entity.TeamMember;
import com.four.service.ManagerService;

import java.util.List;

public class ManagerServiceImpl implements ManagerService {

    private TeamDao teamDao = new TeamDaoImpl();
    private TeamMemberDao teamMemberDao = new TeamMemberDaoImpl();
    @Override
    public List<Team> getTeams() {
       return teamDao.getTeams();
    }

    @Override
    public int getTotalCounts() {
       return  teamDao.getTotalCounts();
    }

    @Override
    public List<TeamMember> getTeamMembers(int id) {
        return teamMemberDao.getMemberListByTeamId(id);
    }

    @Override
    public int deleteTeamMembers(int parseInt) {
        int number = teamMemberDao.deleteMemberByTeamId(parseInt);
        int num = teamDao.deleteTeamById(parseInt);
        return Math.max(num,number);
    }

}
