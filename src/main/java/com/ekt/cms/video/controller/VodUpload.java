package com.ekt.cms.video.controller;

import java.io.File;
import java.io.InputStream;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ekt.cms.common.entity.Result;
import com.ekt.cms.qcloud.QcloudApiModuleCenter;
import com.ekt.cms.qcloud.Module.Vod;
import com.ekt.cms.qcloud.Utilities.SHA1;
import com.ekt.cms.qcloud.Utilities.Json.JSONObject;
import com.ekt.cms.utils.Constants;

@Controller
@RequestMapping("/VodUpload")
public class VodUpload {
	@RequestMapping(value = "/upload" ) 
//	public String  upload (@RequestParam("filePath") MultipartFile  file, HttpServletRequest request){
	public Result  upload(@RequestParam("filePath")String  filePath) {
		TreeMap<String, Object> config = new TreeMap<String, Object>();
		Result result=new Result();
		config.put("SecretId", Constants.DEFAULT_UPLOAD_SECRETID);
		config.put("SecretKey", Constants.DEFAULT_UPLOAD_SECRETKEY);
		config.put("RequestMethod", "POST");
		config.put("DefaultRegion", "cs");
		QcloudApiModuleCenter module = new QcloudApiModuleCenter(new Vod(), config);
		try{
			//传文件过来的方法
//			 MultipartHttpServletRequest mulRequest = (MultipartHttpServletRequest) request;			
//			 InputStream inpustream=file.getInputStream();
//			long fileSize = file.getSize();
//			String filePath=file.getAbsolutePath();
			
			//传路径过来的方法
			File file=new File(filePath);
			long fileSize = file.length();
			//视频文件的sha，采用SHA-1计算文件内容
			String fileSHA1 = SHA1.stringToSHA(filePath);
			//这里的fileNameBak用来取文件名的后缀 
			String fileName=file.getName();
			String fileType=fileName.substring(fileName.lastIndexOf(".")+1);
			System.out.println(fileName);
			int fixDataSize = 1024*1024*50;  //每次上传字节数，可自定义
			int firstDataSize = 1024*512;    //最小片字节数（默认不变）
			int tmpDataSize = firstDataSize;
			long remainderSize = fileSize;
			int tmpOffset = 0;
			int code, flag;
			String fileId;
			String resultJson = null;
			
			while (remainderSize>0) {
				TreeMap<String, Object> params = new TreeMap<String, Object>();
				params.put("fileSha", fileSHA1);
				params.put("fileType", fileType);
				params.put("fileName", fileName);
				params.put("fileSize", fileSize);
				params.put("dataSize", tmpDataSize);
				params.put("offset", tmpOffset);
				params.put("file", filePath);
				
				resultJson = module.call("MultipartUploadVodFile", params);
				
				JSONObject json_result = new JSONObject(resultJson);
				System.out.println(resultJson);
				result.setValue(json_result);
				code = json_result.getInt("code");
				if (code == -3002) {               //服务器异常返回，需要重试上传(offset=0, dataSize=512K)
					tmpDataSize = firstDataSize;
					tmpOffset = 0;
					continue;
				} else if (code != 0) {
					return null;
				}
				flag = json_result.getInt("flag");
				if (flag == 1) {
					fileId = json_result.getString("fileId");
					System.out.println(fileId);
					break;
				} else {
					tmpOffset = Integer.parseInt(json_result.getString("offset"));
				}
				remainderSize = fileSize - tmpOffset;
				if (fixDataSize < remainderSize) {
					tmpDataSize = fixDataSize;
				} else {
					tmpDataSize = (int) remainderSize;
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
