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
import com.ekt.cms.common.entity.CmsDict;
import com.ekt.cms.common.service.CmsIDictService;
import com.ekt.cms.utils.pageHelper.PageBean;
import com.ekt.cms.utils.pageHelper.PageContext;

@Controller
@RequestMapping(value="/dict")  //地域controller
public class CmsDictController {
	
	/** 日志实例 */
    
    @Resource
    private CmsIDictService dictService;
    
    @RequestMapping("/queryDictByCondition")
    @ResponseBody
    public List<CmsDict> queryDictByCondition(HttpServletRequest request ,HttpServletResponse response) {
		String type = request.getParameter("type");
    	CmsDict queryDict = new CmsDict();
    	queryDict.setType(type);
    	List<CmsDict> dictList = dictService.queryDictByCondition(queryDict);
    	
    	return dictList;
    }
    
    @RequestMapping("/toDictPage")
    public String toDictPage() {
    	return "dict/dict";
    }
    
    @SuppressWarnings("static-access")
	@RequestMapping("/listDictPage")
    @ResponseBody
    public PageBean<CmsDict> queryListDictPage(PageContext pageContext,HttpServletRequest request ,HttpServletResponse response) { 	
    	CmsDict queryDict = new CmsDict();
    	queryDict.setType("subject");
    	pageContext.paging();
		PageBean<CmsDict> returnPageBean = dictService.listDictPage(queryDict);
    	return returnPageBean;
    }
    
    
}
