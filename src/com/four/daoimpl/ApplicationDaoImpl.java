package com.four.daoimpl;

import com.four.dao.ApplicationDao;
import com.four.entity.Application;
import com.four.entity.TaskFile;
import com.four.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class ApplicationDaoImpl implements ApplicationDao {
    private static QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
    @Override
    public int addApplication(Application application) {
        return 0;
    }

    @Override
    public int deleteApplicationById(int id) {
        return 0;
    }

    @Override
    public int deleteApplicationByTeamId(int teamId) {
        return 0;
    }

    @Override
    public List<Application> getApplicationListByTeamId(int teamId) throws SQLException {
        String sql = "select * from application where teamId=?";
        List<Application> list = queryRunner.query(sql,new BeanListHandler<Application>(Application.class),teamId);
        if (list.size()>0){
            return list;
        }else {
            return null;
        }
    }

    @Override
    public List<Application> getApplicationList() throws SQLException {
        String sql = "select * from application";
        List<Application> list = queryRunner.query(sql,new BeanListHandler<Application>(Application.class));
        if (list.size()>0){
            return list;
        }else {
            return null;
        }
    }

    @Override
    public List<TaskFile> getApplication(int teamId) throws SQLException {
        String sql = "select * from task_file where memberId in (select id from team_member where teamId=?)";
        List<TaskFile> list = queryRunner.query(sql,new BeanListHandler<TaskFile>(TaskFile.class),teamId);
        if (list.size()>0){
            return list;
        }else {
            return null;
        }
    }

    @Override
    public List<TaskFile> getTimeByMemberId(int memId) throws SQLException {
        return null;
    }

    @Override
    public int getFileName(int id) throws SQLException {
        return 0;
    }

    @Override
    public int getTotalCount(int teamId) throws SQLException {
        String sql = "SELECT count(*) FROM team_member WHERE teamId=?";
        long result = 0;
        try {
            result = queryRunner.query(sql,new ScalarHandler<Long>(),teamId);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return (int)result;
    }

    @Override
    public int getUpCount(int teamId) throws SQLException {
        String sql = "select count(*) from team_member where teamId=? and id in (select memberId from task_file)";
        long result = 0;
        try {
            result = queryRunner.query(sql,new ScalarHandler<Long>(),teamId);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return (int)result;
    }

}

