package com.four.service;

import com.four.dao.TaskFileDao;
import com.four.daoimpl.TaskFileDaoImpl;
import com.four.entity.TaskFile;

public class TaskFileService {
    private static TaskFileDao taskFileDao = new TaskFileDaoImpl();

    public int addTaskFile(TaskFile taskFile){
        return taskFileDao.addTaskFile(taskFile);
    }

    public String modifyAddressById(int memberId){
        return taskFileDao.modifyAddressById(memberId);
    }

    public boolean check(int id){
        return taskFileDao.check(id);
    }

    public int modifyFileAddressById(TaskFile taskFile){
        return taskFileDao.modifyFileAddressById(taskFile);
    }
}
