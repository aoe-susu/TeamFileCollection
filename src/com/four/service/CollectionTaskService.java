package com.four.service;

import com.four.dao.CollectionTaskDao;
import com.four.daoimpl.CollectionTaskDaoImpl;
import com.four.entity.CollectionTask;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class CollectionTaskService{
    private CollectionTaskDao taskDao = new CollectionTaskDaoImpl();

    public int addCollectionTask(CollectionTask task) {
        return taskDao.addCollectionTask(task);
    }
    public int modefyCollectionTaskById(int id, CollectionTask task) {
        return taskDao.modefyCollectionTaskById(id,task);
    }
    public List<CollectionTask> getCollectionTaskListByTeamId(int teamId) throws SQLException {
        return taskDao.getCollectionTaskListByTeamId(teamId);
    }
    public List<CollectionTask> getCollectionTaskListByTeamIdAfterTime(int teamId, Date dateTime) {
        return taskDao.getCollectionTaskListByTeamIdAfterTime(teamId,dateTime);
    }
}
