package com.smallkk.attend.service;

import com.smallkk.attend.dao.AttendMapper;
import com.smallkk.attend.entity.Attend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created By  醉美柳舞之众星捧月
 *
 * @author song
 * @date 2017/10/22 16:31
 */
@Service("attendServiceImpl")
public class AttendServiceImpl implements AttendService {
    @Autowired
    private AttendMapper attendMapper;

    @Override
    public void signAttend(Attend attend) {
    }
}
