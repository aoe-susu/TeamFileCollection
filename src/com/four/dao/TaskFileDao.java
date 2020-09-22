package com.four.dao;

import com.four.entity.TaskFile;

import java.util.List;

public interface TaskFileDao {

    public int addTaskFile(TaskFile taskFile);//添加任务文件

    public int modefyFileAddressByMemberIdAndTaskId(int memberId, int taskId, String address);//通过成员id和任务id修改文件存放地址

    public int deleteTaskFileByTaskId(int taskId);//通过任务id删除任务文件

    public Boolean existMemberIdAndTaskId(int memberId, int taskId);//是否存在成员id=memberId并且任务id=taskId的任务文件

    public List<TaskFile> getTaskFileListByTaskId(int taskId);//通过任务id获取任务文件列表

    public String modifyAddressById(int memberId);

    public boolean check(int id);

    public int modifyFileAddressById(TaskFile taskFile);
}
