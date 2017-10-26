package com.smallkk.attend.dao;

import com.smallkk.attend.entity.Attend;
import com.smallkk.vo.QueryCondition;

import java.util.List;

public interface AttendMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Attend record);

    int insertSelective(Attend record);

    Attend selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Attend record);

    int updateByPrimaryKey(Attend record);

    Attend selectTodaySignRecord(Long userId);

    int countByCondition(QueryCondition condition);

    List<Attend> selectAttendPage(QueryCondition condition);


    List<Long> selectTodayAbsence();

    void batchInsert(List<Attend> attendList);

    List<Attend> selectTodayEveningAbsence();
}