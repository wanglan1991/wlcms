package com.ekt.cms.common.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.ekt.cms.common.entity.Result;
import com.ekt.cms.utils.Constants;

/**
 * This sample demonstrates how to upload/download an object to/from Aliyun OSS
 * using the OSS SDK for Java.
 */
@Controller
@RequestMapping("/upload")
public class OssUploadSample {

	public static String bucketName = "ekt";

	/**
	 * 
	 * @param request
	 * @param key
	 * @param fileName
	 * @return
	 * @throws IOException
	 * 
	 *             参数key是指ObjectName。例如文件名为123.jpg，那么ObjectName设置为123.jpg即可
	 *             如果123.jgp要放在abc下显示，key即ObjectName参数设置为：abc/123.jpg 即可 private
	 *             static String key = "textBook/";
	 * 
	 */
	@RequestMapping("/imageUpload")
	@ResponseBody
	public Result uploadImage(HttpServletRequest request, String key, String fileName) throws IOException {
		Map<String, Object> data = new HashMap<String, Object>();
		Result result = Result.getResults();
		OSSClient client = null;
		try {
			client = new OSSClient(Constants.DEFAULT_OSS_ENDPOINT, 
								   Constants.DEFAULT_OSS_ACCESS_KEY_ID, 
								   Constants.DEFAULT_OSS_ACCESS_KEY_SECRET);
			MultipartHttpServletRequest mulRequest = (MultipartHttpServletRequest) request;
			MultipartFile file = mulRequest.getFile(fileName);
			String sourceName = file.getOriginalFilename();
			String realKey = key + file.getOriginalFilename();
			PutObjectResult putObjectResult = client.putObject(new PutObjectRequest(bucketName, realKey, file.getInputStream()));
			if (putObjectResult != null) {
				data.put("imgUrl", "http://ekt.oss-cn-shenzhen.aliyuncs.com/"+key+sourceName);
				data.put("status", 0);
				data.put("msg", "上传成功");
				result.setValue(data);
			}
		} catch (NullPointerException oe) {
			data.put("status", 1);
			data.put("msg", "上传异常，请重新尝试！");
			result.setValue(data);
			return result;
		} catch (OSSException oe) {
			data.put("status", 1);
			data.put("msg", "访问对象存储服务出异常！");
			result.setValue(data);
			return result;
		} catch (ClientException ce) {
			data.put("status", 1);
			data.put("msg", "访问阿里云出异常！");
			result.setValue(data);
			return result;
		}catch ( ClassCastException cce) {
			data.put("status", 1);
			data.put("msg", "读取文件时异常！");
			result.setValue(data);
			return result;
			
		} finally {

			client.shutdown();
		}
		return result;
	}

}