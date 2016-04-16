package com.ekt.cms.region.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ekt.cms.common.BaseController;
import com.ekt.cms.region.entity.Region;
import com.ekt.cms.region.service.IRegionService;
import com.ekt.cms.utils.ValidatorUtils;
import com.ekt.cms.utils.page.Pagination;

@Controller
@RequestMapping(value="/region")  //地域controller 
public class RegionController extends BaseController {
	/** 日志实例 */
    private static final Logger logger = Logger.getLogger(RegionController.class);
    
    @Resource
    private IRegionService regionService;
    
    @RequestMapping(value = "/addRegion", method = RequestMethod.POST)
    public @ResponseBody Object addRegion(Region region) {
    	Map<String, Object> map = new HashMap<String, Object>();
    	System.out.println(region.getRegionName());
    	
    	try {
    		String message = ValidatorUtils.validate(region);
			Integer i = 0;
			if (StringUtils.isBlank(message)) {
				i = regionService.addRegion(region);
				
				if(i>0) {
					map.put("msg", "保存成功");
				}
			}else {
				map.put("msg", message);
			}
		} catch (Exception e) {
			map.put("msg", "网络错误，请稍后再试！");
			e.printStackTrace();
		}
    	
        return map;
    }
    
    @RequestMapping("/toListRegion")
    public String tolistRegion() {
//    	return "region/regionPageList";
    	return "main/region/regionPage";
    }
    
    @SuppressWarnings("unchecked")
	@RequestMapping("/listRegionPage")
    public List<Region> listRegion(Region a, Pagination pagination, HttpServletRequest request ,HttpServletResponse response)  throws Exception{
    	try{
    		Region region=new Region();
    		region.setId(1);
    		String sEcho = request.getParameter("sEcho");
    		int iDisplayStart = 0;
//    				Integer.decode(request.getParameter("iDisplayStart"));  //开始数字
    		int iDisplayLength = 3;
//    		Integer.decode(request.getParameter("iDisplayLength"));  //页大小
    		pagination.setRows(iDisplayLength);
        	int page = iDisplayStart/iDisplayLength + 1;
        	pagination.setPage(page);
        	
        	List<Region> regionList = regionService.listPage(region, pagination);
        	List<Region> returnList = new ArrayList<Region>();
        	
        	if(regionList != null && regionList.size() > 0) {
	        	for(int i=0; i<regionList.size(); i++) {
	        		Region oneRegion = regionList.get(i);
	        		String parentName = regionService.queryByKey(oneRegion.getParentId()).getRegionName();
	        		oneRegion.setParentName(parentName);
	        		if(oneRegion.getLevel().equals("0")) {
	        			oneRegion.setLevelName("国家");
	        		}
	        		if(oneRegion.getLevel().equals("1")) {
	        			oneRegion.setLevelName("省");
	        		}
	        		if(oneRegion.getLevel().equals("2")) {
	        			oneRegion.setLevelName("市");
	        		}
	        		if(oneRegion.getLevel().equals("3")) {
	        			oneRegion.setLevelName("区");
	        		}
	        		if(oneRegion.getLevel().equals("4")) {
	        			oneRegion.setLevelName("街道");
	        		}
	        		returnList.add(oneRegion);
	        	}
        	}
        	super.printStr(returnList, pagination, response,sEcho);
        	
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return null;
		//System.out.println(regionList);
		//return regionList;
    }
    
    //删除单条地域数据
    @RequestMapping("/deleteRegion")
    @ResponseBody
    public int deleteRegion(HttpServletRequest request ,HttpServletResponse response) {
    	int result = 0;
    	try{
    		int id = Integer.decode(request.getParameter("id"));
    		result = regionService.deleteByKey(id);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	return result;
    }
    
    //根据ID修改单条记录
    @RequestMapping("/updateRegion")
    @ResponseBody
    public int updateRegion(HttpServletRequest request ,HttpServletResponse response) {
    	int result = 0;
    	try{
    		int id = Integer.decode(request.getParameter("updateId"));
    		String regionName = request.getParameter("updateRegionName");
    		
    		Region region = regionService.queryByKey(id);
    		region.setRegionName(regionName);
    		
    		result = regionService.updateByKey(region);
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	return result;
    }
    
    @RequestMapping("/loadCity")
    @ResponseBody
    public List<Region> loadCity(HttpServletRequest request ,HttpServletResponse response) {
    	int para = Integer.decode(request.getParameter("para"));
    	Region region = new Region();
    	region.setParentId(para);
    	List<Region> returnList = regionService.queryByCondition(region);
    	return returnList;
    }
    
    @RequestMapping("/queryByParentId")
    @ResponseBody
    public List<Region> queryByParentId(HttpServletRequest request ,HttpServletResponse response) {
    	int parentId = Integer.decode(request.getParameter("parentId"));
    	Region region = new Region();
    	region.setParentId(parentId);
    	List<Region> returnList = regionService.queryByCondition(region);
    	return returnList;
    }
    
    
}
