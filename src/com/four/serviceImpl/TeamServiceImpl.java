package com.four.serviceImpl;

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
    public int deleteTeamById(int id) {
        return teamDao.deleteTeamById(id);
    }

    @Override
    public Team getTeamById(int id) {
        return teamDao.getTeamById(id);
    }

    @Override
    public int modefyNameById(int id, String Name) {
        return teamDao.modefyNameById(id, Name);
    }

    @Override
    public int modefyIntroductionById(int id, String introduction) {
        return teamDao.modefyIntroductionById(id, introduction);
    }

    @Override
    public int modefyIconUrlById(int id, String iconUrl) {
        return teamDao.modefyIconUrlById(id, iconUrl);
    }
}
