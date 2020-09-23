package com.four.daoimpl;

import com.four.dao.TeamMemberDao;
import com.four.entity.Application;
import com.four.entity.TeamMember;
import com.four.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.lang.reflect.Member;
import java.sql.*;
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
    public int deleteMemberById1(int id) {
        String delete1_sql="delete from application where id=?";
        try {
            return qr.update(delete1_sql,id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    @Override
    public Boolean existMemberNumber(String num) {
        String sql="select *  from application where number =?";
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
    public int addMember(TeamMember member) {
        String insert_sql="insert into team_member (number,name,teamId,img) values(?,?,?,?)";
        try {
            return qr.update(insert_sql, new Object[]{member.getNumber(), member.getName(),member.getTeamId(),member.getIconUrl()});
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int addMember1(Application application) {
        String insert_sql1="insert into application(number,name,teamId) values(?,?,?)";
        try {

            int re= qr.update(insert_sql1,new Object[]{application.getNumber(),application.getName(),application.getTeamId()});
            System.out.println("re="+re);
            return re;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<TeamMember> getApplyMemberListByteamId(int teamId) throws SQLException {
        String get2_sql="select * from application where teamId=?";
        List<TeamMember>list=null;
        list=qr.query(get2_sql, new BeanListHandler<TeamMember>(TeamMember.class),teamId);
        if(list.size()>0){
            return list;
        }else {
            return null;
        }
    }

    @Override
    public TeamMember getTeamById(int id) {
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
        String sql = "update teamMember set iconUrl=? where id=?";
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
        String sqlUpdate = "update teamMember set img=? where =number?";
        try {
            return qr.update(sqlUpdate,number,img);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override


    public int getMemberByPwdAndNum(String password,String num){



        String Pwd_sql="SELECT count(*) from team_member tm,team t where t.id=tm.teamId and t.`password`=? and tm.number=?;";
        try {



            Object obj=qr.query(Pwd_sql,new ScalarHandler<>(),password,num);
            int count =Integer.parseInt(obj.toString()) ;
            System.out.println("count  ="+count);
            return count;

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return 0;

    }

}
