package com.ekt.cms.video.controller;

import java.io.File;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ekt.cms.common.entity.Result;
import com.ekt.cms.qcloud.QcloudApiModuleCenter;
import com.ekt.cms.qcloud.Module.Vod;
import com.ekt.cms.qcloud.Module.VodInfo;
import com.ekt.cms.qcloud.Utilities.SHA1;
import com.ekt.cms.qcloud.Utilities.Json.JSONArray;
import com.ekt.cms.qcloud.Utilities.Json.JSONObject;
import com.ekt.cms.utils.Constants;
import com.ekt.cms.utils.DateUtil;
import com.ekt.cms.utils.FileUtil;
import com.ekt.cms.video.entity.CmsVideo;

@Controller
@RequestMapping("/VodCloud")
public class VodCloud {
	//视频上传到第三方
	@RequestMapping(value = "/upload" , method = RequestMethod.POST) 
	public String    upload (HttpServletRequest request , RedirectAttributes arr){
		TreeMap<String, Object> config = new TreeMap<String, Object>();
		Result result=new Result();
		config.put("SecretId", Constants.DEFAULT_UPLOAD_SECRETID);
		config.put("SecretKey", Constants.DEFAULT_UPLOAD_SECRETKEY);
		config.put("RequestMethod", "POST");
		config.put("DefaultRegion", "gz");
		QcloudApiModuleCenter module = new QcloudApiModuleCenter(new Vod(), config);
		try{
			MultipartHttpServletRequest mulRequest = (MultipartHttpServletRequest) request;
			MultipartFile  file=mulRequest.getFile("videoFile");
		//创建文件保存路径
			String fileName=file.getOriginalFilename();
			String fileDir=DateUtil.getCurrentYM();
			String filePath=request.getSession().getServletContext().getRealPath("/")+fileDir+"\\"+fileName;
			System.out.println(filePath);
			String realPath = request.getSession().getServletContext().getRealPath("/")  + fileDir + "/";
		//当文件路径不存在的时候，创立路径
			FileUtil.makeDirs(realPath);
		//创建一个新文件
			File targetFile=new File(filePath);
		//将上传的文件先复制到targetFile
			if (!targetFile.exists()) {
				file.transferTo(targetFile);
			}
			long fileSize = targetFile.length();
//			int isTranscode=Integer.parseInt(request.getParameter("transcode"));//转码
//			int isWatermark=Integer.parseInt(request.getParameter("watermark"));//水印
			
		//视频文件的sha，采用SHA-1计算文件内容
			String fileSHA1 = SHA1.fileNameToSHA(filePath);
		//获取文件名的后缀 
			String fileType=fileName.substring(fileName.lastIndexOf(".")+1);
			int fixDataSize = 1024*1024*50;  //每次上传字节数，可自定义 1024*1024*50; 
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
//				params.put("isTranscode", isTranscode);
//				params.put("isWatermark", isWatermark);
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
					arr.addAttribute("fileId", fileId);
					return "redirect:/VodCloud/describeVodInfo";
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
		return "main/video/testUpload";
	}
	
	//获取视频的播放信息url 视频名称  时长等
	/**
	 * @param fileId
	 * @return
	 */
	@RequestMapping(value = "/describeVodInfo" ) 
	@ResponseBody
	public  Result  getUrl(String fileId) {
		
		Result result=new Result();
		CmsVideo video=new CmsVideo();
		//通过DescribeVodPlayUrls接口获得视频的播放地址
		TreeMap<String, Object> configUrl = new TreeMap<String, Object>();
		System.out.println("enter...");
		configUrl.put("SecretId", Constants.DEFAULT_UPLOAD_SECRETID);
		configUrl.put("SecretKey", Constants.DEFAULT_UPLOAD_SECRETKEY);
		configUrl.put("RequestMethod", "GET");
		configUrl.put("DefaultRegion", "gz");
		VodInfo vodUrl = new VodInfo();
		QcloudApiModuleCenter module = new QcloudApiModuleCenter(vodUrl, configUrl);
		
		//通过DescribeVodInfo接口获得视频信息 如视频名称 时长 视频封面图片URL等
		TreeMap<String, Object> configInfo = new TreeMap<String, Object>();
		System.out.println("enter...");
		configInfo.put("SecretId", Constants.DEFAULT_UPLOAD_SECRETID);
		configInfo.put("SecretKey", Constants.DEFAULT_UPLOAD_SECRETKEY);
		configInfo.put("RequestMethod", "GET");
		configInfo.put("DefaultRegion", "gz");
		VodInfo vodInfo = new VodInfo();
		QcloudApiModuleCenter moduleInfo = new QcloudApiModuleCenter(vodInfo, configInfo);
		
		try{
			System.out.println("starting...");
				
				//给DescribeVodPlayUrls传参并调用
				String resultUrl = null;
				TreeMap<String, Object> params = new TreeMap<String, Object>();
				params.put("fileId", fileId);
				resultUrl = module.call("DescribeVodPlayUrls", params);
				JSONObject jsonObject=new JSONObject(resultUrl);
				int code=jsonObject.getInt("code");
				
				//给DescribeVodInfo传参并调用
				String resultInfo = null;
				TreeMap<String, Object> paramsInfo = new TreeMap<String, Object>();
				paramsInfo.put("fileIds.1", fileId);
				resultInfo = moduleInfo.call("DescribeVodInfo", paramsInfo);
				JSONObject jsonObjectInfo=new JSONObject(resultInfo);
				int codeInfo=jsonObjectInfo.getInt("code");
				
				if(code==0&&codeInfo==0){
					//从DescribeVodPlayUrls接口中获得URL
					JSONArray playSet=jsonObject.getJSONArray("playSet");//playSet是一个数组 元素是一个json对象
					System.out.println("返回的结果为DescribeVodPlayUrls------------"+playSet);
					JSONObject playSetJson = playSet.getJSONObject(0);// 取playSet得第一元素是需要的结果集   
					String url=playSetJson.getString("url");
					video.setUrl(url);
					
					//从DescribeVodInfo接口中获得视频名称 时长 ID  目前时长取出来是0 
					JSONArray fileSet=jsonObjectInfo.getJSONArray("fileSet");//fileSet与playSet一样
					JSONObject fileSetJson=fileSet.getJSONObject(0);
					System.out.println("返回的结果为DescribeVodInfo------------"+fileSetJson);
					String videoId=fileSetJson.getString("fileId");
					String fileName=fileSetJson.getString("fileName");
					String fileNameReal=fileName.substring(0,fileName.lastIndexOf("."));//去掉后缀名
					int  duration=fileSetJson.getInt("duration");
					video.setVideoId(videoId);
					video.setFileName(fileNameReal);
					video.setDuration(duration);
					
					System.out.println("返回的结果为DescribeVodInfo------------"+resultInfo);
					result.setValue(video);
					return result;
				}
		}
		catch (Exception e) {
			e.printStackTrace();
			result.setMsg("获取信息失败");
			System.out.println("error...");
		}
		System.out.println("end...");
		return result;
	}
}
