package com.smallkk.login.controller;

import com.smallkk.common.utils.SecurityUtils;
import com.smallkk.user.entity.User;
import com.smallkk.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created By  醉美柳舞之众星捧月
 *
 * @author song
 * @date 2017/10/19 0:03
 */
@Controller
@RequestMapping("login")
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * Author song  醉美柳舞之众星捧月
     * Date & Time  2017/10/19 22:57
     * Description  登录页面
     */
    @RequestMapping()
    public String login() {
        return "login";
    }

    /**
     * Author song  醉美柳舞之众星捧月
     * Date & Time  2017/10/19 22:24
     * Description  账号密码校验
     */

    @RequestMapping("/check")
    @ResponseBody    //  因为异步;要添加@ResponseBody注解; 否则就404
    public String CheckLogin(HttpServletRequest request) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String username = request.getParameter("username");
        String pwd = request.getParameter("password");

        //调用MD5
        User user = userService.findUserByUserName(username);
        if (user != null) {
            if (SecurityUtils.checkPassword(pwd, user.getPassword())) {
                //此处登录成功 可以设置session
                request.getSession().setAttribute("userinfo", user);
                return "login_succ";
            } else {
                //返回失败信号
                return "login_fail";
            }
        } else {
            return "login_fail";
        }
    }

    /**
     * Author song  醉美柳舞之众星捧月
     * Date & Time  2017/10/19 23:03
     * Description  注册 植入用户
     */
    @RequestMapping("/register")
    @ResponseBody
    public String register(@RequestBody User user) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        // @RequestBody 通过json 的形式把用户传进来
        userService.createUser(user);
        return "succ";
    }
}
