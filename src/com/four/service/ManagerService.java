package com.four.service;

import com.four.entity.Team;
import com.four.entity.TeamMember;

import java.util.List;

public interface ManagerService {
    List<Team> getTeams();
    int getTotalCounts();
    List<TeamMember> getTeamMembers(int id);

    int deleteTeamMembers(int parseInt);
}
