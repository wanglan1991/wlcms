package com.ekt.cms.common.controller;


import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.PutObjectRequest;
import com.ekt.cms.common.entity.Result;
import com.ekt.cms.utils.Constants;

/**
 * wanglan
 * 2016-10-18
 * @author Administrator
 * 批量上传控制器
 */



@Controller
@RequestMapping("/common")
public class CommonUploadController {

	
	
	@RequestMapping("/imgUpload")
	@ResponseBody
	public String uploadImg(HttpServletRequest request,HttpServletResponse response){		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile file = multipartRequest.getFile("imgFile");
		String sourceName = file.getOriginalFilename();
		String path = request.getSession().getServletContext().getRealPath("/WEB-INF/img/upload");
		System.out.println("上传文件类型为:" + sourceName.substring(sourceName.lastIndexOf(".")) + "文件名为:" + sourceName);
		try {
			
			 File targetFile = new File(path, sourceName);  
			 file.transferTo(targetFile);
			 
			 
//			InputStream input = file.getInputStream();
//			byte[] b = new byte[1048576];
//			int length = input.read(b);     
//			path += "\\" + sourceName;
//			// 文件流写到服务器端
//			FileOutputStream outputStream = new FileOutputStream(path);
//			outputStream.write(b, 0, length);
//			input.close();
//			outputStream.close();
			 
			 
		} catch (Exception e) {
			e.printStackTrace();
			
		}	
		return "/boss/img/upload/"+sourceName;
	}


}
