package com.four.daoimpl;

import com.four.dao.TeamMemberDao;
import com.four.entity.TeamMember;

import java.util.List;

public class TeamMemberDaoImpl implements TeamMemberDao {
    @Override
    public int getMemberCountByTeamId(int teamId) {
        return 0;
    }

    @Override
    public List<TeamMember> getMemberListByTeamId(int teamId) {
        return null;
    }

    @Override
    public int deleteMemberById(int id) {
        return 0;
    }

    @Override
    public int deleteMemberByTeamId(int teamId) {
        return 0;
    }

    @Override
    public Boolean existMemberNumber(String num) {
        return null;
    }

    @Override
    public int addMember(TeamMember member) {
        return 0;
    }

}
