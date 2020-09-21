package com.four.service;

import com.four.entity.CollectionTask;
import com.four.entity.PageBean;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface CollectionTaskService {
    /**
     * 查询所有文档信息
     * @return
     */
    public List<CollectionTask> findAll() throws SQLException;

    /**
     * 保存task
     * @param task
     */
    void addTask(CollectionTask task) throws SQLException;

    /**
     * 根据id删除Task
     * @param id
     */
    void deleteCollectionTask(String id) throws SQLException;

    /**
     * 根据id查询
     * @param id
     * @return
     */
    CollectionTask findCollectionTaskById(String id) throws SQLException;

    /**
     * 修改任务信息
     * @param task
     */
    void updateTask(CollectionTask task) throws SQLException;

    /**
     * 批量删除用户
     * @param ids
     */
    void delSelectedCollectionTask(String[] ids) throws SQLException;

    /**
     * 分页条件查询
     * @param currentPage
     * @param rows
     * @param condition
     * @return
     */
    PageBean<CollectionTask> findCollectionTaskByPage(String currentPage, String rows, Map<String, String[]> condition) throws SQLException;
}
