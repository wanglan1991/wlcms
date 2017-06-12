package com.ekt.cms.agency.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ekt.cms.agency.entity.CmsAgency;
import com.ekt.cms.agency.service.CmsAgencyService;
import com.ekt.cms.agency.service.CmsIAgencyService;
import com.ekt.cms.exercise.entity.CmsExercise;
import com.ekt.cms.utils.pageHelper.PageBean;
import com.ekt.cms.utils.pageHelper.PageContext;
/**
 * 
* @Title: DistributorController.java 
* @Package Distributor 
* @Description: TODO(代理商管理) 
* @author wanglan
* @date 2017年5月9日 上午10:43:43 
* @version V1.0
 */

@Controller
@RequestMapping("/agency")
public class CmsAgencyController {
	
	@Resource
	private CmsIAgencyService agencyService;
	
	/**
	 * 动态资讯管理页面
	 * @return
	 */
	@RequestMapping("/manager")
	@ResponseBody
	public ModelAndView agencyManage(){
		return new ModelAndView("/main/agency/agencyManage");
	}
	
	@RequestMapping("/pageList")
	@ResponseBody
	public PageBean<CmsAgency> pageList(PageContext page, CmsAgency agency) {
		page.paging();
		return new PageBean<CmsAgency>(agencyService.getAgencyList(agency));
	}
	
	
	

}
