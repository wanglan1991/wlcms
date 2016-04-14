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
import com.ekt.cms.utils.pageHelper.PageBean;
import com.ekt.cms.utils.pageHelper.PageContext;

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
    
    @RequestMapping("/toDictPage")
    public String toDictPage() {
    	return "dict/dict";
    }
    
    @SuppressWarnings("static-access")
	@RequestMapping("/listDictPage")
    @ResponseBody
    public PageBean<Dict> queryListDictPage(HttpServletRequest request ,HttpServletResponse response) {
    	int limit = Integer.parseInt(request.getParameter("limit")); //一页显示多少条10
    	int offset = Integer.parseInt(request.getParameter("offset")); //从第几条开始显示10
    	
    	Dict queryDict = new Dict();
    	queryDict.setType("subject");
    	
    	PageContext pageContext = new PageContext();
    	int pageNum = offset/limit +1;
    	pageContext.setPageNum(pageNum);
    	pageContext.setPageSize(offset);
    	
//    	pageContext.setPageNum(2);
//    	pageContext.setPageSize(10);
    	
		PageBean<Dict> returnPageBean = dictService.listDictPage(queryDict, pageContext);
    	
    	return returnPageBean;
    }
}
