package com.smallkk.workflow.dao;

import com.smallkk.workflow.entity.ReAttend;

public interface ReAttendMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ReAttend record);

    int insertSelective(ReAttend record);

    ReAttend selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ReAttend record);

    int updateByPrimaryKey(ReAttend record);
}