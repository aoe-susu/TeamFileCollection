package com.four.daoimpl;

import com.four.dao.CollectionTaskDao;
import com.four.entity.CollectionTask;

import java.util.Date;
import java.util.List;

public class CollectionTaskDaoImpl implements CollectionTaskDao {
    @Override
    public int addCollectionTask(CollectionTask task) {
        return 0;
    }

    @Override
    public int modefyCollectionTaskById(int id, CollectionTask task) {
        return 0;
    }

    @Override
    public int deleteCollectionTaskById(int id) {
        return 0;
    }

    @Override
    public int deleteCollectionTaskByTeamId(int teamId) {
        return 0;
    }

    @Override
    public List<CollectionTask> getCollectionTaskListByTeamId(int teanId) {
        return null;
    }

    @Override
    public List<CollectionTask> getCollectionTaskListByTeamIdAfterTime(int teanId, Date dateTime) {
        return null;
    }
}
