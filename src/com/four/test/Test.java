package com.four.test;

import com.four.dao.TeamMemberDao;

import com.four.daoimpl.TeamMemberDaoImpl;
import com.four.entity.TeamMember;

import java.util.List;

public class Test {

    @org.junit.Test
    public void test() {

        TeamMemberDao teamMemberDao = new TeamMemberDaoImpl();

        int t =  teamMemberDao.deleteMemberById(3);
        System.out.println("----t-----"+t);

       /* int count = teamMemberDao.getMemberCountByTeamId(1);
        System.out.println("----------" + count);

        List<TeamMember> list = teamMemberDao.getMemberListByTeamId(1);
        for (TeamMember teamMember:list){
            System.out.println("----------"+teamMember.toString());
        }
        TeamMember teamMember=new TeamMember();
        teamMember.setIconUrl("2425761238@qq.com");
        teamMember.setId(1);
        teamMember.setName("老王");
        teamMember.setNumber("6");
        teamMember.setTeamId(2);
        int t = teamMemberDao.addMember(teamMember);
        System.out.println("-----"+t);*/

    }

}
