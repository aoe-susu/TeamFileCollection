package com.four.daoimpl;

import com.four.dao.TeamMemberDao;
import com.four.entity.Application;
import com.four.entity.TaskFile;
import com.four.entity.TeamMember;
import com.four.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class TeamMemberDaoImpl implements TeamMemberDao {
    private static QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
    @Override
    public int getMemberCountByTeamId(int teamId) {
        return 0;
    }

    @Override
    public List<TeamMember> getMemberListByTeamId(int teamId) {
        return null;
    }

    @Override
    public int deleteMemberById(int id) {
        return 0;
    }

    @Override
    public int deleteMemberByTeamId(int teamId) {
        return 0;
    }

    @Override
    public Boolean existMemberNumber(String num) {
        return null;
    }

    @Override
    public int addMember(TeamMember member) {
        return 0;
    }

    @Override
    public List<Application> getTeamMemberListByTeamId(int teamId) throws SQLException {
        String sql = "select * from team_member where teamId=?";
        List<Application> list = queryRunner.query(sql,new BeanListHandler<Application>(Application.class),teamId);
        if (list.size()>0){
            return list;
        }else {
            return null;
        }
    }

    @Override
    public List<Application> getTeamMemberList() throws SQLException {
        String sql = "select * from team_member";
        List<Application> list = queryRunner.query(sql,new BeanListHandler<Application>(Application.class));
        if (list.size()>0){
            return list;
        }else {
            return null;
        }
    }

    @Override
    public List<TaskFile> getTeamMember(int teamId) throws SQLException {
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
