package com.four.service;

import com.four.dao.ApplicationDao;
import com.four.dao.TeamMemberDao;
import com.four.daoimpl.ApplicationDaoImpl;
import com.four.daoimpl.TeamMemberDaoImpl;
import com.four.entity.Application;
import com.four.entity.TaskFile;
import com.four.entity.TeamMember;

import java.sql.SQLException;
import java.util.List;

public class TeamMemberService{
    private TeamMemberDao applicationDao = new TeamMemberDaoImpl();

    public List<Application> getApplicationList() throws SQLException {
        return applicationDao.getApplicationList();
    }

    public List<Application> getApplicationListByTeamId(int teamId) throws SQLException{
        return applicationDao.getApplicationListByTeamId(teamId);
    }

    public List<TaskFile> getApplication(int teamId) throws SQLException{
        return applicationDao.getApplication(teamId);
    }

    public List<TaskFile> getTimeByMemberId(int memId) throws SQLException{
        return applicationDao.getTimeByMemberId(memId);
    }

    public int getTotalCount(int teamId) throws SQLException{
        return applicationDao.getTotalCount(teamId);
    }

    public int getUpCount(int teamId) throws SQLException{
        return applicationDao.getUpCount(teamId);
    }
}
