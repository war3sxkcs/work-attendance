package com.smallkk.user.controller;
import com.smallkk.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

}
