package com.four.serviceimpl;

import com.four.dao.TeamMemberDao;
import com.four.daoimpl.TeamMemberDaoImpl;
import com.four.entity.Application;
import com.four.entity.TaskFile;

import java.sql.SQLException;
import java.util.List;

public class TeamMemberService{
    private TeamMemberDao TeamMemberDao = new TeamMemberDaoImpl();

    public List<Application> getTeamMemberList() throws SQLException {
        return TeamMemberDao.getTeamMemberList();
    }

    public List<Application> getTeamMemberListByTeamId(int teamId) throws SQLException{
        return TeamMemberDao.getTeamMemberListByTeamId(teamId);
    }

    public List<TaskFile> getTeamMember(int teamId) throws SQLException{
        return TeamMemberDao.getTeamMember(teamId);
    }

    public List<TaskFile> getTimeByMemberId(int memId) throws SQLException{
        return TeamMemberDao.getTimeByMemberId(memId);
    }

    public int getTotalCount(int teamId) throws SQLException{
        return TeamMemberDao.getTotalCount(teamId);
    }

    public int getUpCount(int teamId) throws SQLException{
        return TeamMemberDao.getUpCount(teamId);
    }
}
