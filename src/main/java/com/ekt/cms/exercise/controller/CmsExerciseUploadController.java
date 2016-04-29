package com.ekt.cms.exercise.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ekt.cms.common.entity.Result;
import com.ekt.cms.utils.excel.ImportExecl;

/**
 * 2016-04-28
 * @author wanglan
 *	习题导入控制器
 */
@Controller
@RequestMapping("/upload")
public class CmsExerciseUploadController {
	
@RequestMapping("/exercises")
@ResponseBody
public Result uploadExercise(@RequestParam("fileData") MultipartFile fileData, HttpServletRequest request){
	Result result=Result.getResults();
	//方便地得到文件名和文件内容
	MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
    // 获得文件： 
	MultipartFile file = multipartRequest.getFile("fileData");
	//获取原始文件名
	String sourceName = file.getOriginalFilename();
	System.out.println("上传文件类型为:"+sourceName.substring(sourceName.lastIndexOf("."))+"文件名为:"+sourceName);
//	//加入流
//	InputStream inputStream = null;
//	//加入工作簿
//	Workbook wb = null;
//	try {
//		
//		inputStream = fileData.getInputStream();
//		wb = new HSSFWorkbook(inputStream);
//		Sheet sheet = wb.getSheetAt(0);
//		int firstRowIndex = sheet.getFirstRowNum();
//		int lastRowIndex = sheet.getLastRowNum();
//		System.out.println("第一行:" + firstRowIndex + "最后一行:" + lastRowIndex);
//	} catch (IOException e) {
//		e.printStackTrace();
//		result.setMsg("IO异常！");
//		result.setResult(-1);
//		
//	}

	ImportExecl poi = new ImportExecl(); 
//	poi.read()
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	return result;
}
	

}
