package com.four.serviceimpl;

import com.four.dao.CollectionTaskDao;
import com.four.daoimpl.CollectionTaskDaoImpl;
import com.four.entity.CollectionTask;
import com.four.entity.PageBean;
import com.four.service.CollectionTaskService;

import java.sql.SQLException;
import java.util.List;

public class CollectionTaskServiceImpl implements CollectionTaskService {
    private CollectionTaskDao dao=new CollectionTaskDaoImpl();


    @Override
    public List<CollectionTask> findAll() throws SQLException {
        //调用dao完成查询
        return dao.findAll();
    }

    @Override
    public void addTask(CollectionTask task) throws SQLException {
        dao.addCollectionTask(task);
    }

    @Override
    public void deleteCollectionTask(String id) throws SQLException {
        dao.deleteCollectionTaskById(Integer.parseInt(id));
    }

    @Override
    public CollectionTask findCollectionTaskById(String id) throws SQLException {
        return dao.findCollectionTaskById(Integer.parseInt(id));
    }

    @Override
    public void updateTask(CollectionTask task) throws SQLException {
        dao.modefyCollectionTaskById(task);
    }

    @Override
    public void delSelectedCollectionTask(String[] ids) throws SQLException {
        for (String id : ids) {
            dao.deleteCollectionTaskById(Integer.parseInt(id));
        }
    }

    @Override
    public PageBean<CollectionTask> findCollectionTaskByPage(String _currentPage, String _rows) throws SQLException {

        int currentPage=Integer.parseInt(_currentPage);
        int rows=Integer.parseInt(_rows);


        //创建空的PageBean对象
        PageBean<CollectionTask> pb=new PageBean<CollectionTask>();
        //设置参数
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);

        //调用dao查询总记录数
        int totalCount=dao.findTotalCount();
        pb.setTotalCount(totalCount);

        //调用dao查询List集合
        int start=(currentPage-1)*rows;
        List<CollectionTask> list=dao.findByPage(start,rows);
        pb.setList(list);

        //计算总页码
        int totalPage=(totalCount % rows) ==0 ? totalCount/rows : (totalCount/rows) + 1;
        pb.setTotalPage(totalPage);

        return pb;
    }
}
