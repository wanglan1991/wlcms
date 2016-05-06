package com.ekt.cms.qcloud.Module;

import com.ekt.cms.qcloud.Common.*;

import java.util.TreeMap;

public abstract class Base {
	protected String serverHost = "";
	protected String serverUri = "/v2/index.php";
	protected String secretId = "";
	protected String secretKey = "";
	protected String defaultRegion = "";
	protected String requestMethod = "GET";

	public void setConfig(TreeMap<String, Object> config) {
		if (config == null)
			return;
		for (String key : config.keySet()) {
			switch (key) {
			case "SecretId":
				setConfigSecretId(config.get(key).toString());
				break;

			case "SecretKey":
				setConfigSecretKey(config.get(key).toString());
				break;

			case "DefaultRegion":
				setConfigDefaultRegion(config.get(key).toString());
				break;

			case "RequestMethod":
				setConfigRequestMethod(config.get(key).toString());
				break;

			default:
				;
				break;
			}
		}
	}

	public void setConfigSecretId(String secretId) {
		this.secretId = secretId;
	}

	public void setConfigSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public void setConfigDefaultRegion(String region) {
		this.defaultRegion = region;
	}

	public void setConfigRequestMethod(String method) {
		this.requestMethod = method;
	}

	public String getLastRequest() {
		return Request.getRequestUrl();
	}

	public String getLastResponse() {
		return Request.getRawResponse();
	}
	
	private String ucFirst(String word){
		return word.replaceFirst(word.substring(0, 1),
				word.substring(0, 1).toUpperCase());
	}

	public String generateUrl(String actionName, TreeMap<String, Object> params) {
		actionName = ucFirst(actionName);
		if(params == null)
			params = new TreeMap<String, Object>();
		params.put("Action", actionName);
		if (!params.containsKey("Region")) {
			params.put("Region", defaultRegion);
		}
		return Request.generateUrl(params, secretId, secretKey, requestMethod,
				serverHost, serverUri);
	}
	
	public String call(String actionName, TreeMap<String, Object> params){
		return call(actionName, params, null);
	}
	
	public String call(String actionName, TreeMap<String, Object> params, String fileName){
		actionName = ucFirst(actionName);
		if(params == null)
			params = new TreeMap<String, Object>();
		params.put("Action", actionName);
		if (!params.containsKey("Region")) {
			params.put("Region", defaultRegion);
		}
		String response = Request.send(params, secretId, secretKey, requestMethod, serverHost, serverUri, fileName);
		return response;
	}
}
