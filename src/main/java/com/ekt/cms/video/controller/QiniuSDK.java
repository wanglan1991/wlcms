package com.ekt.cms.video.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ekt.cms.common.BaseController;
import com.ekt.cms.common.entity.Result;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

/**
 * 
 * @author wanglan
 * @date 2016-09-14 16:06
 * 
 *       七牛云存储
 *
 */



@Controller
@RequestMapping("/qiniu")
public class QiniuSDK extends BaseController{

	// 设置好账号的ACCESS_KEY和SECRET_KEY
	String ACCESS_KEY = "icv6DGmbYyhUjIrldr6Z8wwffCzvDkJFs3nc8TxU";
	String SECRET_KEY = "hWEw0l0O8H1PPrTcBjAqAd_XwezHvMyE5zJFRUTt";
	String bucketname = "video";
	Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
	UploadManager uploadManager = new UploadManager();
//
//	// 简单上传，使用默认策略，只需要设置上传的空间名就可以了
	public String getUpToken() {
//		return auth.uploadToken(bucketname);
		
		return auth.uploadToken(bucketname
//				  ,null,3600,new StringMap()
//		          .put("callbackUrl","http://your.domain.com/callback")
//		          .put("callbackBody", "filename=$(fname)&filesize=$(fsize)")
		          );
	}

//	public void upload1() throws Exception {
//		try {
//			// 调用put方法上传
//			com.qiniu.http.Response res = uploadManager.put(FilePath, key, getUpToken());
//			// 打印返回的信息
//			System.out.println(res.bodyString());
//		} catch (QiniuException e) {
//			com.qiniu.http.Response r = e.response;
//			// 请求失败时打印的异常的信息
//			System.out.println(r.toString());
//			try {
//				// 响应的文本信息
//				System.out.println(r.bodyString());
//			} catch (QiniuException e1) {
//				// ignore
//			}
//		}
//	}
	

	
	
	
	@RequestMapping(value ="/uploadByQiNiu", method = RequestMethod.POST)
	@ResponseBody
	public Result upload(HttpServletRequest request){
		MultipartHttpServletRequest mulRequest = (MultipartHttpServletRequest) request;
		MultipartFile file = mulRequest.getFile("videoFile");
		String sourceName = file.getOriginalFilename();
		String path = request.getSession().getServletContext().getRealPath("/WEB-INF/upload/");
		 try {
				InputStream input = file.getInputStream();
				  byte[] b = new byte[10485760];
				  int length = input.read(b);
				  path += "\\" + sourceName;
				  // 文件流写到服务器端
				  FileOutputStream outputStream = new FileOutputStream(path);
				  outputStream.write(b, 0, length);
				  input.close();
				  outputStream.close();
				  
				  com.qiniu.http.Response res;
				  try {
						res = uploadManager.put(path, sourceName, getUpToken());
						
						} catch (QiniuException e) {						
							 res = e.response;
							 System.out.println(res.bodyString());
						}
	  
			} catch (IOException e) {
				e.printStackTrace();
			}
		
			
		
		
		
		
		
		
		
		
		
		
		
		
		
		return null;

	}

}
