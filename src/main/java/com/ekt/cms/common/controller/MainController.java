package com.ekt.cms.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class MainController {
    //跳转到主页
    @RequestMapping(value={"/",""})
    public String index() {
        return "main/index";
    }
}
