package com;

import com.four.service.TeamService;
import com.four.serviceImpl.TeamServiceImpl;

public class Test {
    TeamService teamService = new TeamServiceImpl();
    @org.junit.Test
    public void test1(){
        boolean flag =  teamService.existTeamAccount("2017java1");
        System.out.println(flag);
    }
    @org.junit.Test
    public  void test2(){

    }

}
