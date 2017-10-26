package com.smallkk.attend.service;

import com.smallkk.attend.entity.Attend;
import com.smallkk.common.page.PageQueryBean;
import com.smallkk.vo.QueryCondition;

/**
 * Created By  醉美柳舞之众星捧月
 *
 * @author song
 * @date 2017/10/22 16:54
 */
public interface AttendService {
    /**
     * 签到
     *
     * @param attend
     * @throws Exception
     */
    void signAttend(Attend attend) throws Exception;

    /**
     * 分页查询
     *
     * @param condition
     * @return
     */
    PageQueryBean listAttend(QueryCondition condition);

    /**
     * 任务调度
     */
    void checkAttend();
}
