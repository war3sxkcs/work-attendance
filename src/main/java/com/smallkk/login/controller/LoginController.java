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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

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
    public String CheckLogin(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        String username = request.getParameter("username");
        String pwd = request.getParameter("password");
        String remember = request.getParameter("remember");
        System.out.println(remember);
        //调用MD5
        User user = userService.findUserByUserName(username);
        if (user != null) {
            if (SecurityUtils.checkPassword(pwd, user.getPassword())) {
                //Session功能记住密码;   暂时无效
                if (remember.equals("1")) {
                    Cookie usernamecookie = new Cookie("username", username);
                    usernamecookie.setMaxAge(3600 * 24);
                    Cookie pwdcookie = new Cookie("password", pwd);
                    pwdcookie.setMaxAge(3600 * 24);
                    response.addCookie(usernamecookie);
                    response.addCookie(pwdcookie);
                }
                if (request.getCookies() != null) {
                    Cookie[] cookies = request.getCookies();
                    for (Cookie cookie : cookies) {
                        if (cookie.getName().equals("password")) {
                            pwd = cookie.getValue();
                        }
                    }
                }
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
    public String register(User user, HttpServletRequest request, HttpSession session) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        // @RequestBody 通过json 的形式把用户传进来
        String emailuser = request.getParameter("emailuser");
        String emailpwd = request.getParameter("emailpwd");
        String emailcode = request.getParameter("emailcode");
        String strcode = session.getAttribute("strCode").toString();
        String realname = request.getParameter("realname");
        String mobile = request.getParameter("mobile");
        if (emailcode.equals(strcode)) {
            user.setUsername(emailuser);
            user.setPassword(emailpwd);
            user.setRealName(realname);
            user.setMobile(mobile);
            userService.createUser(user);
            return "register_succ";
        } else {
            return "register_error";
        }
    }

    //    文件下载
    @RequestMapping("/down")
    public ResponseEntity<byte[]> download() throws IOException {
        String path = "\\static\\personal\\1.jpg";
        //  文件所在地和文件的名字格式
        File file = new File(path);
        HttpHeaders headers = new HttpHeaders();      //告诉Http头
        headers.setContentDispositionFormData("attachment", path);   //我这儿有一个附件  ;path是路径
        headers.setContentType(MediaType.IMAGE_JPEG);     // 这个附件是什么类型的
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
    }

    @RequestMapping("/authCode")
    public void getAuthCode(HttpServletRequest request, HttpServletResponse response, HttpSession session)
            throws IOException {
        int width = 63;
        int height = 37;
        Random random = new Random();
        //设置response头信息
        //禁止缓存
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        //生成缓冲区image类
        BufferedImage image = new BufferedImage(width, height, 1);
        //产生image类的Graphics用于绘制操作
        Graphics g = image.getGraphics();
        //Graphics类的样式
        g.setColor(this.getRandColor(200, 250));
        g.setFont(new Font("Times New Roman", 0, 28));
        g.fillRect(0, 0, width, height);
        //绘制干扰线
        for (int i = 0; i < 40; i++) {
            g.setColor(this.getRandColor(130, 200));
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int x1 = random.nextInt(12);
            int y1 = random.nextInt(12);
            g.drawLine(x, y, x + x1, y + y1);
        }
        //绘制字符
        String strCode = "";
        for (int i = 0; i < 4; i++) {
            String rand = String.valueOf(random.nextInt(10));
            strCode = strCode + rand;
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            g.drawString(rand, 13 * i + 6, 28);
        }
        //将字符保存到session中用于前端的验证
        session.setAttribute("strCode", strCode);
        g.dispose();
        ImageIO.write(image, "JPEG", response.getOutputStream());
        response.getOutputStream().flush();
    }

    //创建颜色
    Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
}
