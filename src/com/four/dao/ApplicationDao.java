package com.four.dao;

import com.four.entity.Application;

import java.util.List;

public interface ApplicationDao {

    public int addApplication(Application application);//添加申请

    public int deleteApplicationById(int id);//通过id删除申请

    public int deleteApplicationByTeamId(int teamId);//通过团队id删除申请

    public List<Application> getApplicationListByTeamId(int teamId);//通过团队id获取申请信息列表

}
