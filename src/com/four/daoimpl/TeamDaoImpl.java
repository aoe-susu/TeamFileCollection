package com.four.daoimpl;

import com.four.dao.TeamDao;
import com.four.entity.Team;
import com.four.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class TeamDaoImpl implements TeamDao {
    private QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());

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
        return null;
    }

    @Override
    public Team getTeamByAccount(String account) {
        return null;
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
    public int modefyPasswordById(int id, String password) {
        String updateSql = "update team password=? where id=?";
        try {
            return queryRunner.update(updateSql,password,id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int modefyNameById(int id, String Name) {
        return 0;
    }

    @Override
    public int modefyIntroductionById(int id, String introduction) {
        return 0;
    }

    @Override
    public int modefyIconUrlById(int id, String iconUrl) {
        return 0;
    }

}
