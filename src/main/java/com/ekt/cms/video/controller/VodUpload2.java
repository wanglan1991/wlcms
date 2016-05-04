package com.ekt.cms.video.controller;

import java.io.File;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.ekt.cms.common.entity.Result;
import com.ekt.cms.qcloud.QcloudApiModuleCenter;
import com.ekt.cms.qcloud.Module.Vod;
import com.ekt.cms.qcloud.Utilities.SHA1;
import com.ekt.cms.qcloud.Utilities.Json.JSONObject;
import com.ekt.cms.utils.Constants;
import com.ekt.cms.utils.DateUtil;
import com.ekt.cms.utils.FileUtil;

@Controller
@RequestMapping("/VodUpload2")
public class VodUpload2 {
	@RequestMapping(value = "/upload2" , method = RequestMethod.POST) 
	public String  upload (HttpServletRequest request){
	TreeMap<String, Object> config = new TreeMap<String, Object>();
		
		config.put("SecretId", Constants.DEFAULT_UPLOAD_SECRETID);
		config.put("SecretKey", Constants.DEFAULT_UPLOAD_SECRETKEY);
		config.put("RequestMethod", "POST");
		config.put("DefaultRegion", "gz");
		QcloudApiModuleCenter module = new QcloudApiModuleCenter(new Vod(), config);
		try{
			System.out.println("starting...");
			String fileName = "F:\\MyDownload\\VID_20160102_130211.mp4";
			long fileSize = new File(fileName).length();
			String fileSHA1 = SHA1.fileNameToSHA(fileName);
			
			int fixDataSize = 1024*1024*50;  //每次上传字节数，可自定义
			int firstDataSize = 1024*512;    //最小片字节数（默认不变）
			int tmpDataSize = firstDataSize;
			long remainderSize = fileSize;
			int tmpOffset = 0;
			int code, flag;
			String fileId;
			String result = null;
			
			while (remainderSize>0) {
				TreeMap<String, Object> params = new TreeMap<String, Object>();
				params.put("fileSha", fileSHA1);
				params.put("fileType", "mp4");
				params.put("fileName", "jimmyTest");
				params.put("fileSize", fileSize);
				params.put("dataSize", tmpDataSize);
				params.put("offset", tmpOffset);
				params.put("file", fileName);
				
				result = module.call("MultipartUploadVodFile", params);
				System.out.println(result);
				JSONObject json_result = new JSONObject(result);
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
			System.out.println("end...");
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("error...");
		}
		return "main/video/manage";
	}
}
