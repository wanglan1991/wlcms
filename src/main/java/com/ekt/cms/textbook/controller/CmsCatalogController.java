package com.ekt.cms.textbook.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.runners.Parameterized.Parameter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ekt.cms.common.entity.Result;
import com.ekt.cms.textbook.entity.CmsCatalog;
import com.ekt.cms.textbook.service.ICmsCatalogService;
import com.ekt.cms.utils.pageHelper.PageBean;
import com.ekt.cms.utils.pageHelper.PageContext;

/**
 * 
 * @author wanglan 2016-05-05 教材目录控制器
 *
 */
@Controller
@RequestMapping("/catalog")
public class CmsCatalogController {

	@Resource
	private ICmsCatalogService cmsCatalogService;

	/**
	 * 可带参查询目录列表
	 * 
	 * @param page
	 * @param cmsCatalog
	 * @return PageBean<CmsCatalog>
	 */

	@RequestMapping("/pageList")
	@ResponseBody
	public PageBean<CmsCatalog> pageList(PageContext page, CmsCatalog cmsCatalog) {
		page.paging();
		return new PageBean<CmsCatalog>(cmsCatalogService.pageList(cmsCatalog));

	}

	/**
	 * 添加目录
	 * 
	 * @param cmsCatalog
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Result add(CmsCatalog cmsCatalog) {
		Result result = Result.getResults();
		List<Map<String,Object>> list=cmsCatalogService.queryBycatalogName(cmsCatalog.getCatalogName());
		if(list.size()!=0){
		String name=list.get(0).get("catalog_name").toString();
			if (name!=null&&cmsCatalog.getCatalogName().equals(name)){
				result.setResult(-1);
				result.setMsg(name+"已存在 ！");
				return result;
			}
		}
			result.setResult(cmsCatalogService.add(cmsCatalog));
		return result;
	}

	/**
	 * 根据id删除或批量删除
	 * 
	 * @param cmsCatalog
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Result delete(@RequestParam("ids") String ids) {
		Result result = Result.getResults();
		String[] arr = ids.split(",");
		int total = 0;
		for (String id : arr) {
			total += cmsCatalogService.delete(Integer.parseInt(id));
		}
		result.setResult(total);
		return result;
	}

	/**
	 * 根据id停用或启用目录
	 * 
	 * @param cmsCatalog
	 * @return
	 */
	@RequestMapping("/confine")
	@ResponseBody
	public Result confine(@RequestParam("id") Integer id, @RequestParam("status") Integer status) {
		Result result = Result.getResults();
		result.setResult(cmsCatalogService.confine(id, status));
		return result;
	}

	/**
	 * 根据id修改
	 * 
	 * @param cmsCatalog
	 * @return
	 */
	@RequestMapping("/update")
	@ResponseBody
	public Result update(CmsCatalog cmsCatalog) {
		Result result = Result.getResults();
		List<Map<String,Object>> list=cmsCatalogService.queryBycatalogName(cmsCatalog.getCatalogName());
		if(list.size()!=0){
		String name=list.get(0).get("catalog_name").toString();
			if (name!=null&&cmsCatalog.getCatalogName().equals(name)){
				result.setResult(-1);
				result.setMsg(name+"已存在 ！");
				return result;
			}
		}
		result.setResult(cmsCatalogService.updata(cmsCatalog));
		return result;
	}

}
