package com.ekt.cms.exercise.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.ekt.cms.common.BaseController;
import com.ekt.cms.common.entity.Result;
import com.ekt.cms.common.service.ICmsDictService;
import com.ekt.cms.common.service.ICmsKnowledgeService;
import com.ekt.cms.exercise.entity.CmsAnswer;
import com.ekt.cms.exercise.entity.CmsExercise;
import com.ekt.cms.exercise.service.ICmsAnswerService;
import com.ekt.cms.exercise.service.ICmsExerciseService;
import com.ekt.cms.utils.CMSConstants;

/**
 * 2016-04-28
 * 
 * @author wanglan 习题导入控制器
 */
@Controller
@RequestMapping("/upload")
public class CmsExerciseUploadController extends BaseController {
	
	@Resource
	private ICmsDictService dictService;

	@Resource
	private ICmsKnowledgeService cmsKnowledgeService;

	@Resource
	private ICmsAnswerService cmsAnswerService;

	@Resource
	private ICmsExerciseService cmsExerciseService;

	@RequestMapping(value = "/exercises", method = RequestMethod.POST)
	@ResponseBody
	public Result uploadExercise(HttpServletRequest request) {
		Result result = Result.getResults();
		// 方便地得到文件名和文件内容
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		// 获得文件：
		MultipartFile file = multipartRequest.getFile("fileData");
		// 获取原始文件名
		String sourceName = file.getOriginalFilename();
		System.out.println("上传文件类型为:" + sourceName.substring(sourceName.lastIndexOf(".")) + "文件名为:" + sourceName);
		

		String path = request.getSession().getServletContext().getRealPath("/WEB-INF/upload/");
		// ---------------------上--------传--------文--------件------------------------开始
		String msg = "";
		int total = 0;
		int accountId = 0;
		try {
			accountId = getCurrentAccount().getId();
			InputStream input = file.getInputStream();
			byte[] b = new byte[1048576];
			int length = input.read(b);
			boolean flag = false;
			path += "\\" + sourceName;
			// 文件流写到服务器端
			FileOutputStream outputStream = new FileOutputStream(path);
			outputStream.write(b, 0, length);
			input.close();
			outputStream.close();

			// ---------------------上--------传--------文--------件------------------------结束

			// ---------------------读--------取--------xlsx,xls文件------------------------开始
			// 构造 XSSFWorkbook 对象，strPath 传入文件路径
			XSSFWorkbook xwb = new XSSFWorkbook(path);

			// 读取第一章表格内容
			XSSFSheet sheet = xwb.getSheetAt(0);
			// 定义 row、cell
			XSSFRow row;
			String cell;

			if (sheet.getPhysicalNumberOfRows() < 3) {
				return new Result(-1, "【E】上传失败 ，请上传有效的“excel文件”");
			}
			// 循环输出表格中的内容
			for (int i = sheet.getFirstRowNum() + 2; i < sheet.getPhysicalNumberOfRows(); i++) {
				row = sheet.getRow(i);
				if (row.getPhysicalNumberOfCells() > 11) {
					msg+="【E】 超出录入规范的列数。\n";
					break;
					
				}
				CmsExercise exercise = new CmsExercise();
				for (int j = row.getFirstCellNum(); j < row.getPhysicalNumberOfCells(); j++) {
					cell = row.getCell(j).toString();
					if(j==0&&cell.equals("")){
						flag=true;
						break;
					}
					
					if (j == 0) {
						exercise.setIndex((int)Double.parseDouble(cell));
					} else if(j == 1 && cell.trim().length() > 0){
						exercise.setPhaseNo(dictService.exerciseQueryByDictNameAndEncoding(cell, CMSConstants.PHASE).getId());//设置学段
					}else if (j == 2 && cell.trim().length() > 0) {
						exercise.setGradeNo(dictService.exerciseQueryByDictNameAndEncoding(cell,CMSConstants.GRADE).getId());//设置年级					
					} else if (j == 3 && cell.trim().length() > 0) {
						exercise.setSubjectNo(dictService.exerciseQueryByDictNameAndEncoding(cell,CMSConstants.SUBJECT).getId());//设置科目
					} else if (j == 4 && cell.trim().length() > 0) {
						exercise.setTypeNo(dictService.exerciseQueryByDictNameAndEncoding(cell,CMSConstants.EXERCISE_TYPE).getId());//设置类型
					} else if (j == 5 && cell.trim().length() > 0) {
						exercise.setCategoryNo(dictService.exerciseQueryByDictNameAndEncoding(cell,CMSConstants.CATEGORY).getId());//设置题类
					} else if (j == 6 && cell.trim().length() > 0) {
						exercise.setDifficultyNo(dictService.exerciseQueryByDictNameAndEncoding(cell,CMSConstants.DIFFICULTY).getId());//设置难易度
					} else if (j == 7 && cell.trim().length() > 0) {
						exercise.setKnowledges(cell);
						exercise.setKnowledgeId(cmsKnowledgeService.getKnowledgeByName(cell).getId());
					} else if (j == 8 && cell.trim().length() > 0) {
						exercise.setContent(cell);
					} else if (j == 9) {
						if( cell.split("`").length>=2){
							String[] answerArr = cell.split("`");
							List<CmsAnswer> answerList = new ArrayList<CmsAnswer>();
							List<Integer> isTrueArr =new ArrayList<Integer>();
							for (int x = 0; x < answerArr.length; x++) {
								String[] temp = answerArr[x].toString().trim().split(",");
								Integer isTrue =Integer.parseInt(temp[2].toString());
								answerList.add(new CmsAnswer(temp[0], temp[1], isTrue, 1));
								if(isTrue==1){
									isTrueArr.add(isTrue);
								}
							}
							//没有给出正确答案时抛出
//							if(isTrueArr.size()==0){
//								exercise.setErrorMsg("至少需要给出一个正确的答案！");
//							}
//							if(isTrueArr.size()==cell.split("`").length){
//								exercise.setErrorMsg("至少需要给出一个错误的答案！");
//							}
							exercise.setOptions(answerList);
						}else{
							continue;
						}
						
					} else if (j == 10 && cell.trim().length() > 0) {
						exercise.setAnalysis(cell);
					}
				}
				if(flag){
					continue;
				}
				if ( exercise.getSubjectNo() == null
						|| exercise.getPhaseNo() ==null
						|| exercise.getDifficultyNo() == null 
						|| exercise.getCategoryNo() == null
						|| exercise.getContent() == null || exercise.getTypeNo() == null
						|| exercise.getOptions() == null||exercise.getErrorMsg()!=null){
					msg += "【E】习题序号为:[" + exercise.getIndex() + "] 添加失败！ 原因:";
					
					if(exercise.getOptions()==null){
						msg+="习题答案不小于2个。";					
					}
					if(exercise.getErrorMsg()!=null){
						msg+=exercise.getErrorMsg();
					}
					if(exercise.getCategoryNo()==null){
						msg+="题类、";
					}
					if(exercise.getPhaseNo()==null){
						msg+="学段、";
					}					
					if(exercise.getSubjectNo()==null){
						msg+="科目、";
					}
					if(exercise.getDifficultyNo()==null){
						msg+="难易度、";
					}
					if(exercise.getContent()==null){
						msg+="习题内容、";
					}
					if(exercise.getTypeNo()==null){
						msg+="题型、";
					}
					if(exercise.getOptions()!=null&&exercise.getErrorMsg()==null){
						msg=msg.substring(0, msg.length()-1)+" 参数不存在或为空";
					}	
					msg+="\n";
					
					continue;
				}
				exercise.setInputAccountId(accountId);
				
				
				
				if (cmsExerciseService.pageList(exercise).size()>0) {
					msg += "【W】习题序号为:[" + exercise.getIndex() + "] ，添加失败。原因:该习题已经存在，无法重复添加。\n";
				} else {
					int res = cmsExerciseService.insertExercise(exercise);
					if (res > 0) {
						total+=1;
						msg += "【I】习题序号为:[" + exercise.getIndex() + "] ，添加成功。\n";
						for (int v = 0; v < exercise.getOptions().size(); v++) {
							cmsExerciseService.insertAnswer(exercise.getId(), exercise.getOptions().get(v).getOption(),
									exercise.getOptions().get(v).getContents(),
									exercise.getOptions().get(v).getIsTrue());
						}
						exercise = null;
						
					} else {
						msg += "【W】习题序号为:[" + exercise.getIndex() + "] 添加失败，原因:插入数据库失败！\n";
					}

				}
				;

			}

		} catch (IOException e) {
			e.printStackTrace();
			return new Result(-1, msg+"【E】上传文件异常，请仔细检查文档是否按照规范录入，如依然还存在该问题 。请与管理员联系！\n");
		}catch (Exception e){
			e.printStackTrace();
			return new Result(-1, msg+"【E】上传文件异常，请仔细检查文档是否按照规范录入，如依然还存在该问题 。请与管理员联系！\n");
		}
		// ---------------------读--------取--------xlsx,xls文件------------------------结束

		File temp = new File(path);
		temp.delete();
		msg+="【I】 成功添加"+total+"个习题\n";
		return new Result(-1, msg);
	}

}
