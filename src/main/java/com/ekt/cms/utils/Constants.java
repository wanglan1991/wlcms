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
     * WEBSOCKET Session中的用户名 Key
     */
	public final static String DEFAULT_WEBSOCKET_USERNAME = "ws_username";
	
	public final static String DEFAULT_WEBSOCKET_HOSTNAME = "hostname";
	
	
	
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
