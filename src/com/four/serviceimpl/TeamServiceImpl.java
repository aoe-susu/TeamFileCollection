package com.four.serviceimpl;

import com.four.dao.TeamDao;
import com.four.daoimpl.TeamDaoImpl;
import com.four.entity.Team;
import com.four.service.TeamService;

public class TeamServiceImpl implements TeamService {
    TeamDao teamDao = new TeamDaoImpl();

    @Override
    public Boolean existTeamAccount(String account) {
        return teamDao.existTeamAccount(account);
    }

    @Override
    public int addTeam(Team team) {
        return teamDao.addTeam(team);
    }

    @Override
    public Boolean existTeamAccountAndPassword(String account, String password) {
        return teamDao.existTeamAccountAndPassword(account,password);
    }

    @Override
    public String getPasswordById(int id) {
        return teamDao.getPasswordById(id);
    }

    @Override
    public int deleteTeamById(int id) {
        return teamDao.deleteTeamById(id);
    }

    @Override
    public Team getTeamById(int id) {
        return teamDao.getTeamById(id);
    }

    @Override
    public Team getTeamByName(String name) {
        return teamDao.getTeamByName(name);
    }

    @Override
    public Team getTeamByAccount(String account) {
        return teamDao.getTeamByAccount(account);
    }

    @Override
    public int modifyPasswordById(int id, String password) {
        return teamDao.modifyPasswordById(id,password);
    }

    @Override
    public int modifyNameById(int id, String Name) {
        return teamDao.modifyNameById(id, Name);
}

    @Override
    public int modifyIntroductionById(int id, String introduction) {
        return teamDao.modifyIntroductionById(id, introduction);
    }

    @Override
    public int modifyIconUrlById(int id, String iconUrl) {
        return teamDao.modifyIconUrlById(id, iconUrl);
    }
}
