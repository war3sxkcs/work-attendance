package com.smallkk.workflow.controller;

import com.smallkk.workflow.service.ReAttendService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * Created By  醉美柳舞之众星捧月
 *
 * @author song
 * @date 2017/10/26 22:32
 */
@Controller
@RequestMapping("reAttend")
public class ReAttendController {

    @Autowired
    private ReAttendService reAttendService;

    /**
     * Author song  醉美柳舞之众星捧月
     * Date & Time  2017/10/26 22:39
     * Description  用户补签流程开始
     */
    @RequestMapping("/start")
    public void startReAttendFlow(Map varibles) {
        reAttendService.startReAttendFlow(varibles);
    }

    /**
     * Author song  醉美柳舞之众星捧月
     * Date & Time  2017/10/26 22:39
     * Description  组长登录账号后查看有什么流程执行到组长这个位置
     */
    @RequestMapping("/list")
    public List<Task> listReAttendFlow(Map varibles) {
        List<Task> tasks = reAttendService.listTasks(varibles);
        return tasks;
    }

    /**
     * Author song  醉美柳舞之众星捧月
     * Date & Time  2017/10/26 22:39
     * Description   组长决定;完成审批   不管是通过还是不通过
     */
    @RequestMapping("/approve/{taskId}")
    public void approveReAttendFlow(@PathVariable String taskId) {
        reAttendService.approve(taskId);
    }
}
