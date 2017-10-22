package com.smallkk.attend.service;

import com.smallkk.attend.dao.AttendMapper;
import com.smallkk.attend.entity.Attend;
import com.smallkk.common.utils.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created By  醉美柳舞之众星捧月
 *
 * @author song
 * @date 2017/10/22 16:55
 */
@Service("attendServiceImpl")
public class AttendServiceImpl implements AttendService {
    private Log log = LogFactory.getLog(AttendServiceImpl.class);
    @Autowired
    private AttendMapper attendMapper;

    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    /**
     * 判定上下午
     */
    private int NOON_HOUR = 12;
    private int NOON_MINUTERS = 00;

    @Override
    public void signAttend(Attend attend) throws Exception {
        try {
            Date today = new Date();
            attend.setAttendDate(today);  //打卡时间获取服务器当前系统时间
            attend.setAttendWeek((byte) DateUtils.getTodayWeek());
            //查询当天已经有了的打卡记录
            Attend todayRecord = attendMapper.selectTodaySignRecord(attend.getUserId());
            Date noon = DateUtils.getDate(NOON_HOUR, NOON_MINUTERS);
            if (todayRecord == null) {
                //今天的打卡记录还不存在
                if (today.compareTo(noon) <= 0) {
                    //打卡时间早于12点;判定是上午打卡
                    attend.setAttendMorning(today);
                } else {
                    attend.setAttendEvening(today);
                }
                attendMapper.insertSelective(attend);
            } else {
                if (today.compareTo(noon) <= 0) {
                    //如果今天早上有过打卡;那么在上午再打卡就不管了,以最早的一条有效
                    return;
                } else {
                    todayRecord.setAttendEvening(today);
                    // 下午打卡是以最晚的一条为准
                    attendMapper.updateByPrimaryKeySelective(todayRecord);
                }
            }
            //中午12点之前打卡都算早上打开
            //12点以后都算下午打卡
            //不足8小时都算异常
            //要存数据库缺席了多长时间
            //如果9.30以后打卡,直接算异常
        } catch (Exception e) {
            log.error("用户签到异常", e);
            throw e;
            //如果不抛出去;事务将无法回滚    事务是基于异常来回滚的
        }
    }
}
