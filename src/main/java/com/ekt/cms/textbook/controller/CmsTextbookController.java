package com.ekt.cms.textbook.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ekt.cms.common.BaseController;
import com.ekt.cms.common.entity.CmsKnowledge;
import com.ekt.cms.common.entity.Result;
import com.ekt.cms.common.service.ICmsKnowledgeService;
import com.ekt.cms.textbook.entity.CmsCatalog;
import com.ekt.cms.textbook.entity.CmsCatalogMessage;
import com.ekt.cms.textbook.entity.CmsTextbook;
import com.ekt.cms.textbook.service.ICmsCatalogService;
import com.ekt.cms.textbook.service.ICmsTextbookService;
import com.ekt.cms.utils.pageHelper.PageBean;
import com.ekt.cms.utils.pageHelper.PageContext;

/**
 * 2016-05-02
 * 
 * @author wanglan 教材控制器
 */
@Controller
@RequestMapping("/textbook")
public class CmsTextbookController extends BaseController {

	/**
	 * 返回教材页面
	 * 
	 * @return
	 */
	@Resource
	private ICmsTextbookService cmsTextbookService;

	@Resource
	private ICmsKnowledgeService cmsKnowledgeService;

	@Resource
	private ICmsCatalogService cmsCatalogService;

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
	@Transactional
	@RequestMapping("/delete")
	@ResponseBody
	public Result deleteTextbook(@RequestParam("ids") String ids) {
		Result result = Result.getResults();
		String[] arr = ids.split(",");
		int total = 0;
		for (String id : arr) {
			total += cmsTextbookService.deleteTextbook(Integer.parseInt(id));
			cmsCatalogService.deleteByTextbookId(Integer.parseInt(id));
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
		cmsTextbook.setInputAccountId(getCurrentAccount().getId());
		cmsTextbook.setIsFree(cmsTextbook.getPrice() == 0 ? 1 : 0);
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
	 * 
	 * @param cmsKnowledge
	 * @return
	 */
	@RequestMapping("/update")
	@ResponseBody
	public Result update(CmsTextbook cmsTextbook) {
		Result result = Result.getResults();
		cmsTextbook.setInputAccountId(getCurrentAccount().getId());
		cmsTextbook.setIsFree(cmsTextbook.getPrice() == 0 ? 1 : 0);
		result.setResult(cmsTextbookService.update(cmsTextbook));
		return result;
	}

	/**
	 * 获取textbook节的tree数据
	 * 
	 * @param textbookId
	 * @return
	 */
	@RequestMapping("/catalogTree")
	@ResponseBody
	public Result getCatalogTree(@RequestParam("textbookId") int textbookId) {
		Result result = Result.getResults();
		result.setValue(cmsTextbookService.getCatalogTree(textbookId));
		return result;
	}

	/**
	 * 拆分选课成单品
	 * 
	 * @return
	 */
	@RequestMapping("/splitTextbook")
	@ResponseBody
	@Transactional
	public Result splitTextbook(@RequestParam("ids") String arr) {
		Result result = Result.getResults();
		String[] ids = arr.split(",");
		String msg = "";
		if (ids.length > 0) {
			List<CmsCatalogMessage> data = cmsTextbookService.selectCatalogById(ids);
			if (data != null) {
				// cmsTextbookService.addTextbook(cmsTextbook)
				for (CmsCatalogMessage cm : data) {
					CmsTextbook textbook = new CmsTextbook();
					textbook.setTitle(cm.getCatalogName());
					textbook.setGradeNo(cm.getGradeNo());
					textbook.setSubjectNo(cm.getSubjectNo());
					textbook.setPhaseNo(cm.getPhaseNo());
					if (cmsTextbookService.listPage(textbook).size() > 0) {
						result.setResult(-1);
						msg += cm.getCatalogName() + ",";
						continue;
					} else {
						CmsTextbook book = cm.getTextbook();
						// 设置录入人
						book.setInputAccountId(getCurrentAccount().getId());
						// 1.添加选课 并返回该行id
						int result1 = cmsTextbookService.addTextbook(book);
						// 2.从cm中 构造 catalog 章
						CmsCatalog z = cm.getCatalog(book.getId(), 51, 0, 0);
						// 3.添加该选课的章并返回id
						int result2 = cmsCatalogService.add(z);
						// 4.从cm中构造 catalog 节
						CmsCatalog j = cm.getCatalog(book.getId(), 52, 0, z.getId());
						// 5.添加该选课节并
						int result3 = cmsCatalogService.add(j);
						if (result1 != 0 && result2 != 0 && result3 != 0) {
							result.setResult(1);
						} else {
							result.setMsg("添加失败！");
							result.setResult(-1);
						}
					}
				}
			} else {
				result.setResult(-1);
				result.setMsg("根据所选项获取数据异常 请联系管理员！");
			}
		}
		if (msg.length() > 0) {
			result.setResult(-1);
			result.setMsg(msg.substring(0, msg.length() - 1) + "已经存在无法添加！");
		}
		return result;
	}
	
	/**
	 * 推荐或取消推荐
	 * @param id
	 * @return
	 */
	@RequestMapping("/recommend")
	@ResponseBody
	public  Result recommend(int id){
		return Result.getResults(cmsTextbookService.recommendById(id));
		
	}
	
	
}
