package com.ekt.cms.region.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/test")
public class TestController {
	@RequestMapping(method=RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("bootstrap/login");
        view.addObject("message", "Say hi for Freemarker.");
        return view;
    }
}
