package com.smallkk.user.dao;

import com.smallkk.user.entity.User;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * Author song  醉美柳舞之众星捧月
     * Date & Time  2017/10/19 22:59
     * Description   根据用户名查询用户
     */
    User selectByUserName(String username);
}