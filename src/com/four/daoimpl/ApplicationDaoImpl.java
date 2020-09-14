package com.four.daoimpl;

import com.four.dao.ApplicationDao;
import com.four.entity.Application;
import com.four.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

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
    public List<Application> getApplicationListByTeamId(int teamId) {
        return null;
    }

    public static Application getApplication(){
        String sql = "select * from application";
        Application application = null;
        try {
            application = queryRunner.query(sql,new BeanHandler<>(Application.class));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return application;
    }
    public static void main(String[] args) {
        Application application = ApplicationDaoImpl.getApplication();
        System.out.println(application);
    }
}
