package com.four.daoimpl;

import com.four.dao.CollectionTaskDao;
import com.four.entity.CollectionTask;
import com.four.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.*;

import java.sql.SQLException;
import java.util.*;

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
        String sql="SELECT * FROM collection_task WHERE teamId=?";
        List<CollectionTask> list = null;
        list = queryRunner.query(sql, new BeanListHandler<CollectionTask>(CollectionTask.class),teamId);
        if (list.size()>0){
            return list;
        }else{
            return null;
        }
    }

    @Override
    public List<CollectionTask> getCollectionTaskListByTeamIdAfterTime(int start, int rows, Map<String, String[]> condition, Date dateTime) throws SQLException {
        String sql="select * from collection_task where teamId=2 and TO_DAYS(?)<TO_DAYS(deadline)";
        StringBuilder sb = new StringBuilder(sql);
        Set<String> keySet = condition.keySet();
        List<Object> params=new ArrayList<Object>();
        for (String key : keySet) {

            //排除分页条件参数
            if("currentPage".equals(key)||"rows".equals(key)){
                continue;
            }

            String value = condition.get(key)[0];
            if(value!=null&&!"".equals(value)){
                sb.append(" and "+key+" like ? ");
                params.add("%"+value+"%");
            }
        }

        //添加分页查询
        sb.append(" limit ?,? ");
        //添加分页查询参数值
        params.add(dateTime);
        params.add(start);
        params.add(rows);

        sql=sb.toString();

        return queryRunner.query(sql,new BeanListHandler<CollectionTask>(CollectionTask.class),params.toArray());

    }

    @Override
    public int findTotalCount(Map<String, String[]> condition) throws SQLException {
        String sql="select count(*) from collection_task where teamId=2";
        StringBuilder sb = new StringBuilder(sql);
        Set<String> keySet = condition.keySet();
        List<Object> params=new ArrayList<Object>();
        for (String key : keySet) {

            //排除分页条件参数
            if("currentPage".equals(key)||"rows".equals(key)){
                continue;
            }

            String value = condition.get(key)[0];
            if(value!=null&&!"".equals(value)){
                sb.append(" and "+key+" like ? ");
                params.add("%"+value+"%");
            }
        }
        long result = queryRunner.query(sb.toString(),new ScalarHandler<Long>(),params.toArray());
        return (int)result;
    }

    @Override
    public int findTotalCountAfterTime(Map<String, String[]> condition,Date date) throws SQLException {
        String sql="select count(*) from collection_task where teamId=2 and TO_DAYS(?)<TO_DAYS(deadline) ";
        StringBuilder sb = new StringBuilder(sql);
        Set<String> keySet = condition.keySet();
        List<Object> params=new ArrayList<Object>();
        params.add(date);
        for (String key : keySet) {

            //排除分页条件参数
            if("currentPage".equals(key)||"rows".equals(key)){
                continue;
            }

            String value = condition.get(key)[0];
            if(value!=null&&!"".equals(value)){
                sb.append(" and "+key+" like ? ");
                params.add("%"+value+"%");
            }
        }
        long result = queryRunner.query(sb.toString(),new ScalarHandler<Long>(),params.toArray());
        return (int)result;
    }

    @Override
    public List<CollectionTask> findByPage(int start, int rows, Map<String, String[]> condition) throws SQLException {
        String sql="select * from collection_task where teamId=2 ";
        StringBuilder sb = new StringBuilder(sql);
        Set<String> keySet = condition.keySet();
        List<Object> params=new ArrayList<Object>();
        for (String key : keySet) {

            //排除分页条件参数
            if("currentPage".equals(key)||"rows".equals(key)){
                continue;
            }

            String value = condition.get(key)[0];
            if(value!=null&&!"".equals(value)){
                sb.append(" and "+key+" like ? ");
                params.add("%"+value+"%");
            }
        }

        //添加分页查询
        sb.append(" limit ?,? ");
        //添加分页查询参数值
        params.add(start);
        params.add(rows);

        sql=sb.toString();

        return queryRunner.query(sql,new BeanListHandler<CollectionTask>(CollectionTask.class),params.toArray());

    }
}
