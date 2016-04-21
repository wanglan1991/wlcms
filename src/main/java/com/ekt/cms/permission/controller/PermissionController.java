package com.ekt.cms.permission.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ekt.cms.common.BaseController;
import com.ekt.cms.common.entity.Result;
import com.ekt.cms.permission.entity.CmsPermission;
import com.ekt.cms.permission.service.IPermissionService;
import com.ekt.cms.utils.pageHelper.PageBean;
import com.ekt.cms.utils.pageHelper.PageContext;

/**
 * 权限类
 * @author wanglan
 * 2016-04-20
 *
 */
@Controller
@RequestMapping(value="/permission")
public class PermissionController extends BaseController{
	
	
	
	@Resource
	private IPermissionService permissionService;

	/**
	 * 返回权限首页
	 * @return
	 */
@RequestMapping(value="/manage")
public String permission(){
	return "main/permission/manage";
}	

/**
 * 加载permission列表
 * @return
 */
@RequestMapping(value="/pageList")
@ResponseBody
public PageBean<CmsPermission> getListPage(PageContext page, CmsPermission cmsPermission) {
	page.paging();
	return new PageBean<CmsPermission>(permissionService.listPage(cmsPermission));
}

/**
 * 请求类型列表
 * @return
 */
@RequestMapping(value="/typeList")
@ResponseBody
public Result getTypeList(){
	Result result=Result.getResults();
	result.setValue(permissionService.getTypeList());
	return result;
}

/**
 * 请求父级列表
 */
@RequestMapping(value="/pidList")
@ResponseBody
public Result getPidList(@RequestParam("type")int type){
	Result result=Result.getResults();
	result.setValue(permissionService.getPidList(type));
	return result;
}

/**
 * 启用或停用
 * @param cmsPermission
 * @return
 */
@RequestMapping(value="/confine")
@ResponseBody
public Result confine(CmsPermission cmsPermission){
	Result result =Result.getResults();
	result.setResult(permissionService.confine(cmsPermission));
	return result;
	
}
/**
 * 添加权限
 * @param cmsPermission
 * @return
 */
@RequestMapping(value = "/addPermission")
@ResponseBody
public Result addPermission(CmsPermission cmsPermission){
	Result result =Result.getResults();
	result.setResult(permissionService.addPermission(cmsPermission));
	return result;
}

/**
 * 删除
 * @param ids
 * @return
 */
@RequestMapping(value = "/delete")
@ResponseBody
public Result delete(@RequestParam("ids")String ids){
	Result result=Result.getResults();
	String[]arr=ids.split(",");
	int total=0;
	for(int i=0;i<arr.length;i++){
		total+=permissionService.deleteCmsPermission(Integer.parseInt(arr[i].toString()));
	}
	result.setResult(total);
	return result;
	
}


}

