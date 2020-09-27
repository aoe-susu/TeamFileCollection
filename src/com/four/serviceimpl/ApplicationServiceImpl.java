package com.four.serviceimpl;

import com.four.dao.ApplicationDao;
import com.four.daoimpl.ApplicationDaoImpl;
import com.four.entity.Application;
import com.four.entity.TaskFile;
import com.four.service.ApplicationService;

import java.sql.SQLException;
import java.util.List;

public class ApplicationServiceImpl implements ApplicationService {
    private ApplicationDao applicationDao = new ApplicationDaoImpl();

    @Override
    public int deleteApplicationId(int id) {
        return applicationDao.deleteApplicationById(id);
    }
    @Override
    public int addApplication(Application application) {
        return applicationDao.addApplication(application);
    }

    @Override
    public Application getApplication(int applicationId){
        return applicationDao.getApplication(applicationId);
    }

    @Override
    public List<Application> getApplyApplicationListByteamId(int teamId) throws SQLException {
        return applicationDao.getApplyApplicationListByteamId(teamId);
    }

}
