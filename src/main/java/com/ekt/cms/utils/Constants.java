package com.ekt.cms.utils;

public interface Constants {
	public final static String SUPER_TREE_NODE="0";
	/**
     * HTTP Session中的用户名 Key
     */
	public final static String DEFAULT_SESSION_USERNAME = "username";
	/**
	 * HTTP Session中的用户  account
	 */
	public final static String DEFAULT_SESSION_ACCOUNT="account";
	/**
	 * HTTP session中的用户 id
	 */
	public final static String DEFAUL_TSESSION_ACCOUNT_ID="accountId";

    /**
     * WEBSOCKET Session中的用户名 Key
     */
	public final static String DEFAULT_WEBSOCKET_USERNAME = "ws_username";
	
	public final static String DEFAULT_WEBSOCKET_HOSTNAME = "hostname";
	
	
	public final static String BUCKET_NAME ="ekt";
	
	/**
	 * 上传用到的密钥ID和key
	 */
	 public final static String DEFAULT_UPLOAD_SECRETID = "AKIDzmf42z75tIn1R1517NmQZFNzKEr9GNhY";
	 public final static String DEFAULT_UPLOAD_SECRETKEY = "eJSYfrccKT5aHJQEJJqHr3hLJplcvQU5";

	
	 /**
	  * 阿里云OSS ACCESS_KEY_ID
	  */
	 public final static String DEFAULT_OSS_ACCESS_KEY_ID ="NqEreTvHzKmQaekAa";
	 /**
	  * 阿里云OSS ACCESS_KEY_SECRETS
	  */
	 public final static String DEFAULT_OSS_ACCESS_KEY_SECRET ="qpTNXZEqpA8KN7zSAWLPnP1jJl5vRMb";
	 /**
	  * 阿里云OSS 外网 endpoint
	  */
	 public final static String DEFAULT_OSS_ENDPOINT ="oss-cn-shenzhen.aliyuncs.com";
	 
	public enum VERSIONTYPE{  
	    MQ_MAIN("0");
		private final String value;

		private VERSIONTYPE(String value) {
            this.value = value;
        }  
        public String getValue(){  
            return this.value;  
        }  
	} 
}