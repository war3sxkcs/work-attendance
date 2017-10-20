package com.smallkk.login.controller;

import com.smallkk.common.utils.SecurityUtils;
import com.smallkk.user.entity.User;
import com.smallkk.user.service.UserService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
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

    //    文件下载
    @RequestMapping("/down")
    public ResponseEntity<byte[]> download() throws IOException {
        String path = "C://Users//song//Desktop//oracle.txt";     //  文件所在地和文件的名字格式
        File file = new File(path);
        HttpHeaders headers = new HttpHeaders();      //告诉Http头
        headers.setContentDispositionFormData("attachment", path);   //我这儿有一个附件  ;path是路径
        headers.setContentType(MediaType.TEXT_PLAIN);     // 这个附件是什么类型的
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
    }
}
