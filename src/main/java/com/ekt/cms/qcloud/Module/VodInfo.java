package com.ekt.cms.qcloud.Module;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.TreeMap;

public class VodInfo extends Base {
	public VodInfo(){
		serverHost = "vod.api.qcloud.com";
	}
	
	
	public String DescribeVodPlayUrls(TreeMap<String, Object> params) throws NoSuchAlgorithmException, IOException {
		String actionName = "DescribeVodPlayUrls";
		
		
        return call(actionName, params);
	}
	
	
	public String DescribeVodInfo(TreeMap<String, Object> params) throws NoSuchAlgorithmException, IOException {
		String actionName = "DescribeVodInfo";
		
		
        return call(actionName, params);
	}
	
	public String DescribeVodPlayInfo(TreeMap<String, Object> params) throws NoSuchAlgorithmException, IOException {
		String actionName = "DescribeVodPlayInfo";
		
		
        return call(actionName, params);
	}
	public String ConvertVodFile(TreeMap<String, Object> params) throws NoSuchAlgorithmException, IOException {
		String actionName = "ConvertVodFile";
        return call(actionName, params);
	}
}
