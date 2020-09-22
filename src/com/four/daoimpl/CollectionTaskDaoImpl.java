package com.four.daoimpl;

import com.four.dao.CollectionTaskDao;
import com.four.entity.CollectionTask;
import com.four.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class CollectionTaskDaoImpl implements CollectionTaskDao {
    private QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
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
    public List<CollectionTask> getCollectionTaskListByTeamId(int teamId) throws SQLException {
        String sql = "select * from collection_task where teamId=?";
        List<CollectionTask> list = queryRunner.query(sql,new BeanListHandler<CollectionTask>(CollectionTask.class),teamId);
        if (list.size()>0){
            return list;
        }else {
            return null;
        }
    }

    @Override
    public List<CollectionTask> getCollectionTaskListByTeamIdAfterTime(int teamId, Date dateTime) {
        return null;
    }
}
