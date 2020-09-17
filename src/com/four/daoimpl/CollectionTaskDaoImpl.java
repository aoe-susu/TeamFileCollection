package com.four.daoimpl;

import com.four.dao.CollectionTaskDao;
import com.four.entity.CollectionTask;
import com.four.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.*;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class CollectionTaskDaoImpl implements CollectionTaskDao {
    private QueryRunner queryRunner=new QueryRunner(JDBCUtils.getDataSource());

    @Override
    public List<CollectionTask> findAll() throws SQLException{
        String sql="select * from collection_task";
        List<CollectionTask> tasks = queryRunner.query(sql, new BeanListHandler<CollectionTask>(CollectionTask.class));
        return tasks;
    }

    @Override
    public void addCollectionTask(CollectionTask task) throws SQLException {
        String sql="insert into collection_task values(null,?,?,?,?)";
        queryRunner.update(sql,task.getTitle(),task.getContent(),task.getTeamId(),task.getDeadline());

    }

    @Override
    public CollectionTask findCollectionTaskById(int id) throws SQLException {
        String sql="select * from collection_task where id=?";
        return (CollectionTask) queryRunner.query(sql,new BeanHandler<CollectionTask>(CollectionTask.class),id);
    }

    @Override
    public void modefyCollectionTaskById(CollectionTask task) throws SQLException {
        String sql="update collection_task set title=?,content=?,deadline=? where id=?";
        queryRunner.update(sql,task.getTitle(),task.getContent(),task.getDeadline(),task.getId());
    }

    @Override
    public void deleteCollectionTaskById(int id) throws SQLException {
        String sql="delete from collection_task where id=?";
        queryRunner.update(sql,id);
    }

    @Override
    public int deleteCollectionTaskByTeamId(int teamId) {
        return 0;
    }

    @Override
    public List<CollectionTask> getCollectionTaskListByTeamId(int teamId) throws SQLException {
        String sql="select * from collection_task";
        List<CollectionTask> list = null;
        list = queryRunner.query(sql, new BeanListHandler<CollectionTask>(CollectionTask.class));
        if (list.size()>0){
            CollectionTask task = list.get(0);
            System.out.println(task.getDeadline().toString());
            return list;
        }else{
            return null;
        }
    }

    @Override
    public List<CollectionTask> getCollectionTaskListByTeamIdAfterTime(int teamId, Date dateTime) {
        return null;
    }

    @Override
    public int findTotalCount() throws SQLException {
        String sql="select count(*) from collection_task";
        long result = queryRunner.query(sql,new ScalarHandler<Long>());
        return (int)result;
    }

    @Override
    public List<CollectionTask> findByPage(int start, int rows) throws SQLException {
        String sql="select * from collection_task limit ? , ? ";
        return queryRunner.query(sql,new BeanListHandler<CollectionTask>(CollectionTask.class),start,rows);

    }
}
