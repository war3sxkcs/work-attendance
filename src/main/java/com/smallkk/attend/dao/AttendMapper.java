package com.smallkk.attend.dao;

import com.smallkk.attend.entity.Attend;

public interface AttendMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Attend record);

    int insertSelective(Attend record);

    Attend selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Attend record);

    int updateByPrimaryKey(Attend record);

    Attend selectTodaySignRecord(Long userId);
}