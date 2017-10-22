package com.smallkk.vo;

import com.smallkk.common.page.PageQueryBean;

/**
 * Created By  醉美柳舞之众星捧月
 *
 * @author song
 * @date 2017/10/23 1:13
 */
public class QueryCondition extends PageQueryBean {
    private Long userId;
    private String startDate;
    private String endDate;

    public QueryCondition() {
    }

    public QueryCondition(Long userId, String startDate, String endDate) {
        this.userId = userId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
