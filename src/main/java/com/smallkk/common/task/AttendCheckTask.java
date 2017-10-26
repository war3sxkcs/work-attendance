package com.smallkk.common.task;

import com.smallkk.attend.service.AttendService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created By  醉美柳舞之众星捧月
 *
 * @author song
 * @date 2017/10/25 21:09
 */
public class AttendCheckTask {
    @Autowired
    private AttendService attendService;


    public void checkAttend() {
        // 首先获取没有数据的人;
        // 给没有打卡的人 插入一条记录 并且设置为异常 缺勤480分钟
        // 如果有打卡记录 检查早晚打卡 看看考勤是不是正常

        attendService.checkAttend();

    }
}
