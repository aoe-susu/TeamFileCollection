package com.four.daoimpl;

import com.four.dao.TeamDao;
import com.four.entity.Team;

public class TeamDaoImpl implements TeamDao {
    @Override
    public Team getTeamById(int id) {
        return null;
    }

    @Override
    public Team getTeamByName(String name) {
        return null;
    }

    @Override
    public Team getTeamByAccount(String account) {
        return null;
    }

    @Override
    public Boolean existTeamAccount(String account) {
        return null;
    }

    @Override
    public Boolean existTeamAccountAndPassword(String account, String password) {
        return null;
    }

    @Override
    public int addTeam(Team team) {
        return 0;
    }

    @Override
    public int deleteTeamById(int id) {
        return 0;
    }

    @Override
    public int modefyPasswordById(int id, String password) {
        return 0;
    }

    @Override
    public int modefyBaseInfoById(int id, Team team) {
        return 0;
    }
}
