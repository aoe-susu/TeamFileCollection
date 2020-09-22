package com.four.daoimpl;

import com.four.dao.TaskFileDao;
import com.four.entity.TaskFile;
import com.four.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class TaskFileDaoImpl implements TaskFileDao {
    private static QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());

    @Override
    public int addTaskFile(TaskFile taskFile) {
        int result = 0;
        String sql = "insert into task_file(memberId,taskId,address,uploadTime) values(?,?,?,?)";
        Object[] params = {taskFile.getMemberId(),taskFile.getTaskId(),taskFile.getAddress(),taskFile.getUploadTime()};
        try {
            result = queryRunner.update(sql,params);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public String modifyAddressById(int memberId) {
        String sql = "select address from task_file where memberId=?";
        String result = null;
        try {
            result = queryRunner.query(sql,new ScalarHandler<String>(),memberId);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean check(int id) {
        String sql = "select * from task_file where memberId=?";
        Object[] result = null;
        try {
            result = queryRunner.query(sql,new ArrayHandler(),id);
        }catch (SQLException e){
            e.printStackTrace();
        }
        if (result.length>0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public int modifyFileAddressById(TaskFile taskFile) {
        int result = 0;
        String sql = "update task_file set address=?,uploadTime=? where memberId=? and taskId=?";
        Object[] params = {taskFile.getAddress(),taskFile.getUploadTime(),taskFile.getId(),taskFile.getTaskId()};
        try {
            result = queryRunner.update(sql,params);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int modefyFileAddressByMemberIdAndTaskId(int memberId, int taskId, String address) {
        return 0;
    }

    @Override
    public int deleteTaskFileByTaskId(int taskId) {
        return 0;
    }

    @Override
    public Boolean existMemberIdAndTaskId(int memberId, int taskId) {
        return null;
    }

    @Override
    public List<TaskFile> getTaskFileListByTaskId(int taskId) {
        return null;
    }


}
