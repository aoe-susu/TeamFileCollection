package com.four.daoimpl;

import com.four.dao.TeamMemberDao;
import com.four.entity.TeamMember;
import com.four.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

public class TeamMemberDaoImpl implements TeamMemberDao {

    private static QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());

    @Override
    public int getMemberCountByTeamId(int teamId) {

        String sql = " select count(*)  from  team_member where teamId=?";
        int count = 0;
        try {
            count = ((Long) queryRunner.query(sql, new ScalarHandler<>(), teamId)).intValue();
        } catch (SQLException th) {
            th.printStackTrace();
        }
        return count;
    }

    @Override
    public List<TeamMember> getMemberListByTeamId(int teamId) {
        String sql = "select * from team_member where teamId=?";
        List<TeamMember> list = new ArrayList<>();
        try {
            list.addAll(queryRunner.query(sql, new BeanListHandler<>(TeamMember.class), teamId));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    @Override
    public int deleteMemberById(int id) {
        String sql = "delete from team_member where id=?";
        int number = 0;
        try {
            number = queryRunner.update(sql, id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return number;
    }

    @Override
    public int deleteMemberByTeamId(int teamId) {
        String sql = "delete from team_member where teamId=?";
        int number = 0;
        try {
            number = queryRunner.update(sql, teamId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return number;
    }

    @Override
    public Boolean existMemberNumber(String num) {

        String sql = "select * from team_member where number=?";
        boolean count = false;
        TeamMember teamMember;
        try {
            teamMember = queryRunner.query(sql, new BeanHandler<>(TeamMember.class), num);
            if (teamMember != null) {
                count = true;
            }
        } catch (SQLException th) {
            th.printStackTrace();
        }
        return count;
    }

    @Override
    public int addMember(TeamMember member) {

        String sql = "insert into team_member(number,name,iconUrl,teamId) values(?,?,?,?)";
        Object[] params = {member.getNumber(), member.getName(), member.getIconUrl(), member.getTeamId()};
        int num = 0;
        try {
            BigInteger big  = (queryRunner.insert(sql, new ScalarHandler<>(), params));
            num = Integer.parseInt(big.toString());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return num;
    }

}
