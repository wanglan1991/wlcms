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
	
	/**
	 * 上传用到的密钥ID和key
	 */
	 public final static String DEFAULT_UPLOAD_SECRETID = "AKIDzmf42z75tIn1R1517NmQZFNzKEr9GNhY";
	 public final static String DEFAULT_UPLOAD_SECRETKEY = "eJSYfrccKT5aHJQEJJqHr3hLJplcvQU5";

	/**
	 * 乐视云
	 */
	 public final static String LECLOUD_API_URI="http://api.letvcloud.com/open.php";
	 public final static String LECLOUD_CLIENT_IP="192.168.1.11";
	 /**
	 * 乐视云 用户唯一标识码，由乐视网统一分配并提供
	 */
	 public final static String LECLOUD_USER_UNIQUE="qwlsaqi9kx";
	 /**
	 * 乐视云 返回参数格式：支持json和xml两种方式
	 */
	 public final static String LECLOUD_FORMAT="json"; 
	 /**
	 * 乐视云 协议版本号，统一取值为2.0
	 */
	 public final static String LECLOUD_VER="2.0";
	 /**
	 * 乐视云  乐视网统一分配并提供的用户私钥
     */
	 public final static String LECLOUD_SECRET="ecbf7093b6f4c02cf7c6286449eaa7e1";
	 /**
	  * 阿里云OSS ACCESS_KEY_ID
	  */
	 public final static String DEFAULT_OSS_ACCESS_KEY_ID ="NqEreTvHzKmQaekA";
	 /**
	  * 阿里云OSS ACCESS_KEY_SECRETS
	  */
	 public final static String DEFAULT_OSS_ACCESS_KEY_SECRET ="qpTNXZEqpA8KN7zSAWLPnP1jJl5vRM";
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
