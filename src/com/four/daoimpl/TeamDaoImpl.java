package com.four.daoimpl;

import com.four.dao.TeamDao;
import com.four.entity.PageBean;
import com.four.entity.Team;
import com.four.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import sun.plugin2.main.server.ResultHandler;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TeamDaoImpl implements TeamDao {

    private static QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
    @Override
    public List<Team> getTeams() {
        String sql = "select * from team ";
        try {
            List<Team> query = queryRunner.query(sql, new BeanListHandler<Team>(Team.class));
            return query;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public int getTotalCounts() {
        String sql = "select count(*) from team";
        try {
          Number number = queryRunner.query(sql,new ScalarHandler<Integer>());
           return number.intValue();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    @Override
    public Team getTeamById(int id) {
        return null;
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
        return null;
    }

    @Override
    public Boolean existTeamAccountAndPassword(String account, String password) {
        return null;
    }

    @Override
    public int countTeam(PageBean bean) {
        String sql = "select count(*) from team ";
        try {
            int num = Integer.parseInt(queryRunner.query(sql,new ScalarHandler<Integer>()).toString());
            bean.setTotalSize(num);
            return num;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    @Override
    public int addTeam(Team team) {
        String sql = "insert into team(id,account,name,password,introduction,iconUrl) values(?,?,?,?,?,?)";
        Object[] params = {team.getId(),team.getAccount(), team.getName(), team.getPassword(), team.getIntroduction(),team.getIconUrl()};
        int num =0;
        try {
            BigInteger big = queryRunner.insert(sql,new ScalarHandler<>(),params);
            num = Integer.parseInt(big.toString());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return num;
    }

    @Override
    public int deleteTeamById(int id) {
        String sql = "delete from team where id=?";
        try {
           int number =  queryRunner.update(sql,id);
           return number;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }

    @Override
    public int modefyPasswordById(int id, String password) {
        return 0;
    }

    @Override
    public int modefyBaseInfoById(int id, Team team) {
        return 0;
    }

    @Override
    public PageBean getPageTeam(PageBean bean)  {
        int size = bean.getSIZE();
        int currentPage = bean.getCurrentPage();
        String sql ="select* from team limit ?,?";
        try {
           List<Team> team = (List<Team>) queryRunner.query(sql,new BeanListHandler(Team.class),new Object[]{(currentPage-1)*size,size});
           bean.setTeamList(team);
           return bean;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return bean;
    }
}
