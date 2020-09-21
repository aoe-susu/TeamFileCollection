package com.four.dao;

import com.four.entity.CollectionTask;

import java.sql.SQLException;
import java.util.List;
import java.util.Date;
import java.util.Map;

public interface CollectionTaskDao {
    public List<CollectionTask> findAll() throws SQLException;//通过id获取任务列表

    public void  addCollectionTask(CollectionTask task) throws SQLException;//添加收集任务

    public CollectionTask findCollectionTaskById(int id) throws SQLException;

    public void modefyCollectionTaskById(CollectionTask task) throws SQLException;//通过id修改任务，只能修改名称、说明、以及任务截止时间

    public void deleteCollectionTaskById(int id) throws SQLException;//通过id删除任务

    public int deleteCollectionTaskByTeamId(int teamId);//通过团队id删除任务

    public List<CollectionTask> getCollectionTaskListByTeamId(int teamId) throws SQLException;//通过团队id获取任务列表

    public List<CollectionTask> getCollectionTaskListByTeamIdAfterTime(int start, int rows, Map<String, String[]> condition, Date dateTime) throws SQLException;//通过团队id获取比当前时间大的任务列表（未截止的任务）

    /**
     * 查询总记录数
     * @return
     * @param condition
     */
    int findTotalCount(Map<String, String[]> condition) throws SQLException;

    int findTotalCountAfterTime(Map<String, String[]> condition,Date date) throws SQLException;

    /**
     * 分页查询每页记录
     * @param start
     * @param rows
     * @param condition
     * @return
     */
    List<CollectionTask> findByPage(int start, int rows, Map<String, String[]> condition) throws SQLException;
}
