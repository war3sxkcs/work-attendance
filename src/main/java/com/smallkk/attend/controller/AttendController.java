package com.smallkk.attend.controller;

import com.smallkk.attend.entity.Attend;
import com.smallkk.attend.service.AttendService;
import com.smallkk.common.page.PageQueryBean;
import com.smallkk.vo.QueryCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created By  醉美柳舞之众星捧月
 *
 * @author song
 * @date 2017/10/22 16:52
 */
@Controller
@RequestMapping("attend")
public class AttendController {

    @Autowired
    private AttendService attendService;

    @RequestMapping
    public String toAttend() {
        return "attend";
    }

    @RequestMapping("/sign")
    @ResponseBody    // 返回是一个json数据
    public String signAttend(@RequestBody Attend attend) throws Exception {
        attendService.signAttend(attend);
        return "succ";
    }

    /**
     * Author song  醉美柳舞之众星捧月
     * Date & Time  2017/10/23 1:16
     * Description  考勤数据分页查询
     */
    @RequestMapping("/signList")
    @ResponseBody
    public PageQueryBean listAttend(QueryCondition condition) {
        PageQueryBean result = attendService.listAttend(condition);
        return result;
    }
}
