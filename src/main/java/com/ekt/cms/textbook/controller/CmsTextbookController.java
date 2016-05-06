package com.ekt.cms.textbook.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ekt.cms.common.entity.CmsKnowledge;
import com.ekt.cms.common.entity.Result;
import com.ekt.cms.common.service.ICmsKnowledgeService;

import org.springframework.web.bind.annotation.ResponseBody;


import com.ekt.cms.textbook.entity.CmsTextbook;
import com.ekt.cms.textbook.service.ICmsTextbookService;
import com.ekt.cms.utils.pageHelper.PageBean;
import com.ekt.cms.utils.pageHelper.PageContext;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Text;


/**
 * 2016-05-02
 * 
 * @author wanglan 教材控制器
 */
@Controller
@RequestMapping("/textbook")
public class CmsTextbookController {

	/**
	 * 返回教材页面
	 * 
	 * @return
	 */
	@Resource
	private ICmsTextbookService cmsTextbookService;

	@Resource
	private ICmsKnowledgeService cmsKnowledgeService;

	@RequestMapping("/manage")
	public String textbookManage() {
		return "/main/textbook/textbookManage";
	}

	/**
	 * 教材集合
	 * 
	 * @param pageContext
	 * @param cmsTextbook
	 * @return
	 */
	@RequestMapping("/pageList")
	@ResponseBody
	public PageBean<CmsTextbook> textbookPageList(PageContext pageContext, CmsTextbook cmsTextbook) {
		pageContext.paging();
		return new PageBean<CmsTextbook>(cmsTextbookService.listPage(cmsTextbook));
	}


	// 根据科目 年级查询知识点
	@RequestMapping("/knowledgeList")
	@ResponseBody
	public Result knowledgeList(CmsKnowledge cmsKnowledge) {
		Result result = Result.getResults();
		result.setValue(cmsKnowledgeService.knowledgelist(cmsKnowledge));
		return result;
	}

	/**
	 * 根据is删除教材
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Result deleteTextbook(@RequestParam("ids") String ids) {
		Result result = Result.getResults();
		String[] arr = ids.split(",");
		int total = 0;
		for (String id : arr) {
			total += cmsTextbookService.deleteTextbook(Integer.parseInt(id));
		}
		result.setResult(total);
		return result;
	}

	/**
	 * 根据年级，学科 加载知识点树
	 * 
	 * @return
	 */
	@RequestMapping(value = "/knowledgeTree")
	@ResponseBody
	public Result knowledgeTree(@RequestParam("gradeNo") Integer gradeNo,
			@RequestParam("subjectNo") Integer subjectNo) {
		Result result = Result.getResults();
		result.setValue(cmsKnowledgeService.knowledgeTree(gradeNo, subjectNo));
		return result;
	}

	/**
	 * 编辑时用的树
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/editKnowledgeTree")
	@ResponseBody
	public Result editKnowledgeTree(CmsTextbook cmsTextbook) {
		Result result = Result.getResults();
		Map<String, Object> map = new HashMap<String, Object>();
		// 将该教材中的知识点转换为数组
		String[] knowledgeArr = cmsTextbook.getKnowledgePointArr().split(",");
		// 通过该教材中的年级 ，科目获得知识点zTree插件需要的json格式数据
		List<Map<String, Object>> knowledgeList = cmsKnowledgeService.knowledgeTree(cmsTextbook.getGradeNo(),
				cmsTextbook.getSubjectNo());
		for (int i = 0; i < knowledgeList.size(); i++) {
			if (knowledgeList.get(i).get("id") == "0") {
				continue;
			}
			for (int j = 0; j < knowledgeArr.length; j++) {
				if (knowledgeList.get(i).get("id").equals(knowledgeArr[j])) {
					map = knowledgeList.get(i);
					Iterator<Entry<String, Object>> it = map.entrySet().iterator();
					while (it.hasNext()) {
						if (it.next().getKey().equals("checked")) {
							it.remove();
						}
					}
					map.put("checked", "true");
				}
			}

		}
		result.setValue(knowledgeList);
		return result;
	}

	/**
	 * 添加教材
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Result addTextbook(CmsTextbook cmsTextbook) {
		Result result = Result.getResults();
		result.setResult(cmsTextbookService.addTextbook(cmsTextbook));
		return result;
	}

	/**
	 * 停启用教材
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/confine")
	@ResponseBody
	public Result confine(@RequestParam("id") Integer id, @RequestParam("status") Integer status) {
		Result result = Result.getResults();
		result.setResult(cmsTextbookService.confine(id, status));
		return result;
	}
	
	/**
	 * 修改教材
	 * @param cmsKnowledge
	 * @return
	 */
	@RequestMapping("/update")
	@ResponseBody
	public Result update(CmsTextbook cmsTextbook){
		Result result = Result.getResults();
		result.setResult(cmsTextbookService.update(cmsTextbook));
		return result;
	}

}
