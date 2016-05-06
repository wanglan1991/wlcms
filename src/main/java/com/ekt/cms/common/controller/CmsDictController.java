package com.ekt.cms.common.controller;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ekt.cms.common.entity.CmsDict;
import com.ekt.cms.common.entity.Result;
import com.ekt.cms.common.service.ICmsDictService;
import com.ekt.cms.utils.pageHelper.PageBean;
import com.ekt.cms.utils.pageHelper.PageContext;
/**
 * 2016-04-22
 * 
 * @author zhuyanqiong 地域控制器
 */

@Controller
@RequestMapping(value="/dict")  //地域controller
public class CmsDictController {
	
	/** 日志实例 */
    
    @Resource
    private ICmsDictService dictService;
    
    @RequestMapping("/queryDictByCondition")
    @ResponseBody
    public Result queryDictByCondition(CmsDict queryDict) {	
    	Result result =Result.getResults();
    	List<CmsDict> dictList = dictService.queryDictByCondition(queryDict);    
    	 result.setValue(dictList);
    	return result;
    }
    
    @RequestMapping("/toDictPage")
    public String toDictPage() {
    	return "dict/dict";
    }
    
    @RequestMapping("/pageList")
    @ResponseBody
    public PageBean<CmsDict> queryListDictPage(PageContext page,CmsDict cmsDict,HttpServletRequest request) {
    	//获取前台URL传递过来的查询参数
    	page.paging();
    	PageBean<CmsDict> returnPageBean = dictService.listDictPage(cmsDict);
    	return returnPageBean;
    }
    
	/**
	 * 启用或停用用户
	 * @param cmsDict
	 * @return 
	 */
	@RequestMapping("/confine")
	@ResponseBody
	public Result dictConfine(CmsDict cmsDict){
		Result result =Result.getResults();
		result.setResult(dictService.confine(cmsDict));
		return result;		
	}
	/**
	 * 删除用户
	 */
//	@RequestMapping("/delete")
//	@ResponseBody
//	public Result dictDelete(@RequestParam("id") int id){
//		Result result=Result.getResults();
//		result.setResult(dictService.deleteByPrimaryKey(id));
//		return result;
//		
//	}
	/**
	 * 批量删除用户
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Result dictDeletes(@RequestParam("ids") String ids){
		Result result=Result.getResults();
		String[] arr=ids.split(",");
		int total=0;
		for(String id:arr){
			total+=dictService.deleteByPrimaryKey(Integer.parseInt(id));
		}
		result.setResult(total);
		return result;
		
	}
	/**
	 * 添加字典
	 */
	@RequestMapping("/addDict")
	@ResponseBody
	public Result dictAdd(CmsDict dict){
		Result result=Result.getResults();
		CmsDict cmsDict=dictService.queryByDictName(dict.getValue());
		if(cmsDict!=null){
			result.setMsg("字典值已存在");
			return result;
		}else{
			result.setResult(dictService.insert(dict));
		}
		return result;
	}
	/**
	 * 编辑字典
	 */
	@RequestMapping("/editDict")
	@ResponseBody
	public Result dictEdit(CmsDict dict){
		Result result=Result.getResults();
		List<CmsDict> cmsDict=dictService.queryDictByCondition(dict);
		if(cmsDict.size()>0){
			result.setMsg("该字典已存在");
			return result;}
		else
			{
		result.setResult(dictService.update(dict));
		}
		return result;
	}
	/**
	 * 页面条件查询
	 */
	@RequestMapping("/query")
	@ResponseBody
	public Result queryByCondition(CmsDict dict){
		Result result=Result.getResults();
		result.setResult(dictService.update(dict));
		return result;
	}
	/**
	 * 查询所有的字典类型名称
	 */
	@RequestMapping("/typeNameList")
	@ResponseBody
	public Result getTypeNameList(){
		Result result=Result.getResults();
		result.setValue(dictService.queryTypeName());
		return result;		
	}
}
