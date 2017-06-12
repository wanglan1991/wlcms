package com.ekt.cms.utils.IPLocation;

import com.ekt.cms.utils.TextUtil;
import com.ekt.cms.utils.IPLocation.entity.IPInfo;
import com.google.gson.Gson;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.GetMethod;

/**
 * 获取ip位置工具类
 * 
 * @author wanglan 2017年3月28日下午7:58:54
 */
public class IPLocationUtil {

	private static HttpClient httpClient;
	private static final String BAIDU_MAP_INTERFACE = "http://api.map.baidu.com/location/ip?ak=F454f8a5efe5e577997931cc01de3974&ip=";
	private static Gson gson;

	private static Gson getGson() {
		if (gson == null) {
			return gson = new Gson();
		}
		return gson;
	}

	private static HttpClient getHttpClient() {
		if (httpClient == null) {
			return httpClient= new HttpClient();
		}
		return httpClient;
	}

	/**
	 * 根据ip v4 获取ip所在地相关信息
	 * 
	 * @param ipAddress
	 * @return IPInfo.class
	 */
	public static IPInfo getLocationByIpV4(String ipAddress) {
		if (!TextUtil.isIpAddress(ipAddress)) {
			new Exception("IP format error!");
			return null;
		} else {
			HttpClient client = getHttpClient();
			HttpMethod method = new GetMethod(BAIDU_MAP_INTERFACE + ipAddress);
			try {
				client.executeMethod(method);
				if (method.getStatusLine().getStatusCode() == 200) {
					return getGson().fromJson(method.getResponseBodyAsString(), IPInfo.class);
				} else {
					return null;
				}

			} catch (IOException e) {
				e.printStackTrace();
				return null;
			} finally {
				// 释放链接
				method.releaseConnection();
			}

		}

	}

	
	/**
	 * 获取客户端ip 支持多级代理
	 * @param request
	 * @return 
	 */
	public static String getClientIpAddress(HttpServletRequest request) {

//		String ip = request.getHeader("x-forwarded-for");
		String ip = request.getHeader("X-Real-IP");

		String localIP = "127.0.0.1";

		if ((ip == null) || (ip.length() == 0) || (ip.equalsIgnoreCase(localIP)) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if ((ip == null) || (ip.length() == 0) || (ip.equalsIgnoreCase(localIP)) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if ((ip == null) || (ip.length() == 0) || (ip.equalsIgnoreCase(localIP)) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

}
