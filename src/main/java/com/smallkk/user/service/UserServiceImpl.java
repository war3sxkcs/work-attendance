package com.smallkk.user.service;

import com.smallkk.common.utils.MD5Utils;
import com.smallkk.qiniu.QiNiuFileUploadUtils;
import com.smallkk.user.dao.UserMapper;
import com.smallkk.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
    public static final String QINIU_HEAD_IMG_BUCKET_NAME = "mamabike";
    public static final String QINIU_HEAD_IMG_BUCKET_URL = "p0cj2p6dz.bkt.clouddn.com";

    /***七牛keys end****/
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

    @Override
    public String uploadHeadImg(MultipartFile file, Long id) {
        //获取user 得到原来的头像地址
        User user = userMapper.selectByPrimaryKey(id);
        try {
            //调用七牛
            String headImg = QiNiuFileUploadUtils.uploadHeadImg(file);
            user.setHeadImage(headImg);
            //更新用户头像URL
            userMapper.updateByPrimaryKeySelective(user);
            return QINIU_HEAD_IMG_BUCKET_URL + "/" + QINIU_HEAD_IMG_BUCKET_NAME + headImg;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
