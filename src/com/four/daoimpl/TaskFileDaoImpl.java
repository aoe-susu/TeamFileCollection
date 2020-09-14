package com.four.daoimpl;

import com.four.dao.TaskFileDao;
import com.four.entity.TaskFile;

import java.util.List;

public class TaskFileDaoImpl implements TaskFileDao {
    @Override
    public int addTaskFile(TaskFile taskFile) {
        return 0;
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
