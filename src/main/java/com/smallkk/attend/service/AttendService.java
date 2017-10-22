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
    void signAttend(Attend attend) throws Exception;

    PageQueryBean listAttend(QueryCondition condition);
}
