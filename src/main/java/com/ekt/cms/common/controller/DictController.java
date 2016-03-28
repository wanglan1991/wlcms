package com.ekt.cms.common.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ekt.cms.common.BaseController;
import com.ekt.cms.common.entity.Dict;
import com.ekt.cms.common.service.IDictService;

@Controller
@RequestMapping(value="/dict")  //地域controller
public class DictController {
	
	/** 日志实例 */
    
    @Resource
    private IDictService dictService;
    
    @RequestMapping("/queryDictByCondition")
    @ResponseBody
    public List<Dict> queryDictByCondition(HttpServletRequest request ,HttpServletResponse response) {
		String type = request.getParameter("type");
    	Dict queryDict = new Dict();
    	queryDict.setType(type);
    	List<Dict> dictList = dictService.queryDictByCondition(queryDict);
    	
    	return dictList;
    }
}
