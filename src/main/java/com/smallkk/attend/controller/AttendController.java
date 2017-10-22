package com.smallkk.attend.controller;

import com.smallkk.attend.entity.Attend;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created By  醉美柳舞之众星捧月
 *
 * @author song
 * @date 2017/10/22 16:17
 */
@Controller
@RequestMapping("attend")
public class AttendController {
    @RequestMapping()
    public String attend() {
        return "attend";
    }

    @RequestMapping()
    public String sign(@RequestBody Attend attend) {
        return "";
    }
}
