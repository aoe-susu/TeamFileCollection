package com.four.dao;

import com.four.entity.Application;
import com.four.entity.TaskFile;

import java.sql.SQLException;
import java.util.List;

public interface ApplicationDao {

    public int addApplication(Application application);//添加申请

    public int deleteApplicationById(int id);//通过id删除申请

    public int deleteApplicationByTeamId(int teamId);//通过团队id删除申请

    public List<Application> getApplicationListByTeamId(int teamId) throws SQLException;//通过团队id获取申请信息列表

    public List<Application> getApplicationList() throws SQLException;

    public List<TaskFile> getApplication(int teamId) throws SQLException;

    public List<TaskFile> getTimeByMemberId(int memId) throws SQLException;

    public int getFileName(int id) throws SQLException;

    public int getTotalCount(int teamId) throws SQLException;

    public int getUpCount(int teamId) throws SQLException;
}
