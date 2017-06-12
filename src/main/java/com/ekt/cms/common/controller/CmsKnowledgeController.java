package com.ekt.cms.common.controller;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ekt.cms.common.entity.CmsKnowledge;
import com.ekt.cms.common.entity.Result;
import com.ekt.cms.common.service.ICmsKnowledgeService;
import com.ekt.cms.utils.pageHelper.PageBean;
import com.ekt.cms.utils.pageHelper.PageContext;
/**
 * 2016-05-02
 * 
 * @author zhuyanqiong 知识点控制器
 */
@Controller
@RequestMapping(value = "/knowledge") // 知识点controller
public class CmsKnowledgeController {

	@Resource
	private ICmsKnowledgeService cmsKnowledgeService;

	@RequestMapping("/toKnowledge")
	public String toDictPage() {
		return "dict/knowledge";
	}

	@RequestMapping("/listPage")
	@ResponseBody
	public PageBean<CmsKnowledge> knowledgeList(PageContext page, CmsKnowledge cmsKnowledge) {
		page.paging();
		return cmsKnowledgeService.listPage(cmsKnowledge);
	}
	
	
	@RequestMapping("/list")
	@ResponseBody
	public Result knowledgeList(CmsKnowledge cmsKnowledge){
		Result result =Result.getResults();
		result.setValue(cmsKnowledgeService.knowledgeList(cmsKnowledge));
		return result ;
	}
	
	@RequestMapping("/editKnowledge")
	@ResponseBody
	public Result update(CmsKnowledge cmsKnowledge) {
		Result result = Result.getResults();
		result.setResult(cmsKnowledgeService.updateByPrimaryKey(cmsKnowledge));
		return result;
	}

	/**
	 * 添加知识点
	 * @param cmsKnowledge
	 * @return
	 */
	@RequestMapping("/addKnowledge")
	@ResponseBody
	public Result insert(CmsKnowledge cmsKnowledge) {
		Result result = Result.getResults();
		List<String>list=new ArrayList<String>();
		String[]title=cmsKnowledge.getTitle().split(",");
		int rs =cmsKnowledge.getOrderNo();
		int total = 0;
		for(String str:title){
			if(str.equals("")){
				continue;
			}
			cmsKnowledge.setTitle(str);
			if(cmsKnowledgeService.queryByTitle(cmsKnowledge).size()!=0){ 
				list.add(str);	
				rs++;
			}else{
				cmsKnowledge.setTitle(str);
				cmsKnowledge.setOrderNo(rs);
				rs++;
				total+=cmsKnowledgeService.insert(cmsKnowledge);
			}
			
		}
		
		result.setResult(rs);
		result.setMsg("成功添加"+total+"个知识点！"+(list.size()>0?list.toString()+"已存在无法被添加！":""));
		return result;
	}

	@RequestMapping("/confine")
	@ResponseBody
	public Result confine(CmsKnowledge cmsKnowledge) {
		Result result = Result.getResults();
		result.setResult(cmsKnowledgeService.confine(cmsKnowledge));
		return result;
	}

	@RequestMapping("/delete")
	@ResponseBody
	public Result deletes(String ids) {
		Result result =  Result.getResults();
		String[] arr = ids.split(",");
		int total = 0;
		for (String id : arr) {
			total += cmsKnowledgeService.delete(Integer.parseInt(id));

		}
		result.setResult(total);
		return result;
	}
	 @RequestMapping("/queryByCondition")
	 @ResponseBody
	 public Result queryByCondition(CmsKnowledge cmsKnowledge){
	 Result result=Result.getResults();
	 result.setValue(cmsKnowledgeService.query(cmsKnowledge));
	 return result;
	 }
}
