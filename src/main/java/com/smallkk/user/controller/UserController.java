package com.smallkk.user.controller;

import com.smallkk.user.entity.User;
import com.smallkk.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created By  醉美柳舞之众星捧月
 *
 * @author song
 * @date 2017/10/18 23:02
 */
@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * Author song  醉美柳舞之众星捧月
     * Date & Time  2017/10/22 14:55
     * Description   打卡主页
     */
    @RequestMapping("/home")
    public String home() {
        return "home";
    }

    /**
     * Author song  醉美柳舞之众星捧月
     * Date & Time  2017/10/22 14:53
     * Description 获取用户的
     */
    @RequestMapping("userinfo")
    @ResponseBody   //  返回user对象自动转为json发送到前端
    public User getUser(HttpSession session) {
        User user = (User) session.getAttribute("userinfo");
        return user;
    }

    /**
     * Author song  醉美柳舞之众星捧月
     * Date & Time  2017/10/22 16:10
     * Description   登出方法; 摧毁session
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        //HttpSession的方式
        // session.invalidate();
        //摧毁session

        return "login";
    }
}
