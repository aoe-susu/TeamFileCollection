package com.four.serviceimpl;

import com.four.dao.TaskFileDao;
import com.four.daoimpl.TaskFileDaoImpl;
import com.four.entity.TaskFile;

import java.sql.SQLException;
import java.util.List;

public class TaskFileService {
    private static TaskFileDao taskFileDao = new TaskFileDaoImpl();

    public int addTaskFile(TaskFile taskFile){
        return taskFileDao.addTaskFile(taskFile);
    }

    public String getAddressById(int memberId,int taskId){
        return taskFileDao.getAddressById(memberId,taskId);
    }

    public boolean check(int id){
        return taskFileDao.check(id);
    }

    public int modifyFileAddressById(TaskFile taskFile){
        return taskFileDao.modifyFileAddressById(taskFile);
    }


    public List<TaskFile> getTaskFile(int taskId) throws SQLException{
        return taskFileDao.getTaskFile(taskId);
    }

    public List<TaskFile> getTimeByMemberId(int memId) throws SQLException{
        return taskFileDao.getTimeByMemberId(memId);
    }


    public int getFileCount(int taskId) throws SQLException {
        return taskFileDao.getTaskFileCount(taskId);
    }

}
