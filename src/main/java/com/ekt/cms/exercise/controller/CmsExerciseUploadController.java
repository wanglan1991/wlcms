package com.ekt.cms.exercise.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.aliyun.oss.common.comm.ServiceClient.Request;
import com.ekt.cms.common.entity.Result;
import com.ekt.cms.exercise.entity.CmsExercise;

/**
 * 2016-04-28
 * @author wanglan
 *	习题导入控制器
 */
@Controller
@RequestMapping("/upload")
public class CmsExerciseUploadController {
	
@RequestMapping(value="/exercises",method=RequestMethod.POST)
@ResponseBody
public Result uploadExercise(HttpServletRequest request,CmsExercise cmsExercise){
	Result result=Result.getResults();
	//方便地得到文件名和文件内容
	MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
    // 获得文件： 
	MultipartFile file = multipartRequest.getFile("fileData");
	//获取原始文件名
	String sourceName = file.getOriginalFilename();
	System.out.println("上传文件类型为:"+sourceName.substring(sourceName.lastIndexOf("."))+"文件名为:"+sourceName);
	
	 String path = request.getSession().getServletContext().getRealPath("/WEB-INF/upload/");
// ---------------------上--------传--------文--------件------------------------开始
	 try {
		InputStream input = file.getInputStream();
		  byte[] b = new byte[1048576];
		  int length = input.read(b);
		  path += "\\" + sourceName;
		  // 文件流写到服务器端
		  FileOutputStream outputStream = new FileOutputStream(path);
		  outputStream.write(b, 0, length);
		  input.close();
		  outputStream.close();
	} catch (IOException e) {
		e.printStackTrace();
	}
// ---------------------上--------传--------文--------件------------------------结束 
	 
	 
	 
// ---------------------读--------取--------xlsx,xls文件------------------------开始	 
	// 构造 XSSFWorkbook 对象，strPath 传入文件路径  
		XSSFWorkbook xwb;
		try {
			xwb = new XSSFWorkbook(path);
		
		// 读取第一章表格内容  
		XSSFSheet sheet = xwb.getSheetAt(0);  
		// 定义 row、cell  
		XSSFRow row;  
		String cell;  
		// 循环输出表格中的内容  
		for (int i = sheet.getFirstRowNum(); i < sheet.getPhysicalNumberOfRows(); i++) {  
		    row = sheet.getRow(i);  
		    for (int j = row.getFirstCellNum(); j < row.getPhysicalNumberOfCells(); j++) {  
		        // 通过 row.getCell(j).toString() 获取单元格内容，  
		        cell = row.getCell(j).toString();  
		        System.out.print(cell + "\t");  
		    }  
		    System.out.println("");  
		}  
		} catch (IOException e) {
			e.printStackTrace();
		}  
// ---------------------读--------取--------xlsx,xls文件------------------------结束	
		
		File temp = new File(path);
		temp.delete();
	    return result;
}










	

}
