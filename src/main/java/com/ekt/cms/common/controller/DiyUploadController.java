package com.ekt.cms.common.controller;


import javax.servlet.http.HttpServletRequest;
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
@RequestMapping("/batch/upload")
public class DiyUploadController {
	
	
	private final String OSS_FILE_PATH ="http://ekt.oss-cn-shenzhen.aliyuncs.com/exercise/";
	
	private final String KEY="exercise/";
	
		 
	@RequestMapping(value ="/img", method = RequestMethod.POST)
	@ResponseBody
	public Result batchUploadImg(HttpServletRequest request){
		Result result = null;
		// 获取原始文件名
		String sourceName = null;
		try {
			result = Result.getResults();
			// 方便地得到文件名和文件内容
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			// 获得文件：
			MultipartFile file = multipartRequest.getFile("file");
			sourceName = file.getOriginalFilename();
			System.out.println("上传文件类型为:" + sourceName.substring(sourceName.lastIndexOf(".")) + "文件名为:" + sourceName);
			String realKey = KEY + file.getOriginalFilename();
			OSSClient  client = new OSSClient(Constants.DEFAULT_OSS_ENDPOINT, 
					   Constants.DEFAULT_OSS_ACCESS_KEY_ID, 
					   Constants.DEFAULT_OSS_ACCESS_KEY_SECRET);
			client.putObject(new PutObjectRequest(OssUploadSample.bucketName, realKey, file.getInputStream()));
			result.setResult(1);
//			result.setMsg("&lt;//exercise/"+sourceName+"//&gt;");
			result.setMsg(OSS_FILE_PATH+sourceName);
		   return result;
		
		} catch (Exception e) {
			e.printStackTrace();
			result.setResult(-1);
			result.setMsg(sourceName+"上传失败！");
			return result;
		}
		
		
		
		
	}

}
