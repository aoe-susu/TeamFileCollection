package com.four.daoimpl;

import com.four.dao.TeamMemberDao;
import com.four.entity.Application;
import com.four.entity.TeamMember;
import com.four.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeamMemberDaoImpl implements TeamMemberDao {
    private QueryRunner qr;
    public TeamMemberDaoImpl()  {
        qr = new QueryRunner(JDBCUtils.getDataSource());
    }


//    @Override
//    public Boolean existTeamPassword(String password) {
//        return null;
//    }

    @Override
    public List<TeamMember> getTeamMemberListByTeamId(int teamId) throws SQLException {
        String sql = "select * from team_member where teamId=?";
        List<TeamMember> list = qr.query(sql,new BeanListHandler<TeamMember>(TeamMember.class),teamId);
        if (list.size()>0){
            return list;
        }else {
            return new ArrayList<>();
        }
    }

    @Override
    public List<TeamMember> getMemberListByTeamId(int teamId) throws SQLException {
        String get_sql="select * from team_member where teamId=?";
        List<TeamMember>list=null;
        try {
            list=qr.query(get_sql, new BeanListHandler<TeamMember>(TeamMember.class),teamId);
        }catch (SQLException e){
            e.printStackTrace();
        }
        if(list.size()>0){
            return list;
        }else {
            return null;
        }
    }

    @Override
    public int deleteMemberById(int id) {
        String delete2_sql="delete from team_member where id=?";
        try {
            return qr.update(delete2_sql,id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }




    @Override
    public int addMember(TeamMember member) {
        String insert_sql="insert into team_member (number,name,teamId,iconUrl) values(?,?,?,?)";
        try {
            return qr.update(insert_sql, new Object[]{member.getNumber(), member.getName(),member.getTeamId(),member.getIconUrl()});
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public TeamMember getTeamMemberById(int id) {
        String get3_sql="select * from team_member where id=?";
        TeamMember teamMember=null;
        try {
            teamMember=qr.query(get3_sql,new BeanHandler<TeamMember>(TeamMember.class),id);

            if(teamMember!=null) {
                return teamMember;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public int modifyIconUrlById(int id, String s) {
        int result = 0;
        String sql = "update team_member set iconUrl=? where id=?";
        Object[] params = {s,id};
        try {
            result = qr.update(sql,params);
        }catch (SQLException e){
            e.printStackTrace();
        }
        if (result>0) {
            return result;
        }else {
            return 0;
        }
    }

    @Override
    public int modifyIconUrlByNumber(String number, String img) {
        String sqlUpdate = "update team_member set iconUrl=? where =number?";
        try {
            return qr.update(sqlUpdate,number,img);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public TeamMember getMemberByPwdAndNum(String password,String num,int teamId){



        String Pwd_sql="SELECT tm.`id`,tm.`number`,tm.`name`,tm.`teamId`,tm.`iconUrl` from team_member tm,team t where t.id=tm.teamId and t.`password`=? and tm.number=? and t.id=?;";
        TeamMember teamMember=null;
        try {
            teamMember=qr.query(Pwd_sql,new BeanHandler<TeamMember>(TeamMember.class),password,num,teamId);

            if(teamMember!=null) {
                return teamMember;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    @Override
    public Boolean existMemberNumber(String num) {
        String sql="select *  from team_member where number =?";
        TeamMember member=null;
        try {
            member= qr.query(sql,new BeanHandler<TeamMember>(TeamMember.class),num);
            System.out.println(member);
            if(member!=null){
                return  true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int getMemberCount(int teamId) {
        String sql = "SELECT count(*) FROM team_member WHERE teamId=?";
        long result = 0;
        try {
            result = qr.query(sql,new ScalarHandler<Long>(),teamId);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return (int)result;
    }

}
