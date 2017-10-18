package com.smallkk.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created By  醉美柳舞之众星捧月
 *
 * @author song
 * @date 2017/10/19 0:03
 */
@Controller
@RequestMapping("login")
public class LoginController {

    @RequestMapping("/index")
    public String login() {
        return "login";
    }
}
