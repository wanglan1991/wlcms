package com.ekt.cms.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author sxjun
 * @time 2015/8/12 13:49
 */
@Controller
public class MainController {
    //跳转到主页
    @RequestMapping(value={"/",""})
    public String index() {
        return "main/index";
    }
}
