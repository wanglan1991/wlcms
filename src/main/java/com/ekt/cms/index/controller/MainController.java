package com.ekt.cms.index.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * test
 * */
@Controller
@RequestMapping("/main")
public class MainController {
	@RequestMapping("/index")
public String index(){
	
	return "main/index";
}
}
