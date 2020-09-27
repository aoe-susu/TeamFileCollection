package com.four.service;

import com.four.entity.Application;

import java.sql.SQLException;
import java.util.List;

public interface ApplicationService {
    public int deleteApplicationId(int id) ;
    public List<Application> getApplyApplicationListByteamId(int teamId) throws SQLException;
    public int addApplication(Application application);

    public Application getApplication(int applicationId);
}
