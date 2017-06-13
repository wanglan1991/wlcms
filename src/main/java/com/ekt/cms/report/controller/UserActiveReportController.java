package com.ekt.cms.report.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ekt.cms.common.BaseController;
import com.ekt.cms.report.entity.UserActiveReport;
import com.ekt.cms.report.entity.UserActiveReportValidBean;
import com.ekt.cms.report.service.ICmsUserActiveReportService;
import com.ekt.cms.utils.pageHelper.PageBean;
import com.ekt.cms.utils.pageHelper.PageContext;
/**
 * 
 * @author wanglan
 *
 */
@Controller
@RequestMapping("/userActiveReport")
public class UserActiveReportController  extends BaseController{
	
	
	@Resource
	private ICmsUserActiveReportService userActiveReportService;
	
	
	
	@RequestMapping("/manage")
	public String userActiveReportManage() {
		return "/main/report/userActiveReportManage";
	}
	
	/**
	 * 统计用户活跃度 带分页 默认按日统计
	 * @param page
	 * @param quintessence
	 * @return UserActiveReportList
	 */
	@RequestMapping(value = "/listPage")
	@ResponseBody
	public  PageBean<UserActiveReport> listPage(PageContext page, UserActiveReportValidBean uarvb) {
		page.paging();
		return new PageBean<UserActiveReport>(userActiveReportService.getList(uarvb));
	}
	
	
	
	
	

}
