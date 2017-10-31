package com.smallkk.workflow.controller;

import com.smallkk.user.entity.User;
import com.smallkk.workflow.entity.ReAttend;
import com.smallkk.workflow.service.ReAttendService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

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
     * Date & Time  2017/10/27 12:15
     * Description 补签数据页面
     */
    @RequestMapping
    public String toReAttend(Model model, HttpSession session) {
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("userinfo");
        List<ReAttend> reAttendList = reAttendService.listReAttend(user.getUsername());
        model.addAttribute("reAttendList", reAttendList);
        return "reAttend";
    }

    /**
     * Author song  醉美柳舞之众星捧月
     * Date & Time  2017/10/26 22:39
     * Description  用户补签流程开始
     */
    @RequestMapping("/start")
    public void startReAttendFlow(@RequestBody ReAttend reAttend, HttpSession session) {
//        User user = (User) session.getAttribute("userinfo");
//        reAttend.setReAttendStarter(user.getRealName());
        reAttend.setReAttendStarter("123");
        reAttendService.startReAttendFlow(reAttend);
    }

    /**
     * Author song  醉美柳舞之众星捧月
     * Date & Time  2017/10/26 22:39
     * Description  组长登录账号后查看有什么流程执行到组长这个位置
     */

    //   @RequiresPermissions("reAttend:list") 是基于权限列表来判断权限
    //  @RequiresRoles("leader") 是根据角色来判断权限
    //   两者都可以


//    @RequiresRoles("leader")
    @RequiresPermissions("reAttend:list")
    @RequestMapping("/list")
    public String listReAttendFlow(Model model, HttpSession session) {
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("userinfo");
        String userName = user.getUsername();
        List<ReAttend> tasks = reAttendService.listTasks(userName);
        model.addAttribute("tasks", tasks);
        return "reAttendApprove";
    }

    /**
     * Author song  醉美柳舞之众星捧月
     * Date & Time  2017/10/26 22:39
     * Description   组长决定;完成审批   不管是通过还是不通过
     */
    @RequestMapping("/approve")
    public void approveReAttendFlow(@RequestBody ReAttend reAttend) {
        reAttendService.approve(reAttend);
    }
}
