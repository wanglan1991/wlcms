package com.ekt.cms.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/dict")  //知识点controller
public class CmsKnowledgeController {
	 @RequestMapping("/toKnowledge")
	    public String toDictPage() {
	    	return "dict/knowledge";
	    }
}
