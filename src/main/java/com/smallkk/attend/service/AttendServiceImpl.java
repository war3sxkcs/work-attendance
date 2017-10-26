package com.smallkk.attend.service;

import com.smallkk.attend.dao.AttendMapper;
import com.smallkk.attend.entity.Attend;
import com.smallkk.common.page.PageQueryBean;
import com.smallkk.common.utils.DateUtils;
import com.smallkk.vo.QueryCondition;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private static final int NOON_HOUR = 12;
    private static final int NOON_MINUTERS = 00;
    /**
     * 早晚上班时间判定
     */
    private static final int MORNING_HOUR = 9;
    private static final int MORNING_MINUTE = 30;
    private static final int EVENING_HOUR = 18;
    private static final int EVENING_MINUTE = 30;
    /**
     * 缺勤一整天
     */
    private static final Integer ABSENCE_DAY = 480;
    /**
     * 考勤异常状态
     */
    private static final byte ATTEND_STATUS_ABNORMAL = 2;
    private static final byte ATTEND_STATUS_NORMAL = 1;

    @Override
    public void signAttend(Attend attend) throws Exception {
        try {
            Date today = new Date();
            attend.setAttendDate(today);
            //打卡时间获取服务器当前系统时间
            attend.setAttendWeek((byte) DateUtils.getTodayWeek());
            //查询当天已经有了的打卡记录
            Attend todayRecord = attendMapper.selectTodaySignRecord(attend.getUserId());
            Date noon = DateUtils.getDate(NOON_HOUR, NOON_MINUTERS);
            Date morningAttend = DateUtils.getDate(MORNING_HOUR, MORNING_MINUTE);
            if (todayRecord == null) {
                //今天的打卡记录还不存在
                if (today.compareTo(noon) <= 0) {
                    //打卡时间早于12点;判定是上午打卡
                    attend.setAttendMorning(today);
                    //计算打卡时间是不是迟到
                    if (today.compareTo(morningAttend) > 0) {
                        //大于九点半迟到了
                        attend.setAttendStatus(ATTEND_STATUS_ABNORMAL);
                        attend.setAbsence(DateUtils.getMinutes(morningAttend, today));
                    }
                } else {
                    attend.setAttendEvening(today);
                }
                attendMapper.insertSelective(attend);
            } else {
                if (today.compareTo(noon) <= 0) {
                    //如果今天早上有过打卡;那么在上午再打卡就不管了,以最早的一条有效
                    return;
                } else {
                    //晚上打卡
                    todayRecord.setAttendEvening(today);
                    //判断打卡时间是不是18.30以后是不是早退
                    Date eveningAttend = DateUtils.getDate(EVENING_HOUR, EVENING_MINUTE);
                    if (today.compareTo(eveningAttend) < 0) {
                        //早于下午六点半 早退
                        todayRecord.setAttendStatus(ATTEND_STATUS_ABNORMAL);
                        todayRecord.setAbsence(DateUtils.getMinutes(today, eveningAttend));
                    } else {
                        todayRecord.setAttendStatus(ATTEND_STATUS_NORMAL);
                        todayRecord.setAbsence(0);
                    }
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

    @Override
    public PageQueryBean listAttend(QueryCondition condition) {
        //根据条件查询 count 记录数目
        int count = attendMapper.countByCondition(condition);
        PageQueryBean pageResult = new PageQueryBean();
        if (count > 0) {
            pageResult.setTotalRows(count);
            pageResult.setCurrentPage(condition.getCurrentPage());
            pageResult.setPageSize(condition.getPageSize());
            List<Attend> attendList = attendMapper.selectAttendPage(condition);
            pageResult.setItems(attendList);
        }
        // 如果有记录  才去查询分页数据 没有相关记录数目 没必要去查分页数据
        return pageResult;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void checkAttend() {
        //查询缺勤用户ID 插入打卡记录 并且设置为异常 缺勤480分钟
        List<Long> userIdList = attendMapper.selectTodayAbsence();
        if (!CollectionUtils.isEmpty(userIdList)) {
            List<Attend> attendList = new ArrayList<Attend>();
            for (Long userId : userIdList) {
                Attend attend = new Attend();
                attend.setUserId(userId);
                attend.setAttendDate(new Date());
                attend.setAttendWeek((byte) DateUtils.getTodayWeek());
                attend.setAbsence(ABSENCE_DAY);
                attend.setAttendStatus(ATTEND_STATUS_ABNORMAL);
                attendList.add(attend);
            }
            attendMapper.batchInsert(attendList);
        }
        List<Attend> absenceList = attendMapper.selectTodayEveningAbsence();
        //一定要非空循环    保证 代码的健壮性
        if (!CollectionUtils.isEmpty(absenceList)) {
            for (Attend attend : absenceList) {
                attend.setAbsence(ABSENCE_DAY);
                attend.setAttendStatus(ATTEND_STATUS_ABNORMAL);
                attendMapper.updateByPrimaryKeySelective(attend);
            }
        }
    }
}
