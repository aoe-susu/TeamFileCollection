package com.four.dao;

import com.four.entity.CollectionTask;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface CollectionTaskDao {

    public int  addCollectionTask(CollectionTask task);//添加收集任务

    public int modefyCollectionTaskById(int id, CollectionTask task);//通过id修改任务，只能修改名称、说明、以及任务截止时间

    public int deleteCollectionTaskById(int id);//通过id删除任务

    public int deleteCollectionTaskByTeamId(int teamId);//通过团队id删除任务

    public List<CollectionTask> getCollectionTaskListByTeamId(int teamId) throws SQLException;//通过团队id获取任务列表

    public List<CollectionTask> getCollectionTaskListByTeamIdAfterTime(int teamId, Date dateTime);
    //通过团队id获取比当前时间大的任务列表（未截止的任务）
}
