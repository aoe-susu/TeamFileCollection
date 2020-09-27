package com.four.daoimpl;

import com.four.dao.TeamDao;
import com.four.entity.Team;
import com.four.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;

public class TeamDaoImpl implements TeamDao {
    private QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());

    Team team = new Team();

    private static final String DEAFULTHEADERPICTURE = "\\img\\1.gif";

    @Override
    public Team getTeamById(int id) {
        String selectSql = "select * from team where id=?";
        Team team = null;
        try {
            team = queryRunner.query(selectSql,new BeanHandler<Team>(Team.class),id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return team;
    }

    @Override
    public Team getTeamByName(String name) {
        String selectSql = "select * from team where name=?";
        Team team = null;
        try {
            team = queryRunner.query(selectSql,new BeanHandler<Team>(Team.class),name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return team;
    }

    @Override
    public Team getTeamByAccount(String account) {
        String selectSql = "select * from team where account=?";
        Team team = null;
        try {
            team = queryRunner.query(selectSql,new BeanHandler<Team>(Team.class),account);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return team;
    }


    @Override
    public Boolean existTeamAccount(String account) {
        String selectSql = "select * from team where account=?";
        Team team = null;
        try {
            team = queryRunner.query(selectSql, new BeanHandler<Team>(Team.class), account);
            if (team != null) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean existTeamAccountAndPassword(String account, String password) {
        String selectSql = "select * from team where account=? and password=?";
        Team team = null;
        try {
            team = queryRunner.query(selectSql, new BeanHandler<Team>(Team.class), account, password);
            if (team != null) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public String getPasswordById(int id)  {
        String selectSql = "select password from team where id=?";
        try {

            String password = (String) queryRunner.query(selectSql,new ScalarHandler<>(),id);
            return  password;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int addTeam(Team team) {
        String insertSql = " insert into team(account,name,password,introduction,iconUrl) values(?,?,?,?,?)";
        try {
            return queryRunner.update(insertSql, new Object[]{team.getAccount(), team.getName(),team.getPassword(), team.getIntroduction(), team.getIconUrl()});
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteTeamById(int id) {
        String deleteSql = "delete from team where id=?";
        try {
            return queryRunner.update(deleteSql,id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int modifyPasswordById(int id, String password) {
        String updateSql = "update team set password=? where id=?";
        try {
            return queryRunner.update(updateSql,password,id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int modifyNameById(int id, String name) {
        String updateSql = "update team set name=? where id=?";
        try {
            return queryRunner.update(updateSql,name,id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int modifyIntroductionById(int id, String introduction) {
        String updateSql = "update team set introduction=? where id=?";
        try {
            return queryRunner.update(updateSql,introduction,id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int modifyIconUrlById(int id, String iconUrl) {
        int result = 0;
        String sql = "update team set iconUrl=? where id=?";
        Object[] params = {iconUrl,id};
        try {
            result = queryRunner.update(sql,params);
        }catch (SQLException e){
            e.printStackTrace();
        }
        if (result>0) {
            return result;
        }else {
            return 0;
        }
    }

}
