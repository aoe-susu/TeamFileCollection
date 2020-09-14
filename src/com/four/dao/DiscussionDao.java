package com.four.dao;

import com.four.entity.Discussion;

public interface DiscussionDao {
    public int addDiscussion(Discussion discussion);//添加讨论

    public int deleteDiscussionById(int id);//通过id删除讨论

    public int deleteDiscussionByTeamId(int teamId);//通过团队id删除讨论
}
