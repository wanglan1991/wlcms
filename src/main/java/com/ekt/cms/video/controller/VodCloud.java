package com.ekt.cms.video.controller;

import java.io.File;
import java.util.HashMap;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
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
import com.ekt.cms.video.service.CmsVideoService;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.sun.javafx.collections.MappingChange.Map;
/**
 * 2016-05-02
 * 
 * @author zhuyanqiong 视频上传云点播控制器
 */
@Controller
@RequestMapping("/vodCloud")
public class VodCloud {
	@Resource
	private CmsVideoService cmsVideoService;
	//视频上传到第三方
	@RequestMapping(value = "/upload" , method = RequestMethod.POST) 
	@ResponseBody
	public Result  upload (HttpServletRequest request){
		Result result=Result.getResults();
		//调用接口的公共参数
		int error = 0;
		TreeMap<String, Object> config = new TreeMap<String, Object>();
		config.put("SecretId", Constants.DEFAULT_UPLOAD_SECRETID);
		config.put("SecretKey", Constants.DEFAULT_UPLOAD_SECRETKEY);
		config.put("RequestMethod", "POST");
		config.put("DefaultRegion", "gz");
		
			MultipartHttpServletRequest mulRequest = (MultipartHttpServletRequest) request;
			MultipartFile  file=mulRequest.getFile("videoFile");
			
			//调用DescribeVodPlayInfo 根据视频文件名fileName判断文件是否已上传
			String fileName=file.getOriginalFilename();
			QcloudApiModuleCenter moduleInfo = new QcloudApiModuleCenter(new VodInfo(), config);
			try{
				//给DescribeVodPlayInfo传参并调用
				String resultUrl = null;
				TreeMap<String, Object> params = new TreeMap<String, Object>();
				params.put("fileName", fileName);
				resultUrl = moduleInfo.call("DescribeVodPlayInfo", params);
				JSONObject jsonObject=new JSONObject(resultUrl);
				int code=jsonObject.getInt("code");
				if(code==0 ){
					//获取视频信息成功
					System.out.println("返回的结果为DescribeVodPlayInfo------------"+jsonObject);
					result.setMsg("“"+fileName+"” 已存在，无法重复上传。");
					return result;
				}
		}
		catch (Exception e) {
			e.printStackTrace();
			result.setMsg("判断视频是否已上传出异常");
			return result;
		}
			QcloudApiModuleCenter module = new QcloudApiModuleCenter(new Vod(), config);
    	try{	
			//创建文件保存路径
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
			
		//视频文件的sha，采用SHA-1计算文件内容
			String fileSHA1 = SHA1.fileNameToSHA(filePath);
		//获取文件名的后缀 
			String fileType=fileName.substring(fileName.lastIndexOf(".")+1);
			int fixDataSize = 1024*1024*5;  //每次上传字节数，可自定义 1024*1024*50; 
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
//				params.put("isTranscode", 1);//是否转码 0:否 1:是     默认0
//				params.put("isWatermark", isWatermark);
				params.put("file", filePath);
				resultJson = module.call("MultipartUploadVodFile", params);
				
				JSONObject json_result = new JSONObject(resultJson);
				System.out.println(resultJson);
				code = json_result.getInt("code");
				if (code == -3002) {               //服务器异常返回，需要重试上传(offset=0, dataSize=512K)
					error+=1;
					if(error==5){
							result.setResult(-1);
							result.setMsg("无法支持的文件上传协议！请修改文件名重新尝试上传。");
							return result;
						}
					tmpDataSize = firstDataSize;
					tmpOffset = 0;
					continue;
				} else if (code != 0) {
					result.setResult(-1);
					result.setMsg("上传异常");
					return result;
				}
				flag = json_result.getInt("flag");
				if (flag == 1) {
					CmsVideo video =new CmsVideo();
					video.setFileId(json_result.getString("fileId"));
					video.setFileName(fileName);
					result.setValue(video);
					result.setResult(1);
					return result;
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
			result.setResult(-1);
			return result;
		}
    	
		return result;
	}
	
	/**
	 * 根据视频id进行转码
	 * @param fileId
	 * @return
	 */
	@RequestMapping(value = "/transcode" ) 
	@ResponseBody
	public Result transcodeVideo(String  fileId,int type){
		Result result=Result.getResults();
		//调用接口的公共参数
				TreeMap<String, Object> config = new TreeMap<String, Object>();
				config.put("SecretId", Constants.DEFAULT_UPLOAD_SECRETID);
				config.put("SecretKey", Constants.DEFAULT_UPLOAD_SECRETKEY);
				config.put("RequestMethod", "POST");
				config.put("DefaultRegion", "gz");
				VodInfo vodInfo = new VodInfo();
				//通过ConvertVodFile接口对视频转码
				QcloudApiModuleCenter module = new QcloudApiModuleCenter(vodInfo, config);
				try{
					//给ConvertVodFile传参并调用
					String resultUrl = null;
					TreeMap<String, Object> params = new TreeMap<String, Object>();
					params.put("fileId", fileId);  
					params.put("notifyUrl", "http://112.74.105.4:8080/cms/vodCloud/describeVodInfo?fileId="+fileId+"&type="+type);
//						params.put("notifyUrl", "http://wanglan.tunnel.qydev.com/cms/vodCloud/describeVodInfo?fileId="+fileId+"&type="+type);
					/*
					 * 测试回调
					 *	params.put("notifyUrl", "http://fjsk.tunnel.qydev.com/cms/vodCloud/describeVodInfo?fileId="+fileId);
					 */
					resultUrl = module.call("ConvertVodFile", params);
					JSONObject jsonObject=new JSONObject(resultUrl);
					int code=jsonObject.getInt("code");
					String msg=jsonObject.getString("message");
					if(code==0){
						cmsVideoService.updateVideoTransStatusByFileId(fileId, 1);
						result.setMsg("提交转码成功！请耐心等待转码完成。");
					}else {
						result.setMsg(msg);
					}
			}
			catch (Exception e) {
				e.printStackTrace();
				result.setMsg("异常！转码失败！");
				return result;
			}
		return result;
		
	}
	/**
	 * 获取视频的播放信息url 视频名称  时长等
	 * @param fileId
	 * @return
	 */
	@RequestMapping(value = "/describeVodInfo" ) 
	@ResponseBody
	public  Result  getUrl(String fileId,int type) {
		Result result= Result.getResults();
		CmsVideo video=new CmsVideo();
		//调用接口的公共参数
		TreeMap<String, Object> config = new TreeMap<String, Object>();
		System.out.println("enter...");
		config.put("SecretId", Constants.DEFAULT_UPLOAD_SECRETID);
		config.put("SecretKey", Constants.DEFAULT_UPLOAD_SECRETKEY);
		config.put("RequestMethod", "GET");
		config.put("DefaultRegion", "gz");
		
		VodInfo vodInfo = new VodInfo();
		//通过DescribeVodPlayUrls接口获得视频的播放地址
		QcloudApiModuleCenter module = new QcloudApiModuleCenter(vodInfo, config);
		
		//通过DescribeVodInfo接口获得视频信息 如视频名称 时长 视频封面图片URL等
		QcloudApiModuleCenter moduleInfo = new QcloudApiModuleCenter(vodInfo, config);
		
		try{
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
					System.out.println("返回的结果为DescribeVodPlayUrls------------"+jsonObject);
					System.out.println("返回的结果为DescribeVodInfo------------"+jsonObjectInfo);
					JSONArray playSet=jsonObject.getJSONArray("playSet");//playSet是一个数组 元素是一个json对象
					System.out.println("返回的结果为DescribeVodPlayUrls------------"+playSet);
					JSONObject playSetJson = playSet.getJSONObject(0);// 取playSet得第一元素是需要的结果集   
					String url=playSetJson.getString("url");
					
					
					//从DescribeVodInfo接口中获得视频名称 时长 ID  目前时长取出来是0 
					JSONArray fileSet=jsonObjectInfo.getJSONArray("fileSet");//fileSet与playSet一样
					JSONObject fileSetJson=fileSet.getJSONObject(0);
					System.out.println("返回的结果为DescribeVodInfo------------"+fileSetJson);
					String videoId=fileSetJson.getString("fileId");
					String fileName=fileSetJson.getString("fileName");
					String fileNameReal=fileName.substring(0,fileName.lastIndexOf("."));//去掉后缀名
					int  duration=fileSetJson.getInt("duration");
								
					if(type==1){
						video.setUrl(url);
						video.setVideoKey(videoId);
						video.setFileName(fileNameReal);
						video.setDuration(duration);	
						cmsVideoService.updateByVideoKey(video);
					}else{
						video.setSubVideoKey(videoId);
						video.setSubUrl(url);
						cmsVideoService.updateVideoBySubKey(video);
					}
					
					
					result.setValue(video);
					return result;
				}
		}
		catch (Exception e) {
			e.printStackTrace();
			result.setMsg("根据视频ID获取信息失败");
			return result;
		}
		return result;
	}
}
