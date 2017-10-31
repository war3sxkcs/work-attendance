package com.smallkk.user.service;

import com.smallkk.common.utils.MD5Utils;
import com.smallkk.user.dao.UserMapper;
import com.smallkk.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created By  醉美柳舞之众星捧月
 *
 * @author song
 * @date 2017/10/18 23:37
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * Author song  醉美柳舞之众星捧月
     * Date & Time  2017/10/19 22:58
     * Description  根据用户名来查询用户
     */
    @Override
    public User findUserByUserName(String username) {
        User user = userMapper.selectByUserName(username);
        return user;
    }

    /**
     * Author song  醉美柳舞之众星捧月
     * Date & Time  2017/10/19 23:06
     * Description  添加用户
     */
    @Override
    public void createUser(User user) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        user.setPassword(MD5Utils.encrptyPassword(user.getPassword()));
        userMapper.insertSelective(user);
    }
}
