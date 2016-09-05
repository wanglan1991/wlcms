package com.ekt.cms.video.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ekt.cms.common.entity.Result;
import com.ekt.cms.openapi.sdk.util.LetvApiUtil;
import com.ekt.cms.utils.Constants;
import com.ekt.cms.utils.DateUtil;
import com.ekt.cms.utils.FileUtil;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;
import net.sf.json.JSONObject;

@SuppressWarnings("deprecation")
@Controller
@RequestMapping("/LetvController")
public class LetvController {
	/**
	 * 初始化上传视频
	 * @param videoName
	 * @param file
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/initUpload")
	@ResponseBody
	public Result initUpload(HttpServletRequest request) throws Exception{
		Result result=Result.getResults();
		try{
			String apiUri = Constants.LECLOUD_API_URI;
        	String api = "video.upload.init";
			
//			File file = new File("D:\\516221120.mp4");
			 //MultipartFile
	        MultipartHttpServletRequest mulRequest = (MultipartHttpServletRequest) request;
			MultipartFile  file=mulRequest.getFile("videoFile");
			
			String video_name=file.getOriginalFilename();
			   //创建文件保存路径
					String fileDir=DateUtil.getCurrentYM();
					String filePath=request.getSession().getServletContext().getRealPath("/")+fileDir+"\\"+video_name;
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
					
            Map<String, String> params = new HashMap<String, String>();
            //视频上传前调用，获取正式上传时需要的一些信息
            params.put("video_name", "jerry");//视频名称
            params.put("client_ip", Constants.LECLOUD_CLIENT_IP);//用户IP地址。为了保证用户上传速度，建议将用户公网IP地址写入该参数
            params.put("file_size", String.valueOf(targetFile.length()));//文件大小，单位为字节
            params.put("uploadtype", "0");//是否分片上传，0不分片，1分片；默认0。如用js编写上传功能须使用分片模式。
            params.put("isdownload", "0");//是否支持缓存上传标志,默认0，0 不支持缓存 1支持缓存。（注：离线下载为移动端功能）
            params.put("isdrm", "0");//是否支持DRM上传标志,默认0，不支持drm。（注：html5 不支持播放加密视频，不加密可进行播放，加密之后不支持离线下载）
            params.put("ispay", "0");//是否付费标志,默认0，不付费。
            
            
            HttpResponse  httpResponse = LetvController.executePost(apiUri, api, params, null);
        	int status = httpResponse.getStatusLine().getStatusCode();
        	System.out.println("status--->  " + status);
        	HttpEntity httpEntity =  httpResponse.getEntity();
        	String respContent = EntityUtils.toString(httpEntity);
        	System.out.println("respContent--->  " + respContent);
        	Map<String,Object> map = (Map<String, Object>) JSONObject.fromObject(respContent);
        	
        	int code = (int)map.get("code");
        	if(code==0){
        		//成功
        		String data = map.get("data").toString();
        		Map<String,String> datamap = (Map<String, String>)JSONObject.fromObject(data);
        		
        		String videoId = datamap.get("video_id");
        		String videoUnique = datamap.get("video_unique");
        		String uploadUrl = datamap.get("upload_url");
        		String progressUrl = datamap.get("progress_url");
        		String token = datamap.get("token");
        		
        		//上传视频到乐视
        		HttpResponse upResp = null;
				try {
					upResp = LetvController.postMultipartForObject(uploadUrl, "", Constants.LECLOUD_VER, null, targetFile);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		int upstatus = upResp.getStatusLine().getStatusCode();
    			if(upstatus==200){
    				HttpEntity httpEntity2 =  upResp.getEntity();
    	        	String respContent2 = null;
					try {
						respContent2 = EntityUtils.toString(httpEntity2);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    	        	//解析返回参数
    	        	Map<String,Object> upmap = (Map<String, Object>) JSONObject.fromObject(respContent2);
    				System.out.println("上传结果----------------------"+respContent2);
    	        	int upcode = (int)upmap.get("code");
    	        	if(upcode==0){
    	        		//上传成功
    	        		System.out.println("上传成功");
    	        		result.setMsg("上传成功");
    	        	}else{
    	        		//上传失败
    	        		result.setMsg("上传失败");
    	        		System.out.println("上传失败");
    	        	}
    			}
        	}
		}catch(Exception e){
			e.printStackTrace();
			result.setMsg("上传异常");
		}
		return result;
	}
	
	/**
	 * get方式提交，用于查询，速度更快
	 * @param apiUri
	 * @param api
	 * @param params
	 * @param headers
	 * @return
	 * @throws IOException
	 */
	public static HttpResponse executeGet(String apiUri,String api, Map<String, String> params, Map<String, String> headers) throws IOException {
		HttpResponse response = null;
		try{
			Map<String, String> map = new HashMap<String, String>();
	        //系统参数
	        map.put("user_unique", Constants.LECLOUD_USER_UNIQUE);
	        map.put("timestamp", String.valueOf(System.currentTimeMillis()));
	        map.put("api", api);
	        map.put("format", Constants.LECLOUD_FORMAT);//返回参数格式：支持json和xml两种方式
	        map.put("ver", Constants.LECLOUD_VER);
	        
	        if (params != null) {
	            map.putAll(params);
	        }
	        String sign = LetvApiUtil.digest(map, Constants.LECLOUD_SECRET);
	        map.put("sign", sign);
	        
	        StringBuilder tmp = new StringBuilder(apiUri);

	        if (map != null && map.size() > 0) {
	            tmp.append("?");
	            Set<Entry<String, String>> entries = map.entrySet();
	            for (Iterator<Entry<String, String>> iterator = entries.iterator(); iterator.hasNext(); ) {
	                Entry<String, String> entry = iterator.next();
	                tmp.append(entry.getKey());
	                tmp.append("=");
	                try {
	                    tmp.append(java.net.URLEncoder.encode(entry.getValue(), "UTF-8"));
	                } catch (Exception e) {
	                    throw new RuntimeException(e);
	                }
	                if (iterator.hasNext()) {
	                    tmp.append("&");
	                }
	            }
	        }

	        apiUri = tmp.toString();
	        HttpGet get = new HttpGet(apiUri);
	        if (headers != null && headers.size() > 0) {
	            for (Entry<String, String> entry : headers.entrySet()) {
	                get.addHeader(entry.getKey(), entry.getValue());
	            }
	        }
	        CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(RequestConfig.DEFAULT).build();
	        response = httpClient.execute(get);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return response;
	}
	
	/**
	 * 普通post提交
	 * @param params：form表单提交参数
	 * @param headers：header设置参数
	 * @return
	 */
	public static HttpResponse executePost(String apiUri,String api, Map<String, String> params, Map<String, String> headers) throws IOException {
        Map<String, String> map = new HashMap<String, String>();
        //系统参数
        map.put("user_unique", Constants.LECLOUD_USER_UNIQUE);
        map.put("timestamp", String.valueOf(System.currentTimeMillis()));
        map.put("api", api);
        map.put("format", Constants.LECLOUD_FORMAT);//返回参数格式：支持json和xml两种方式
        map.put("ver", Constants.LECLOUD_VER);
        
        if (params != null) {
            map.putAll(params);
        }
        String sign = LetvApiUtil.digest(map, Constants.LECLOUD_SECRET);
        map.put("sign", sign);
        
        if (headers == null) {
            headers = new HashMap<String, String>();
        }
        headers.put("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        
        List<BasicNameValuePair> data = new ArrayList<BasicNameValuePair>();
        if (map != null && map.size() > 0) {
            for (Entry<String, String> entry : map.entrySet()) {
                data.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }
        
        //除非特殊说明，接口地址统一为：http://api.letvcloud.com/open.php
        //防止特殊情况，故没有写死
        HttpPost post = new HttpPost(apiUri);
        post.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        post.setEntity(new UrlEncodedFormEntity(data, "UTF-8"));
    	CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(RequestConfig.DEFAULT).build();
    	HttpResponse  httpResponse = httpClient.execute(post);
    	return httpResponse;
	}
	
	/**
	 * 上传视频
	 * @param postUri：上传文件post的地址，由video.upload.init返回的upload_url确定
	 * @param api
	 * @param ver
	 * @param params
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static HttpResponse postMultipartForObject(String postUri,String api, String ver, Map<String, String> params, File file) throws IOException {
		Map<String, String> map = new HashMap<String, String>();
        //系统参数
        map.put("user_unique", Constants.LECLOUD_USER_UNIQUE);
        map.put("timestamp", String.valueOf(System.currentTimeMillis()));
        map.put("api", api);
        map.put("format", Constants.LECLOUD_FORMAT);//返回参数格式：支持json和xml两种方式
        map.put("ver", Constants.LECLOUD_VER);
        
        if (params != null) {
            map.putAll(params);
        }
        String sign = LetvApiUtil.digest(map, Constants.LECLOUD_SECRET);
        map.put("sign", sign);
        
        MultipartEntity reqEntity = new MultipartEntity();
        FileBody fileBody = new FileBody(file);
        if (params != null && params.size() > 0) {
            for (Entry<String, String> entry : params.entrySet()) {
                reqEntity.addPart(entry.getKey(), new StringBody(entry.getValue()));
            }
        }
        reqEntity.addPart("video_file", fileBody);
        HttpPost post = new HttpPost(postUri);
        post.setEntity(reqEntity);
        CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(RequestConfig.DEFAULT).build();
    	HttpResponse  httpResponse = httpClient.execute(post);
    	return httpResponse;
	}
	
	
	
}

